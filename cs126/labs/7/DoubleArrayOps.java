//DoubleArrayOps.java defines common operations for double[].
//
// Begun by: Charles Hoot for Hands on Java.
// Adapted from code by: Adams, Fall 1995, Hands on C++.
// Completed by:
// Date:
// *****************************************************************

import java.io.*;    // PrintStream

public class DoubleArrayOps
{

	// *****************************************************
	// subArray returns a sub sequence from an array       *
	//                                                     *
	// Receive: data, an array of doubles,                 *
	//          start, an integer, and                     *
	//          stop, an integer.                          *
	// Preconditions: stop >= start                        *
	//                start >= 0                           *
	//                stop < data.length                   *
	// Output: a sub sequence of the original array.       *
	//******************************************************
	public static double[] subArray(double data[], int start, int stop) 
	{
		double newData[] = new double[stop-start+1];
		
		int storeAt = 0;
		for (int i = start; i<= stop; i++)
		    {	
		    newData[storeAt] = data[i];
		    storeAt++;
		    }
		return newData;
	}
	
	
	//**********************************************************
	//  output values in the array                             *
	// Receive: out, a PrintStream                                  *
	//           data, the double[] to be displayed.           *
	// PRE: data.size() == the number of values .              *
	//**********************************************************
	public static void printArray(PrintStream out, double data[]) 
	{
		for (int i = 0; i< data.length; i++)
			out.print(data[i] + " ");
	}	
	
	
	//****************************************
	// Compute the mean of a list of numbers.*
	// Receive: data, the list of numbers.   *
	// PRE: data.size() > 0.                 *
	// Return: the mean value.               *
	//****************************************
    public static double average(double[] data)
    {
	double sum = 0.0;
	for(int i = 0; i< data.length; i++)
		sum += data[i];
	return sum/data.length;
    }
	
	//***************************************************
	// Compute standard deviation of a list of numbers. *
	// Receive: data, the list of numbers.              *
	// PRE: data.size() > 0.                            *
	// Return: the standard deviation of the list.      *
	//***************************************************
    public static double standardDev(double[] data)
    {
	double term;
	double avg = average(data);
	double sumSqTerms = 0.0;
	for(int i = 0; i< data.length; i++)
	    {
		term = data[i] - avg;
		sumSqTerms += (term * term);
	    }
	return Math.sqrt(sumSqTerms/data.length);
    }


}