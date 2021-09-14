/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventdriven;
import java.util.TreeMap;





    
     
 
   

   import java.util.LinkedHashMap;
   import java.util.TreeMap;
   import java.util.Set;
   import java.util.Iterator;

               

    class EventList {

    public String type;
    public double time;
}

    

public class EventDriven {
    private final double k = 16807.0;
     private final double m = 2147483647;
     public  double s0 = 1234.0;
    


    public void EventDriven(int rho, int myk, int mym, double mymew, double mygamma) {
        int k,m,N,done;
        double clock,gamma,mew,lambda,EN,NDep,NBlock, NArr,u;
         k = myk;  

         m = mym;  

         N = 0;  

         clock = 0.0; 

         gamma = mygamma;   

         mew = mymew;      

        lambda = (m * mew * rho)/10 ; 

        done= 0;   

        EN = 0.0;    

        NDep = 0;   

         NBlock = 0; 
         NArr = 0;

         u = 0;   

        TreeMap<Double, String> tm = new TreeMap<>(); 

        

        EventList currentEvent = new EventList();
        EventDriven exp = new EventDriven(); 
        tm.put(exp.RandomVariable(lambda), "ARV1"); 
        tm.put(exp.RandomVariable(gamma), "ARV2");
        

        
       

        while (done!= 1) { 

            
            EventList eventList= new EventList();
            double mykey;
            String myvalue;
            mykey = tm.firstKey();
            myvalue = tm.get(mykey);
            eventList.type = myvalue;
            eventList.time = mykey;
            tm.remove(mykey);
            currentEvent = eventList;

            double prev = clock;
            clock = currentEvent.time;
          

               if (currentEvent.type.equals("ARV1"))  {  

                    EN = EN + (N * (clock - prev));
                    u = util(N, clock, prev, u, m);
                    if (N < k) {
                        N++;
                        NArr++;
                        tm.put(clock + exp.RandomVariable(lambda), "ARV1");
                    } else {
                        NArr++;
                        tm.put(clock + exp.RandomVariable(lambda), "ARV1");
                        NBlock++;
                    }
                    if (N <= m) {
                        tm.put(clock + exp.RandomVariable(mew), "DEP");
                    }
               }
                if (currentEvent.type.equals("ARV2"))     {

                    EN = EN + (N * (clock - prev));
                    u = util(N, clock, prev, u, m);
                    if (N < 2) {
                        N++;
                        NArr++;
                        tm.put(clock + exp.RandomVariable(gamma), "ARV2");
                        if (N <= m) {
                            tm.put(clock + exp.RandomVariable(mew), "DEP");
                        }
                    } else {
                        tm.put(clock + exp.RandomVariable(gamma), "ARV2");
                    }
                }

                if (currentEvent.type.equals("DEP") ) {

                    EN = EN + (N * (clock - prev));
                    u = util(N, clock, prev, u, m);
                    N--;
                    NDep++;
                    if (N >= m) {
                        tm.put(clock + exp.RandomVariable(mew), "DEP");
                    }
            
            if (NDep == 100000) {   

                done= 1;
            }
        }
        }
         

        
        System.out.println("The simulation for rho "+(lambda / (m * mew)));
        System.out.println("The Number of Customers in the System " + N);
        System.out.println("The Number of Blocked Customers " + NBlock);
        System.out.println("Expected number of customers in the system: " + EN / clock);
        System.out.println("Expected time spent by a customer: " + EN / NDep);
        System.out.println("Blocking probability: " + (NBlock / (double) NArr));
        System.out.println("Total utilization of the system: " + u / clock);
        System.out.println();

    }
    private double util(int N, double clock, double prev, double u, int m) {
        if (N==0){
           return u;
        }
        else{
            
                if (N < m) {
                    u = u + ((N / (double) m) * (clock - prev));
                } else {
                    u = u + (clock - prev);
                }
               
        }
        return u;
    }
   
    public double RandomVariable(Double lambda) {
         
         s0 = (k * s0) % m;
         double uniformgen= s0 / m;
         double exponvar = ((-1) / lambda) * (Math.log(uniformgen));
         return exponvar;
    }

   
   
     
    
     
 
}
    