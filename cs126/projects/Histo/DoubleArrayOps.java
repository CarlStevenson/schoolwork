//DoubleArrayOps.java defines common operations for double[].
//
// Begun by: Charles Hoot for Hands on Java.
// Adapted from code by: Adams, Fall 1995, Hands on C++.
// Completed by: Carl Stevenson
// Date: 3/19/2013
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
    // swaps a[i] with a[j]. (from assignment sheet)
    public static void swap(double[] a, int i, int j)
    {
	if (i != j)
	{
   	    double temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
    }
    // Places the elements of the given array into sorted order
    // using the selection sort algorithm.
    // post: array is in sorted (nondecreasing) order
    // from assignment sheet
    public static void selectionSort(double[] a)
    {
	for (int i = 0; i< a.length - 1; i++)
	{
	    // find index of smallest element
	    int smallest = i;
	    for (int j = i + 1; j < a.length; j++)
	    {
		if (a[j] < a[smallest])
	   	    smallest = j;
	    }
	    swap(a, i, smallest);
	}
    }
	
    // returns the max value from an array
    public static double max(double[] a)
    {
	double max = a[0];  // start with first value
	for (int i =0; i< a.length; i++)
	{
	// go through each value in the array to see if it is smaller than
	// the current maximum
	    if (a[i] > max)
		max = a[i];
	}
	return max;
    }
	
    // returns the min value from an array
    public static double min(double[] a)
    {
	double min = a[0];  // start with first value
	for(int i = 0; i< a.length; i++)
	{
	// go through each value in the array to see if it is smaller than
	// the current minimum
 	    if (a[i] < min)
		min = a[i];
	}
	return min;
    }
}
