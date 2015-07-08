// Popcorn.java takes a list of popcorn farms, their acreage and production,
// and outputs a bar chart of the respective farms, using the PopFarm class.
//
// Date: 4/29/13
// Written by Carl Stevenson
//

import java.util.*; // For Scanner
import java.io.*;	// For PrintStream

public class Popcorn{

	// global scanner for ease
	static Scanner sc;
	// global printstream for ease
	static PrintStream out = new PrintStream(System.out);
	// global list of farms for ease
	static List<PopFarm> farms = new ArrayList<PopFarm>();

	public static void main(String[] args) throws FileNotFoundException{
		
		// intro text
		out.println("\nProgram to produce a bar chart of popcorn production.");
		out.println("Written by Carl Stevenson.\n");

		read(args); // read the data from the input file
		toScreen(); // print the bar chart
	} // end main

	// read()
	// reads in the input file
	public static void read(String[] args) throws FileNotFoundException{

		// reject with a message if the user ran the program without
		// the name of the file they wanted to use on the command line	
		if(args.length == 0){
			out.println("\nYou must enter the name of the file you wish to"
						+" read in on the command line!"
						+"\nEx. (java Popcorn file.txt)\n");
			System.exit(1);
		} // end if
		sc = new Scanner(new File(args[0]));
		while(sc.hasNext()){

			String name = sc.next();
			while(!sc.hasNextDouble()) name+=" "+sc.next();
			name = name.substring(0, name.length()-1);
			
			if(name.length() >29){

				out.println("\nERROR: The name for "+name+" is over 29 "
							+"characters,"
							+"which is incompatable with this program. "
							+"Please fix this in your input file.\n"
							+"Continuing with the rest of the input.\n");
				// throw away the values for this bad input				
				if(sc.hasNextDouble()) sc.nextDouble();
				if(sc.hasNextInt())sc.nextInt();
			} // end if
			else{
				double acres = sc.nextDouble();
				int jars = sc.nextInt();
				farms.add(new PopFarm(name, acres, jars));
			} // end if
		} // end while
	} // end read

	// toScreen
	// prints the chart to the screen, using a chart set up here, and
	// methods in PopFarm
	public static void toScreen(){

		// chart header		
		out.printf("%28s", "Pop CoOp");
		out.printf("\n%-29sProduction in", "Farm name");	
		out.printf("\n%29sThousands of", "");
		out.printf("\n%29sPint Jars per Acre", "");
		out.printf("\n%29s   1   2   3   4   5   6", "");
		out.printf("\n%29s---|---|---|---|---|---|---\n", "");
		
		// loop to print out each farm's info
		for(int i=0;i<farms.size();i++){

			out.println(farms.get(i).printBar());
		} // end for
		out.println();
	} // end toScreen
} // end Popcorn

