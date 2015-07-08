// File:    Sketch.java
// Author:  Carl Stevenson
// Date:    2/25/13
// Purpose: To use methods in order to create a mock police sketch.

import java.util.*;  // Scanner

public class Sketch
{
    // Declare global menus for ease.
    static String hairmenu = ("Please enter a hairstyle:"
          		      + "\n        1, for bald;"
			      + "\n        2, for crew-cut;"
			      + "\n        3, for unkempt;"
			      + "\n        4, for beanie;\n-->");
	
    static String eyemenu = ("Please enter an eye type:"
  			    + "\n        1, for small;"
			    + "\n        2, for large;"
			    + "\n        3, for cross-eyed;"
			    + "\n        4, for sunglasses;\n-->");
							
    static String nosemenu = ("Please enter a nose shape:"
			     + "\n        1, for piggie;"
			     + "\n        2, for small;"
			     + "\n        3, for large;"
			     + "\n        4, for wart;\n-->");
							
    static String mouthmenu = ("Please enter a mouth/facial hair descriptor:"
			      + "\n        1, for good teeth;"
			      + "\n        2, for crooked teeth;"
			      + "\n        3, for stubble;"
			      + "\n        4, for facial hair;\n-->");
	
    // global scanner
    static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args)
    {
	intro();  // Introduction to the program
	int[] data = new int[4];  // data storage
	data[0] = prompt(hairmenu);
	data[1] = prompt(eyemenu);
	data[2] = prompt(nosemenu);
        data[3] = prompt(mouthmenu);
	hair(data[0]);
	eye(data[1]);
	nose(data[2]);
	mouth(data[3]);
		
    }
	
    public static void intro()
    {
	System.out.println("\nProgram to sketch a suspect.");
	System.out.print("You will be asked to choose a hairstyle, nose "
	                 + "type,\neye type and mouth type. Then your sketch"
			 + " will be made.");
	System.out.println("\nWritten by Carl Stevenson.");
    }	
	
    public static int prompt(String menu)
    {
	int choice;
	while (true)
	{
	    while (true)
	    {
     	    	System.out.println();
            	System.out.print(menu);
            	System.out.println();
    	    	if(sc.hasNextInt()) break;
	    	String error = sc.nextLine();
	    	System.out.println("You must enter a menu item <1-4>, not "
			          + error + ". Try again.");
	    }
	    choice = sc.nextInt();
	    if ((choice <= 4) && (choice > 0)) break;
	    System.out.println("You must enter a menu item <1-4>, not "
			      + choice + ". Try again.");
	}
	return choice;
    }			

    public static void hair(int choice)
    {
	switch(choice)
	{
	    case 1: System.out.println("   ________");
		    System.out.println("  |	   |");
		    break;
	    case 2: System.out.println("   ********");
		    System.out.println("  *        *");
		    break;
	    case 3: System.out.println("   MMMMMMMM");
		    System.out.println("  E        F");
		    break;
	    case 4: System.out.println("     mmm");
		    System.out.println("   //   \\\\");
	   	    System.out.println("  8888888888");
		    break;
	    default: break;
	}
	return;
    }

    public static void eye(int choice)
    {
	switch(choice)
	{
	    case 1: System.out.println(" d|  .  .  |b");
		    break;
	    case 2: System.out.println(" d| (.)(.) |b");
		    break;
	    case 3: System.out.println(" d| (>)(<) |b");
		    break;
	    case 4: System.out.println(" d|(__nn__)|b");
		    break;
	    default: break;
	}
    return;
    }

    public static void nose(int choice)
    {
	switch(choice)
	{
	    case 1: System.out.println("  |   00   |");
		    break;
	    case 2: System.out.println("  |   |>   |");
		    break;
	    case 3: System.out.println("  |   |\\   |");
	    	    System.out.println("  |   |/   |");
		    break;
	    case 4: System.out.println("  |   |\\   |");
		    System.out.println("  |   c/   |");
		    break;
	    default: break;
	}
    return;
    }

    public static void mouth(int choice)
    {
	switch(choice)
	{
	    case 1: System.out.println("  | /WWWW\\ |");
		    System.out.println("  | \\MMMM/ |");
		    System.out.println("   ________");
		    break;
	    case 2: System.out.println("  | /Uuwu\\ |");
		    System.out.println("  | \\nNMm/ |");
		    System.out.println("   ________");
		    break;
	    case 3: System.out.println("  |........|");
	 	    System.out.println("  |...--...|");
		    System.out.println("   ::::::::");
		    break;
	    case 4: System.out.println("  |????????|");
		    System.out.println("  |???--???|");
		    System.out.println("   ????????");
		    break;
	    default: break;
	}
    return;
    }
}
