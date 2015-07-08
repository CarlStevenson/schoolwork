//TaxTotals.java is a driver for a simple tax computation program.
//
// Author: Carl Stevenson
// Date: 2/14/2013
// Purpose: to calculate the total cost of an item
// ********************************************************

import java.util.*; // scanner

public class TaxTotals 
{
    static Scanner sc = new Scanner(System.in); // Global scanner

    public static void main(String args[]) 
    {
	System.out.println("This program computes total costs interactively.");
	System.out.print("\nTo begin, enter the number of items: ");
	int numberOfItems = sc.nextInt();
	// new lines for turnin
	System.out.println();
	System.out.println();
	String name = new String();
	double cost = 0;
	double rate = 0;
	for (int itemNum = 1; itemNum <= numberOfItems; itemNum++)
	    {
		System.out.print("Enter the name, cost and tax rate"
				 + " for item " + itemNum + ": ");
		name = sc.next();
		cost = sc.nextDouble();
		rate = sc.nextDouble();
		// new lines for turnin
		System.out.println();
		System.out.println();
		// call computeTotalCost to compute the cost with the given
		// cost and tax rate
		double amount = Tax.computeTotalCost(cost, rate);
		// print the output to 2 points of precision
		System.out.printf("%s cost with tax is $%.2f\n\n", 
				  name, amount);

		
	    }	
    }
}