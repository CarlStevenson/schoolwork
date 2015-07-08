// Mult.java demonstrates basic I/O in Java.
//
// Author:  Carl Stevenson
// Date:    1/17/13
// Purpose: Inputs
//
// Specification:
//   Input(keyboard): aNumber, an integer;
//   Output(screen): 2x, 4x and 8x aNumber.
//
 
import java.util.*;  // Scanner
 
public class Mult
{
	
	public static void main(String args[]) 
     {
	        Scanner theKeyboard = new Scanner(System.in);
 
   		// 0. print a message explaining the purpose of the program.
		System.out.println("\nThis program inputs a integer"
			+ "\n\tand displays 2x, 4x, and 8x its value.");
 
		// 1a. ask the user to enter an integer.
		System.out.print("\nPlease enter an integer: ");
		// 1b.declare an integer container to hold the input number
		int aNumber;
		// 1c. input an integer, storing it in variable aNumber.
		aNumber = theKeyboard.nextInt();
 
		// 2. output 2x, 4x and 8x aNumber.
		System.out.print("\n\nTwice " + aNumber + " is "
			+ 2*aNumber
			+ ",\n\tand four times is "
			+ 4*aNumber
			+ ",\n\tand eight times is "
			+ 8*aNumber
			+ "\n\n");
	}
}