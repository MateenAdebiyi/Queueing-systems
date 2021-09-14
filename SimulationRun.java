/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventdriven;
import java.util.Scanner;

/**
 *
 * @author MATEEN
 */
public class SimulationRun {
    
      public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the System Capacity");
       int k= input.nextInt();
       System.out.println("Enter the number of servers");
       int m= input.nextInt();
       System.out.println("Enter the service rate");
       int mew= input.nextInt();
       System.out.println("Enter the first machine arrival rate");
       int gamma= input.nextInt();
       
        EventDriven[] eventDriven = new EventDriven[10];
        for (int i = 0; i < 10; i++) {
            eventDriven[i] = new EventDriven();
        }
        for (int i = 0; i < 10; i++) {
            eventDriven[i].EventDriven(((i+1)), k, m, mew, gamma); 
        }
    }
    
}

