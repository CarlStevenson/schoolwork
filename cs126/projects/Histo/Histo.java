// File:    Histo.java
// Author:  Carl Stevenson
// Date:    3/19/2013
// Purpose: To use classes and arrays to analyze data.

import java.util.*;  // Scanner
import java.io.*;    // PrintStream

public class Histo
{
    static PrintStream out = new PrintStream(System.out); // easier printing
    public static void main(String[] args) throws FileNotFoundException
    {
	// intro
	out.println();
	out.println("Program to print a histogram of a list of scores.");
	out.println("It also produces high, low, and average score");
	out.println("along with the standard deviation.");
	out.println("Written by Carl Stevenson.\n");
		
	Scanner infile = getInfo();
		
	// get the needed arrays
	String[] names = new String[100]; // 100 should be more than enough
	double[] scores = new double[100];  
	int count = 0; // used for placing values in the array
	while(true)
	{
	    String check = infile.next();
	    if (check.equals("done")) break;
	    names[count] = check;
     	    scores[count] = infile.nextDouble();
	    count++;
	}
		
	double[] apscores = DoubleArrayOps.subArray(scores, 0, count-1);

	displayBasic(names, apscores);
	printHist(apscores);
		
    }
    public static Scanner getInfo() throws FileNotFoundException
    {
	Scanner sc = new Scanner(System.in);
	out.print("Enter the name of the scores file: ");
	String name = sc.next();
	Scanner infile = new Scanner(new File(name));
	out.println();
	out.println();
	return infile;
    }
    public static void displayBasic(String[] names, double[] scores)
    {
	double worst = DoubleArrayOps.min(scores);
	double best = DoubleArrayOps.max(scores);
	double avg = DoubleArrayOps.average(scores);
	double stdv = DoubleArrayOps.standardDev(scores);
		
	out.printf("Worst score:       %6.2f\n", worst);
	out.printf("Best score:        %6.2f\n", best);
	out.printf("Average score:     %6.2f\n", avg);
	out.printf("Standard deviation:%6.2f\n\n", stdv);
	out.println("Name              Score");
	out.println("====              =====");
	out.println();
	for(int i = 0; i< names.length; i++)
	{
	    if (names[i] == null) break;
	    out.printf("%-17s %-5.2f\n",names[i], scores[i]);
	}
	out.println();
    }
    public static void printHist(double[]scores)
    {
	for(int i=(int)DoubleArrayOps.min(scores);
		i<=(int)DoubleArrayOps.max(scores);
		i++)
	{
	    out.print(i + ": ");
	    for(int j = 0; j<scores.length;j++)
		if (scores[j]==i)
		    out.print('*');
	    out.println();
	}
	out.println();
    }
}
