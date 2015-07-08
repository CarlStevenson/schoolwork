//  Prepend2.java compares the time required to prepend 1 values at the end of
//		An Array based List of size n
//		A Linked List based List of size n
//
//  Author: Charles Hoot, for Hands On Java.
//  Modified from code by : Joel Adams, for Hands On C++.
//  Further modification by: John Koch (rename HOJTimer class to HOJTimer)
//  Further modification by: Carl Stevenson

import java.util.*;  //ArrayList and LinkedList
import java.io.*;    // PrintStream

public class Prepend2
{
  static PrintStream theScreen = new PrintStream(System.out);
  static Scanner theKeyboard = new Scanner(System.in);

    public static void main(String args[])
    {
	
	HOJTimer theTimer = new HOJTimer();              // create a HOJTimer
		
	theScreen.print("\nPrepend Timing Test 2:\n"
       			+ " Enter the size of the list to prepend to: ");
	int n = theKeyboard.nextInt();

	// Linked List Based List                                            
                                                    
	LinkedList<Integer> aLinkedList = null;				     //declare it	

	aLinkedList = new LinkedList<Integer>();         	     // build empty linked list
	// prepend n values
	for(int i = 0; i<n; i++){
		aLinkedList.add(0, new Integer(i));
	}
	//  to list
	// start HOJTimer
	theTimer.start();
	aLinkedList.add(0, new Integer(1));              //  add one value to list
	theTimer.stop();	
	// stop HOJTimer
		

	theScreen.println("\nPrepending one value to a LinkedList took : "
			  + theTimer.getTime() + " seconds.");

		
		
	// Array Based List  
	ArrayList<Integer> anArrayList = null;				     //declare it
	anArrayList = new ArrayList<Integer>(n);                  // build empty list
	// prepend n values
	//  to list
	for(int i =0; i<n; i++){
		anArrayList.add(0, new Integer(i));
	}
	// start HOJTimer
	theTimer.start();
	anArrayList.add(0, new Integer(1));              //  add one value to list
	// stop HOJTimer
	theTimer.stop();
		
		

	theScreen.println("\nPrepending one value to an ArrayList took : "
			  + theTimer.getTime() + " seconds.");
                                                    
    }
}//end of class
