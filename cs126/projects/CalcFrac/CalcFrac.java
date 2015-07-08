// Calcfrac.java
// Date: 4/4/13
// Written by Carl Stevenson
//
// A menu-driven fraction calculator, using Fraction.java
//********************************************************************

import java.util.*; // Scanner
import java.io.*;   // PrintStream

public class CalcFrac{

    static Scanner sc = new Scanner(System.in);  // global scanner
    static PrintStream out = new PrintStream(System.out); // easier prints
    
    // global menu
    final static String MENU = ("Please enter an operation:\n"
			        + "        1, for add;\n"
				+ "        2, for subtract;\n"
				+ "        3, for multiply;\n"
				+ "        4, for divide;\n"
				+ "        5, for less than;\n"
				+ "        6, for less than or equal to;\n"
				+ "        7, for equal to;\n"
				+ "        8, for greater than;\n"
				+ "        9, for greater than or equal to;\n"
				+ "        0, to stop;\n"
				+ "-->");

    public static void main(String[] args){

		// intro
		out.println("Program to calculate operations on fractions.");
		out.println("Written by Carl Stevenson\n");
	
		// into the calculator
		int choice;
		// set up what is needed for the loop
		boolean go = true;
		Fraction frac1 = new Fraction();
		Fraction frac2 = new Fraction();
		Fraction result = new Fraction();

		while(go){	
		
			out.print(MENU);
			if(sc.hasNextInt())
	    		choice = sc.nextInt();
			else throw new IllegalArgumentException("You need to enter a menu"
								+" item <0-9>, not "
								+ sc.nextLine());

 			out.println("\n"); // for turnin

	    	switch(choice){
		
	    	case 1: frac1 = getFrac(1);
					frac2 = getFrac(2);
					result = frac1.plus(frac2);
					arithFrac(frac1, frac2, result, " + ");
					break;  
	    
	    	case 2: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					result = frac1.minus(frac2);
					arithFrac(frac1, frac2, result, " - ");
					break;

	    	case 3: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					result = frac1.times(frac2);
					arithFrac(frac1, frac2, result, " * ");
					break;

	    	case 4: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					result = frac1.over(frac2);
					arithFrac(frac1, frac2, result, " / ");
					break;

	    	case 5: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					relaFrac(frac1, frac2, frac1.isLessThan(frac2),
							"less than ");
					break; 

	    	case 6: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					relaFrac(frac1, frac2, frac1.isLessOrEqualTo(frac2),
							"less than or equal to ");
					break;

	    	case 7: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					relaFrac(frac1, frac2, frac1.isEqualTo(frac2),
							"equal to ");
					break;	    

	    	case 8: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					relaFrac(frac1, frac2, frac1.isGreaterThan(frac2),
							"greater than ");
					break;	    

	    	case 9: frac1 = getFrac(1);
                    frac2 = getFrac(2);
					relaFrac(frac1, frac2, frac1.isGreaterOrEqualTo(frac2),
							"greater than or equal to ");
					break;

	    	case 0: go = false;
					break;
	    
	    	}
		}
	}
	

	// getFrac aids in getting the fraction from the user

	public static Fraction getFrac(int choice){
		
		if (choice == 1)
			out.println("Enter first fraction:  ");
		else out.println("Enter second fraction: ");
			Fraction frac = new Fraction();
			frac.read(sc);
			out.println();
			return frac;
	}

	// arithFrac prints the output of an arithmetic operation

	public static void arithFrac(Fraction frac1, Fraction frac2, 
									Fraction result, String op){

		
		out.println(frac1.toString() + op + frac2.toString() +
                    " is " + result.toString());
        out.println("\n");
		return;
	}

	// relaFrac prints the output of a comparison operation
	
	public static void relaFrac(Fraction frac1, Fraction frac2, boolean result,
								String rela){

		if(result)
			out.println(frac1.toString() + " is " + rela + frac2.toString());
		else
			out.println(frac1.toString() + " is not " + rela +
						frac2.toString());
		out.println("\n");
		return;
	}

}

