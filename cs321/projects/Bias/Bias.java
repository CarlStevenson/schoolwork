// Bias.java
// Bias.java demonstrates the existence of bias in the calculation of
// variance due sampling
// Written by: Carl Stevenson
// *************************************************************************

import java.util.*;

public class Bias{

	// global PrintStream and scanner for ease
	static PrintStream out= new PrintStream(System.out);
	static Scanner sc = new Scanner(System.in);	
	public static void main(String[] args){

		int pop = 0;
		double popMean = 0;
		double popDev = 0;
		out.print("\nWhat is the desired population size"
					+ " of the data set(int)? : ");
		pop = sc.nextInt();
		out.print("\nWhat is the population mean? : ");
		popMean = sc.nextDouble();
		out.print("\nWhat is the standard deviation of the population? : ");
		popDev = sc.nextDouble();
		// print the chart!
		outputs(pop, popMean, popDev);
		
	} // end main

	public static void outputs(int pop, double popMean, double popDev){

		

	}  // end outputs
} // end class
