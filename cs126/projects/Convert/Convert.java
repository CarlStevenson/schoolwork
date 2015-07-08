// Convert.java tests out the metric conversion functions
//   defined in the module Metric.
//
// Author: Carl Stevenson
// Date: 2/7/13
// Purpose: To test the Metric package
// ****************************************************

import java.util.*;  // Scanner

public class Convert
{
    public static void main(String[] args)
    {
	System.out.println("\nProgram to convert units.");
	System.out.println("Written by Carl Stevenson.");
	System.out.println("You will be asked to enter one number.");
	System.out.print("\nEnter a number: ");
	// Make scanner and get number
	Scanner sc = new Scanner(System.in);
	double num = sc.nextDouble();
	// Begin conversions
       	double meters = Metric.feetToMeters(num);
	System.out.println("\n\n" + num + " feet = " + meters + " meters");
	double grams = Metric.ouncesToGrams(num);
	System.out.println(num + " ounces = " + grams + " grams");
	double ptk = Metric.poundsToKilograms(num);
	System.out.println(num + " pounds = " + ptk + " kilograms");
	double ttk = Metric.tonsToKilograms(num);
	System.out.println(num + " tons = " + ttk + " kilograms\n");
    }
}