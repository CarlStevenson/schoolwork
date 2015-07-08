// Metric.java contains the definitions of module metric's operations.
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
	// make sure a valid number is entered
	if(feet < 0.0)
	    throw new IllegalArgumentException("in feetToMeters, the argument"
					       + " must be > 0, not " + feet);
	// conversion
	double meters = (feet * .3048);
	return meters;
    }
    // ouncesToGrams converts the given weight in ounces 
    // to the equivalent weight in grams.
    public static double ouncesToGrams(double ounces)
    {
	// make sure a valid number is entered
	if(ounces < 0.0)
	    throw new IllegalArgumentException("in ouncesToGrams, the argument"
					       + " must be > 0, not " + ounces);
	// conversion
	double grams = ounces * 28.349523;
	return grams;
    }
    // poundsToKilograms converts the given weight in pounds
    // to the equivalent weight in kilograms.
    public static double poundsToKilograms(double pounds)
    {
	// make sure a valid number is entered
	if(pounds < 0.0)
	    throw new IllegalArgumentException("in poundsToKilograms,"
					       + "the argument must be > 0,"
					       + "not " + pounds);
	// conversion
	double kilograms = pounds * .3732417;
	return kilograms;
    }
    // tonsToKilograms converts the given weight in tons
    // to the equivalent weight in Kilograms
    public static double tonsToKilograms(double tons)
    {
	// make sure a valid number is entered
	if(tons < 0.0)
	    throw new IllegalArgumentException("in tonsToKilograms,"
					       + "the argument must be > 0,"
					       + "not " + tons);
	// conversion
	double kilograms = tons * 907.18474;
	return kilograms;
    }
}