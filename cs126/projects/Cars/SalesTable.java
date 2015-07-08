// SalesTable.java
// Author:  Carl Stevenson
// Date:    3/25/2013
// Purpose: To make an object from data given to the class in the form
//          of a chart.

import java.util.*;  //  Arrays

public class SalesTable
{
	//  The data given to us will be put into this int array.
    public int[][] data;

	// Constructor
	public SalesTable(int[][] fromfile)
	{
		data = new int[10][8];
		for(int r=0;r<10;r++)
		{
			for(int c=0;c<8;c++)
			{
				data[r][c] = fromfile[r][c];
			}
		}	
	}
	public void displayTable()
	{
		//  Header
		System.out.printf("%21s"," ");
		System.out.print("Salesperson");
		System.out.printf("%21s", " ");
		System.out.println();
		System.out.print("  Model  :");
		for(int i=0; i<8; i++)
		{
			System.out.printf("%4d",(i+1));
		}
		System.out.println("  :  Totals");
		for(int i=0; i<55; i++) System.out.print("-");
		System.out.println();		

		//  Body
		for(int r=0; r<10; r++)
		{
			System.out.print("    " + r + "    :");
			for(int c=0; c<8; c++)
			{
				System.out.printf("%4d", (data[r][c]));
			}
			System.out.print("  :");
			System.out.printf("%6d", automobileSales(r));
			System.out.println();
		}
		
		//  Footer
		for(int i=0; i<55; i++) System.out.print("-");
		System.out.println();
		System.out.print(" Totals  :");
		for(int i=0; i<8; i++)
		{
			System.out.printf("%4d", salespersonSales(i));
		}
		System.out.println("\n");
	}    
	public int salespersonSales(int index)
	{
		int number = 0;
		//  For every automobile i, salesperson index has sold number cars.
		for(int i=0; i<10; i++)
		{
			number += data[i][index];
		}
		return number;
	}

	public int automobileSales(int index)
	{
		int number = 0;
		//  For every salesperson i, there have been number sold automobiles
		//  of model index
        for(int i=0; i<8; i++)
        {
            number += data[index][i];
        }
        return number;
	
	}
} 
