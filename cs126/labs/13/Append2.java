//Append2.java compares the time required to append 1 values at the end of
//		An Array based List of size n
//		A Linked List based List of size n
//
//  Author: Charles Hoot, for Hands On Java.
//  Modified from code by : Joel Adams, for Hands On C++.
//  Further modification by: Carl Stevenson

import java.util.*;  //ArrayList and LinkedList
import java.io.*;    // PrintStream

public class Append2 
{
  static PrintStream theScreen = new PrintStream(System.out);
  static Scanner theKeyboard = new Scanner(System.in);

	public static void main(String args[])
	{
	
		HOJTimer theTimer = new HOJTimer();                
	
		theScreen.print("\nAppend Timing Test 2:\n"
       			+ " Enter the size of the list to append to: ");
		int n = theKeyboard.nextInt();

		// Linked List Based List                                            
                                                    
		LinkedList<Integer> aLinkedList = null;				     	

		aLinkedList = new LinkedList<Integer>();                  
														 
		for(int i =0; i<n; i++){
			aLinkedList.add(i, new Integer(1));
		}											 
														 
		theTimer.start();		
		
		aLinkedList.add(new Integer(1));                 
														 
		theTimer.stop();

		theScreen.println("\nAppending one value to a LinkedList took : "
		   + theTimer.getTime() + " seconds.");

		
		
		// Array Based List
		ArrayList<Integer> anArrayList = null;				     
		anArrayList = new  ArrayList<Integer>(n);                 
														

		
                                                    
	
														
		
		for(int i =0; i<n; i++){
			anArrayList.add(new Integer(1));                 
		}												 
		theScreen.println("\nSize of ArrayList before append is : " +
			 anArrayList.size() + ".");
		theTimer.start();		
		anArrayList.add(new Integer(1));
		theTimer.stop();

		theScreen.println("\nSize of ArrayList after append is : " +
			 anArrayList.size() + ".");
                                                    
		
		

		theScreen.println("\n\nAppending one value to an ArrayList took : "
		   + theTimer.getTime() + " seconds.");
                                                    
	}
}//end of class
