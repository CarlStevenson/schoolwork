// Hypot.java computes the hypotenuse length of a right triangle,
// given the lengths of its two legs
//
// Author:  Carl Stevenson
// Date:    1/24/13
// Purpose: To explore Object-Centered Design by designing a hypotenuse
//          calculator.
// Specification:
//   input (keyboard):
//   output (screen):
// *****************************************************************************

import java.util.*;  // Scanner

public class Hypot
{
    public static void main(String[] args)
    {
	// intro
	System.out.println("Program to calculate the hypotenuse");
	System.out.println("of a right triangle given the");
	System.out.println("Lengths of the two sides.");
	System.out.println("Written by Carl Stevenson\n");

	// input length of 2 sides
	double leg1, leg2;
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the length of side 1: ");
	leg1 = input.nextDouble();
	System.out.println();  // for turnin
	System.out.print("Enter the length of side 2: ");
	leg2 = input.nextDouble();
	System.out.println();  // for turnin
	// calculate the hypotenuse
	double hypotenuse = Math.sqrt(Math.pow(leg1, 2)
				      + Math.pow(leg2, 2));

	// output results
	System.out.println("\nFor a right triangle with sides");
	System.out.print("of length " + leg1 + " and " + leg2 
			   + " is ");
	System.out.printf("%5.3f", hypotenuse);
	System.out.println();

    }  // end of main
}  // end of class