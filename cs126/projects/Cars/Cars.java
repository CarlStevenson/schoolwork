// Cars.java
// Author:  Carl Stevenson
// Date: 3/25/2013
// Purpose: To take a file from the command line, process it, and drive
//          SalesTable.java

import java.util.*;  //  Scanner
import java.io.*;    //  File

public class Cars
{
	public static void main(String[] args)
			       throws FileNotFoundException
	{
		String filename = args[0];
		//  We know the amount of data that will be given beforehand,
        //  making retrieving the data easy.
        int[][] data = new int[10][8];
        Scanner file = new Scanner(new File(filename));
        for (int r=0; r<10; r++)
        {
            for(int c=0; c<8; c++)
            {
                data[r][c] = file.nextInt();
            }
        }
		SalesTable table = new SalesTable(data);

		//  Intro
		System.out.println();
		System.out.println("Program to output a month report of car sales.");
		System.out.println("Written by Carl Stevenson.");
		System.out.println();
		table.displayTable();
	}
}
