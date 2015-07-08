//Tax.java defines tax computation functions.
//
// Author: Carl Stevenson
// Date: 2/14/2013
// Purpose: to provide a method to compute the total cost of an item
//
// ************************************************************

public class Tax extends Object
{

    public static double computeTotalCost(double cost, double rate)

    {
	final double MIN_LUXURY = 100.0;
	final double LUXURY_RATE = 10.0;
	double totalCost;
	// make sure the variables are not negative
	if((cost < 0) || (rate < 0))
	    throw new IllegalArgumentException("\nThe values for cost("
					       + cost + ") and rate("
					       + rate + ") must be"
					       + " nonnegative.");
	// compute total cost without luxury tax
	if (cost <= MIN_LUXURY)
	    {
		totalCost = cost + (cost*rate/100);
	    }
	// compute total cost if luxury tax applies
	else
	    {
		double regularCost = cost + (cost*rate/100);
		double luxuryBase = cost - MIN_LUXURY;
		double luxurySurcharge = luxuryBase * LUXURY_RATE/100;
		totalCost = regularCost + luxurySurcharge;
	    }
	return totalCost;
    }

}