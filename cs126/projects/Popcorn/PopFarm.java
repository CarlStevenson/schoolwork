// PopFarm.java is an object to keep track of statistics of popcorn farms.
//
// Date: 4/29/13
// Written by Carl Stevenson
//

public class PopFarm{

	// name stores the name of the farm as a string
	private String name;
	// acres stores the number of acres planted by the farm
	private double acres;
	// jars is the number of jars produced by the farm
	private int jars;

	// PopFarm
	// Constructor, takes the farm name, number of acres planted, and the pint
	// jars produced by the farm.
	public PopFarm(String inName, double inAcres, int inJars){

		name = inName;
		acres = inAcres;
		jars = inJars;
	} // end PopFarm

	// toString
	// Accessor, returns a string containing the name, number of acres, and
	// number of jars of the PopFarm.
	public String toString(){

		return "Farm name: "+name+" Acres planted: "+acres+
				" Pint jars produced: "+jars;
	} // end toString

	// printBar
	// Accessor, returns a string of appropriate format to be printed in 
	// the bar chart.
	public String printBar(){

		return String.format("%-29s"+getBar(), name);
	} // end printBar

	// getBar
	// Accessor, returns a string of the value of pint jars per acre produced, 
	// with one asterisk marking 250 jars. Private, because it should only be
	// used within the class.
	private String getBar(){

		double number = jars/acres;
		String bar = "";
		int check = 0;
		while(true){

			number -= 250;
			if(number <0) break;
			else if(check == 19){
				bar += "#";
				check += 1;
			} // end else if
			else{
			bar+= "*";
			check += 1;
			} // end else
		} // end while
		for(int i = check; i<20; i++){
			if(i==19) bar+="|";
			else bar+=" ";
		} // end for
		
		return bar;
	} // end getBar
} // end PopFarm

					
	


