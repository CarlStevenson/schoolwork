// File:    Chill.java
// Author:  Carl Stevenson
// Date:    2/18/13
// Purpose: To take temperature of wind chill from the user and output the
//          danger of the wind chill.

import java.util.*;  // Scanner

public class Chill
{
    static Scanner sc = new Scanner(System.in); // global scanner            
    public static void main(String[] args)
    {
        int num = intro(); // display introductory text & 
                           // prompt number of wind chills
	output(num);       // interact with user to output the wind chills
    }
    public static int intro()
    {
        System.out.println("\nProgram to calculate the condition"
			   + " of a group of wind chills.");
	System.out.println("Written by Carl Stevenson.");
	System.out.println("You will be asked to enter the number of"
	                   + " wind chills,");
	System.out.println("followed by the value of each wind chill.\n");
	System.out.print("Enter the number of wind chills: ");
	int num = sc.nextInt();
	System.out.println("\n"); // for turnin
	return num;
    }
	
    public static void output(int num)
    {
	double sum = 0;
	for(int i=1; i <= num; i++)
	{	
            System.out.print("Enter the value of wind chill " + i + ": ");
	    int chillValue = sc.nextInt();
	    sum += chillValue;
	    System.out.println();  // for turnin
	    System.out.println(condition(chillValue));
	    System.out.println();
	}
	    System.out.printf("The average of the %d wind chills is %.3f\n\n", num, 
			      (sum/num));
    }
    public static String condition(int chillValue)
    {
        if (chillValue < -70)
	    return ("A wind chill of " + chillValue + " means"
		    + "\nexposed flesh will usually freeze within half"
		    + " a minute");
	else if (chillValue < -30)
	    return ("A wind chill of " + chillValue + " means"
		    + "\nfrostbite is likely and outdoor activity becomes "
  		    + "dangerous");
	else if (chillValue < -10)
	    return ("A wind chill of " + chillValue + " means frostbite"
		    + " is possible");
	else if (chillValue < 10)
	    return ("A wind chill of " + chillValue + " is considered"
		    + " unpleasant");
	else 
	    return ("A wind chill of " + chillValue + " is not considered "
		    + "dangerous or unpleasant");
    }
}
