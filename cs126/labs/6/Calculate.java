//Calculate.java provides a simple 4-function calculator.
//
// Adapted by: Charles Hoot, for Hands On Java.
// From: Joel Adams, for Hands On C++.
// Completed by: Carl Stevenson
// Date: 2/21/13
// Purpose: To create a simple calculator to test switches and error exceptions
//
// Specification:
// input(keyboard): a char, stored in operator;
//                  two reals, stored in op1 and op2;
// output(screen): the result of the expression (op1 operator op2).
// **************************************************************

import java.util.*;  // Scanner
import java.io.*;    // PrintStream

class Calculate extends Object
{
  static PrintStream theScreen = new PrintStream(System.out);
  static Scanner theKeyboard = new Scanner(System.in);

    static final String MENU = ("\nPlease enter:\n" +
                      	"\t+, to perform addition;\n" +
	              		"\t-, to perform subtraction;\n" +
                      	"\t*, to perform multiplication;\n" +
                      	"\t/, to perform division;\n" +
			"\t<enter>, to exit;\n" +
				"--> ");

  public static void main(String args[])
  {


  	theScreen.println("\nWelcome to the 4-function calculator!\n");
	while (true)
	    {
		theScreen.println(MENU);
		char operation;
		String line = theKeyboard.nextLine();
		//operation = theKeyboard.nextLine().charAt(0);
		if (line.length() == 0) break;
		operation = line.charAt(0);
		if (operation != '+' && operation != '-' &&
		    operation != '*' && operation != '/')
		    {
			break;
		    }
		
		theScreen.print("\nNow enter your operands: ");
		
		double op1, op2;
		op1 = theKeyboard.nextDouble();
		op2 = theKeyboard.nextDouble();
		theKeyboard.nextLine();
		if (operation == '/' && op2 == 0)
		    {
			theScreen.println("ERROR: division by 0.");
			
			
		    }
		
		double result = apply(operation, op1, op2);
		
		theScreen.println("\n" + op1 + operation + op2
				  + " = " + result);
	    }
  }

    public static double apply(char operation, double op1, double op2)
    {
	double result = 0;

	switch (operation)
	    {
	    case '+':

		result = op1 + op2;
		break;

	    case '-':
	    
		result = op1 - op2;
		break;

	    case '*':
	    
		result = op1 * op2;
		break;

	    case '/':
	    
		result = op1 / op2;
		break;

	    default:
		throw new IllegalArgumentException("\n\nIn apply, operator must"
					       + " be +, -, *, /, not " 
					       + operation + "\n");
	    }
	return result;
    }


}