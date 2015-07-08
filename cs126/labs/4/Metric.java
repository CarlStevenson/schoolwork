//Metric.java contains the definitions of module metric's operations.
//
// Author: Carl Stevenson
// Date: 2/7/13
// Purpose: To develop a simple Java package
//
// *****************************************************************

public class Metric extends Object
{

//***********************************************************
// feetToMeters() converts feet into meters.                *
// Receive: feet, the (real) number of feet to be converted.*
// Precondition: feet >= 0.                                 *
// Return: The equivalent number of meters.                 *
//***********************************************************

// define feetToMeters() below here
    public static double feetToMeters (double feet)
    {
	if(feet < 0.0)
	    throw new IllegalArgumentException("in feetToMeters, the argument"
					       + " must be > 0, not " + feet);
	double meters = (feet * .3048);
	return meters;
    }
    public static double ouncesToGrams(double ounces)
    {
	if(ounces < 0.0)
	    throw new IllegalArgumentException("in ouncesToGrams, the argument"
					       + " must be > 0, not " + ounces);
	double grams = ounces * 28.349523;
	return grams;
    }
    public static double poundsToKilograms(double pounds)
    {
	if(pounds < 0.0)
	    throw new IllegalArgumentException("in poundsToKilograms,"
					       + "the argument must be > 0,"
					       + "not " + pounds);
	double kilograms = pounds * .3732417;
	return kilograms;
    }
    public static double tonsToKilograms(double tons)
    {
	if(tons < 0.0)
	    throw new IllegalArgumentException("in tonsToKilograms,"
					       + "the argument must be > 0,"
					       + "not " + tons);
	double kilograms = tons * 907.18474;
	return kilograms;
    }
}