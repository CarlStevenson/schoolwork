//Fraction.java contains the definitions of the Fraction operations,
//  as well as any auxillary functions they utilize.
//
//  Begun by: Charles Hoot, for Hands On Java.
//  Adapted from code by: Joel C. Adams, for Hands On C++.
//  Completed by: Carl Stevenson
//  Date: 4/4/13
// ***************************************************************

import java.util.*;  // Scanner
import java.io.*;    // PrintStream

public class Fraction extends Object{

    private int myNumerator;
    private int myDenominator;
    //start placing attributes and methods here
    
    
    //********************************************************
    // Fraction default-value constructor.                   *
    // Precondition: A Fraction object has been declared.    *
    // Postcondition: myNumerator == 0 && myDenominator == 1.*
    //********************************************************
    public Fraction(){
	
		setFraction(0,1);
    }

    
    //********************************************************
    // Fraction explicit-value constructor.                  *
    // Precondition: A Fraction object has been declared.    *
    // Receive: numerator, denominator, two integers.        *
    // Postcondition: myNumerator == numerator &&            *
    //                 myDenominator == denominator.         *
    //********************************************************
    public Fraction(int numerator, int denominator){
    
		setFraction(numerator, denominator);
    }

    
    //********************************************************
    // Numerator accessor                                    *
    // Return: the value of myNumerator.                     *
    //********************************************************
    public int getNumerator(){

		return myNumerator;
    }
    
    private void setFraction(int num, int denom){

		if(denom ==0)
	    	throw new IllegalArgumentException("Denominator cannot be 0!");
		myNumerator = num;
		myDenominator = denom;
		simplify();
    }    


    //********************************************************
    // Denominator accessor                                  *
    // Return: the value of myDenominator.                   *
    //********************************************************
    public int getDenominator(){

		return myDenominator;
    }
    
    
    //********************************************************
    // Input function member                                 *
    // Precondition: in contains a Fraction n/d.             *
    // Input: in, a Scanner .                                *
    // Passback: in, with fraction read                      *
    // Postcondition: myNumerator == n && myDenominator == d.*
    //********************************************************
    public void read(Scanner sc){

		String fract = sc.next();  // "3/4"
		StringTokenizer parser = new StringTokenizer(fract, "/");
		if(parser.countTokens() != 2)
	    	throw new IllegalArgumentException("Bad format for fraction!");
		String first = parser.nextToken();
		int num = Integer.parseInt(first);
		String second = parser.nextToken();
		int denom = Integer.parseInt(second);
		setFraction(num, denom);
    }
    

	// Begin all arithmetic methods


    //********************************************************
    // multiplication                                        *
    // Receive: rightOperand, a Fraction object.             *
    // Return: result, the product of the receiver of        *
    //          this message and rightOperand.               *
    //********************************************************
    public Fraction times(Fraction rightOperand){
		// top times top, and bottom times bottom
		int num = myNumerator * rightOperand.getNumerator();
		int denom = myDenominator * rightOperand.getDenominator();
	
		return new Fraction(num, denom);
    }

	public Fraction plus(Fraction rightOperand){

		// make a common denominator, then alter the numerator to match,
		// then add them
		int denom = myDenominator*rightOperand.getDenominator();

		int num = (myNumerator * (denom/myDenominator)) +
					(rightOperand.getNumerator() * 
					(denom/rightOperand.getDenominator()));
		
		return new Fraction (num, denom);
	}
    
	public Fraction minus(Fraction rightOperand){

		// make a common denominator, then alter the numerator to match,
		// then subtract them
		int denom = myDenominator*rightOperand.getDenominator();

		int num = (myNumerator * (denom/myDenominator)) -
					(rightOperand.getNumerator() *
					(denom/rightOperand.getDenominator()));

		return new Fraction(num, denom);
	}

	public Fraction over(Fraction rightOperand){

		// multiply by the inverse of the second fraction
		Fraction inverse = new Fraction(rightOperand.getDenominator(),
										rightOperand.getNumerator());
		return this.times(inverse);
	}
	//----------------------------------------------------
	// begin the comparision methods, figured by 
	// making a common denominator, then comparing the altered numerators
	//----------------------------------------------------
	public boolean isLessThan(Fraction rightOperand){

		int denom = myDenominator*rightOperand.getDenominator();

		return (myNumerator*(denom/myDenominator)) <
				(rightOperand.getNumerator() *
				(denom/rightOperand.getDenominator()));
	}

	public boolean isLessOrEqualTo(Fraction rightOperand){

		int denom = myDenominator*rightOperand.getDenominator();

		return (myNumerator*(denom/myDenominator)) <=
				(rightOperand.getNumerator() *
				(denom/rightOperand.getDenominator()));	
	}

	public boolean isEqualTo(Fraction rightOperand){

		int denom = myDenominator*rightOperand.getDenominator();

		return (myNumerator*(denom/myDenominator)) ==
				(rightOperand.getNumerator() *
				(denom/rightOperand.getDenominator()));
	}

	public boolean isGreaterThan(Fraction rightOperand){
	
		int denom = myDenominator*rightOperand.getDenominator();

        return (myNumerator*(denom/myDenominator)) >
                (rightOperand.getNumerator() *
                (denom/rightOperand.getDenominator()));
	}

	public boolean isGreaterOrEqualTo(Fraction rightOperand){

		int denom = myDenominator*rightOperand.getDenominator();

        return (myNumerator*(denom/myDenominator)) >=
                (rightOperand.getNumerator() *
                (denom/rightOperand.getDenominator()));
	}

    //********************************************************
    // Simplify a Fraction from improper to proper.          *
    // Postcondition: the Fraction receiving this message    *
    //                 is a proper fraction.                 *
    //********************************************************
    private void simplify(){

		int gcd;
		while(true){   
	    	gcd = greatestCommonDivisor(myNumerator, myDenominator);
	    	if (gcd ==1) return;
	    	myNumerator = myNumerator / gcd;
	    	myDenominator = myDenominator / gcd;
		}
    }
    
    
    //********************************************************
    // override super classes toString                       *
    // Return: String equivalent of fraction                 *
    //********************************************************
    public String toString(){

		return myNumerator + "/" + myDenominator;
    }

    public void print(){

		System.out.println(toString());
    }
    
    
    
    // **********************************************************
    // greatestCommonDivisor finds the greatest common divisor  *
    // of two integers, using Euclid's (recursive) algorithm.   *
    //                                                          *
    //  Receive: alpha, beta, two integers.                     *
    //  Return: the greatest common divisor of alpha and beta.  *
    //***********************************************************
    
    private static int greatestCommonDivisor(int alpha, int beta){

		alpha = Math.abs(alpha);  // take absolute values of operands
		beta = Math.abs(beta);
	
		if (beta == 0)       // base case
	    	return alpha;
		else{                // induction step
			int remainder = alpha % beta;
		
			return greatestCommonDivisor(beta, remainder);
	    }
    }
    
}
