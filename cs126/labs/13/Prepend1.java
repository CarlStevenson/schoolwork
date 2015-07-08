// Prepend1.java compares the time required to prepend n values at the beginning of
//              An Array based List
//              A Linked List based List
//
//  Author: Charles Hoot, for Hands On Java.
//  Modified from code by : Joel Adams, for Hands On C++.
//  Further modification by: John Koch (rename HOJTimer class to HOJTimer)
//  Further modification by: Carl Stevenson

import java.util.*;  //ArrayList and LinkedList
import java.io.*;    // PrintStream

public class Prepend1 
{
  static PrintStream theScreen = new PrintStream(System.out);
  static Scanner theKeyboard = new Scanner(System.in);

    public static void main(String args[])
    {
        
        HOJTimer theTimer = new HOJTimer();                    // create a HOJTimer
                
        theScreen.print("\nPrepend Timing Test 1:\n"
                        + " Enter the number of values you want to prepend: ");
        int n = theKeyboard.nextInt();

        // Linked List Based List                                            
                                                    
        LinkedList<Integer> aLinkedList = null;                               //declare it       

        aLinkedList = new LinkedList<Integer>();                      // build empty linked list
        theTimer.start();                                // start HOJTimer
        for (int i = 0; i < n; i++)                      // prepend n values
            aLinkedList.add(0, new Integer(i));          //  to list
        theTimer.stop();                                 // stop HOJTimer
                

        theScreen.println("\nPrepending " + n            // display time
                          + " values to a LinkedList took : "
                          + theTimer.getTime() + " seconds.");

                
                
        // Array Based List
        ArrayList<Integer> anArrayList = null;                                //declare it
        anArrayList = new ArrayList<Integer>((int) n);            // build empty list
        theTimer.start();                                // start HOJTimer
        for (int i = 0; i < n; i++)                      // prepend n values
            anArrayList.add(0, new Integer(i));          //  to list
        theTimer.stop();                                 // stop HOJTimer
                
                

        theScreen.println("\nPrepending " + n             // display time
                          + " values to an ArrayList took : "
                          + theTimer.getTime() + " seconds.");
                                                    
    }
}//end of class
