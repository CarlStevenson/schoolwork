//Grades.java computes letter grades using the "curve" method.
//
// Begun by: Charles Hoot for Hands on Java.
// Adapted from code by: Adams, Fall 1995, Hands on C++.
// Completed by:
//
// Input(keyboard): a sequence of names and scores.
// Output: the sequence of names with corresponding letter grades,
//          based on the "curve" method of grading.
// *****************************************************************
	     
import java.util.*;  // Scanner
import java.io.*;    // PrintStream

//import DoubleArrayOps;         // average(), standardDev(), ...

class Grades extends Object
{
  static PrintStream theScreen = new PrintStream(System.out);
  static Scanner theKeyboard = new Scanner(System.in);

  public static void main(String args[]) throws FileNotFoundException
    {
      final int MAX_SCORES = 500;
//	define nameArr as an array of String objects
      String[] nameArr = new String[MAX_SCORES];
//	define scoreArr as an array of double objects
      double[] scoreArr = new double[MAX_SCORES]; 
      int scores = fillArraysFile( nameArr, scoreArr);

      //convert the array into one that just contains valid scores
      scoreArr = DoubleArrayOps.subArray(scoreArr, 0, scores-1);
      //theScreen.println(Arrays.toString(nameArr));
      //DoubleArrayOps.printArray(theScreen, scoreArr);
	theScreen.print( "\nMean score: "
			 + DoubleArrayOps.average(scoreArr)
			 + "\n"
			 + "Std. Dev: "
			 + DoubleArrayOps.standardDev(scoreArr)
			 +"\n");

//	define gradeArr as an array of character objects
	char[] gradeArr = new char[MAX_SCORES];
	gradeArr = computeLetterGrades(scoreArr);

	displayArrays(nameArr, scoreArr, gradeArr);
  }

// **********************************************************
// fillArrays fills nameArr and scoreArr from keyboard.     *
//                                                          *
// Receive: nameArr, an array of strings,                   *
//          scoreArr, an array of doubles.                  *
// Input: a sequence of names and scores,                   *
// Return: the number of scores read                        *
// Passback: the sequence of names in nameArr,              *
//           the sequence of scores in scoreArr.            *
// *********************************************************

    public static int fillArraysFile(String[] nameArr, double[] scoreArr)
    throws FileNotFoundException
    {
	theScreen.println("\nWelcome to the grade calculator.");
	theScreen.println("Written by Carl Stevenson.\n");
	
	String name;
	double score;
	int numscores = 0;
	
	theScreen.print("Enter the name of the data file: ");
	String filename = theKeyboard.next();
	Scanner input = new Scanner(new File(filename));
	
	while(true)
	    {
		
		name = input.next();
		if (name.equals("done")) break;

		score = input.nextDouble();
		
		nameArr[numscores] = name;
		scoreArr[numscores] = score;
		numscores++;
	    }
	return numscores;
    }

// **************************************************** 
// Compute letter grades, using "curve" grading.      *
// Receive: scores, a list of scores.                 *
// Return: a list of the corresponding letter grades. *
// ***************************************************

    public static char[] computeLetterGrades(double[] scoreArr)
    {
	double avg = DoubleArrayOps.average(scoreArr);
	double std = DoubleArrayOps.standardDev(scoreArr);
	
	char[] gradeArr = new char[scoreArr.length];
	
	final double F_CUT_OFF = avg - 1.5 * std;
	final double D_CUT_OFF = avg - 0.5 * std;
	final double C_CUT_OFF = avg + 0.5 * std;
	final double B_CUT_OFF = avg + 1.5 * std;
	
	for(int i = 0; i<scoreArr.length; i++)
	    {
		if(scoreArr[i] < F_CUT_OFF)
		    gradeArr[i] = 'F';
		else if (scoreArr[i] < D_CUT_OFF)
		    gradeArr[i] = 'D';
		else if (scoreArr[i] < C_CUT_OFF)
		    gradeArr[i] = 'C';
		else if (scoreArr[i] < B_CUT_OFF)
		    gradeArr[i] = 'B';
		else
		    gradeArr[i] = 'A';
	    }
	return gradeArr;
    }


	

// *****************************************************
// displayArrays displays the various arrays.          *
//                                                     *
// Receive: names, an array of strings,                *
//          scores, an array of doubles, and           *
//          grades, an array of chars.                 *
// Output: a sequence of names, scores and grades.     *
// ****************************************************

    public static void displayArrays(String[] names, double[] scores,
				     char[] grades)
    {
	theScreen.println("Name     Score   Grade");
	for(int i=0; i<scores.length; i++)
	    theScreen.printf("%-9s%3.2f     %c\n", names[i], scores[i], 
			     grades[i]);
    }

}