// File:    Rainbow.java
// Author:  Carl Stevenson
// Date:    1/31/13
// Purpose: To calculate the height of a rainbow, given the distance to it.

import java.util.*;  // For scanner.

public class Rainbow
{
    // Global scanner for ease and practice.
    public static final Scanner sc = new Scanner(System.in);  

    public static void main(String[] args)
    {
	// Seperate methods for practice.
	double distance = intro();
	// Check for good value, calculate if good, terminate if bad.
	if (distance > 0)
	    {
		calc(distance);
	    }
	    else
	    {
		System.out.println("You entered a number less than"
				   + " or equal to zero.");
	    }
	
    }
    
    public static double intro()
    {
	System.out.println("\nProgram to calculate the height of a rainbow.");
	System.out.println("Program checks that the distance is greater");
	System.out.println("than zero.");
	System.out.println("Written by Carl Stevenson.");
	System.out.println("You will be asked to enter the distance"
			   + " to the rainbow.");
	boolean check = false;
	double distance;	
	System.out.print("\nEnter distance: ");
	distance = sc.nextDouble();
	System.out.println("\n");  // Extra line for turnin.
	System.out.println("Given that the distance to the rainbow is "
			   + distance + ":");
	
	System.out.println();
	return distance;
    }

    public static void calc(double distance)
    {
	// Doing the conversion and trig is unneccesary work, 
	// included it to be safe.
	double angle = 42.3333333 * (Math.PI / 180.);
	double tangent = Math.tan(angle);
	double height = distance * tangent;
	System.out.println("The height of the rainbow is:  " + height);
	System.out.println();
    }
}  // End of class.
