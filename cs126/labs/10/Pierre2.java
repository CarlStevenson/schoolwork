//  Pierre2.java solves chef Pierre's fraction multiplication problem
//  using a Fraction class with print output.
//
//  Author: Charles Hoot, for Hands On Java.
//  Date: July 2000.
//  Revised: October 1997.
//  Adapted from code by: Joel C. Adams, for Hands On C++, October 1997.
//
//  Edited by: Carl Stevenson
//
//  Input: Two Fraction values.
//  Precondition: Neither Fraction has a denominator of zero.
//  Output: The product of the two Fractions.
// ********************************************************


import java.util.*;  // Scanner
import java.io.*;    // PrintStream

public class Pierre2 extends Object{

    public static void main(String args[]){

	PrintStream theScreen = new PrintStream(System.out);
	Scanner theKeyboard = new Scanner(System.in);
	
	theScreen.println("\nTo convert fractional measures,");
	theScreen.print(" enter the fractional measure you want to convert: ");
	Fraction oldMeasure = new Fraction();
	oldMeasure.read(theKeyboard);  
	
	theScreen.print("\nEnter the fractional amount to reduce/increase it by: ");
	Fraction scaleFactor = new Fraction(1,6);
	scaleFactor.read(theKeyboard); 
	
	Fraction newMeasure = oldMeasure.times(scaleFactor);
	
	
	theScreen.println("\nThe converted measurement is: " + newMeasure + "\n");
    }
}