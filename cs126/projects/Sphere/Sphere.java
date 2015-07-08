// File:    Sphere.java
// Author:  Carl Stevenson
// Date:    1/24/13
// Purpose: To calculate the surface area and volume of any sphere.

import java.util.*;  // scanner
public class Sphere
{
    public static void main(String[] args)
    {
	//intro
	System.out.println("Program to calculate the surface area");
	System.out.println("and volume of a sphere.");
	System.out.println("Written by Carl Stevenson.");
	System.out.println("You will be asked to enter the "
			   + "radius of the sphere.\n");
	
	// get inputs
	double radius;
	Scanner input = new Scanner(System.in);
	System.out.print("Enter Radius: ");
	radius = input.nextDouble();
	System.out.println("\n");  // for turnin
	
	// calculate
	double surfaceArea, volume;
	surfaceArea = 4 * Math.PI * radius * radius;
	volume = (4./3.) * Math.PI * radius * radius * radius;
	
	// echo and output
	System.out.println("A sphere with radius " + radius + " has:\n");
	System.out.println("Surface Area : " + surfaceArea);
	System.out.println("      Volume : " + volume);
	System.out.println();
	
	
    }  //end of main
}  // end of class 