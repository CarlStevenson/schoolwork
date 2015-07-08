//Calculate2.java provides a simple 5-function calculator.
//
// Adapted by: Charles Hoot, for Hands On Java.
// From: Joel Adams, for Hands On C++.
// Completed by: Carl Stevenson
// Date: 3/14/2013
// Purpose: To make a calculator to practice repetitive execution.
//
// input(keyboard): a char, stored in operator;
//                  two reals, stored in op1 and op2;
// output(screen): the result of the expression (op1 operator op2).
// **************************************************************

import java.util.*;  // Scanner
import java.io.*;    // PrintStream

class Calculate2 extends Object
{

    public static void main(String args[])
    {
	
	PrintStream theScreen = new PrintStream(System.out);
	Scanner theKeyboard = new Scanner(System.in);
	final String MENU = "\nPlease enter:\n" +
	    "\ta, to perform addition;\n" +
	    "\tb, to perform subtraction;\n" +
	    "\tc, to perform multiplication;\n" +
	    "\td, to perform division;\n" +
	    "\te, to perform exponentiation;\n" +
	    "\tf, to stop;\n" +
	    "--> ";
	
	double op1 = 0.0, op2 = 0.0;
	
	theScreen.println("\nWelcome to the 5-function calculator!\n");
	while(true)
	    {
	char operation = getMenuChoice(MENU, theKeyboard, 'a', 'f');
	if (operation == 'f') break;
	  
	theScreen.print("\nNow enter your operands: ");
	boolean goodInput = false;
	while(!goodInput){
	    try
		{	
		    op1 = theKeyboard.nextDouble();
		    op2 = theKeyboard.nextDouble();
		    goodInput = true;
		}
	    catch (InputMismatchException e)
		{
		    System.out.println("ERROR: entered a non-number!"
				       + "\nTry again");
		    theKeyboard.nextLine();
		}
	}
	if (operation == 'd' && op2 == 0.0)
	    {
		theScreen.println("ERROR: division by 0.");
		System.exit(1);		   
	    }
	
	double result = apply(operation, op1, op2);
	
	theScreen.println("\nThe result is " + result);
	theKeyboard.nextLine();
    }
    }
    private static char getMenuChoice(String MENU, Scanner theKeyboard,
				      char first, char last)
    {
	char choice;
	while(true)
	    {
		System.out.print(MENU);
		choice = theKeyboard.nextLine().charAt(0);
		if (choice >= first && choice <= last) break;
		System.out.println("ERROR, your choice must be between "
				    + first+ " and " + last+ ", not " 
				    + choice);
		System.out.println("Try again");
	    }
	return choice;
    }
    
    private static double apply(char operation, double op1, double op2)
    {
	switch (operation)
	    {
	    case 'a':
		return op1 + op2;
	    case 'b':
		return op1 - op2;
	    case 'c':
		return op1 * op2;
	    case 'd':
		return op1 / op2;
	    case 'e':
		return power(op1, (int) op2);
	    default:
		System.out.println("\nInvalid operation " + operation
				   + " received by calculator!\n");
	    }
	return 0.0;
    }
    
    private static double power(double base, int exp)
    {
	double result = 1.0;
	for(int i = 1; i<=exp; i++)
	    result *= base;

	return result;
    }
    
} // end of class