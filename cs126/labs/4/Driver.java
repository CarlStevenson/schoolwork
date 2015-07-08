//Driver.java tests out the metric conversion functions
//   defined in the module Metric.
//
// Author: Carl Stevenson
// Date: 2/7/13
// Purpose: To test the Metric package
// ****************************************************

import java.util.*;  // Scanner

public class Driver
{
    public static void main(String[] args)
    {
	//1. Display a prompt for whatever values the method requires 
	//as arguments.
	System.out.println("Enter a number: ");
	//2. Read those values from the keyboard.
	Scanner sc = new Scanner(System.in);
	double num = sc.nextDouble();
	//3. Display the result of calling the method,
	//   using the input values as arguments (plus a descriptive label).
	double meters = Metric.feetToMeters(num);
	System.out.println("\n\n" + num + " feet = " + meters 
		       + " meters");
    }
}