// Flags.java
// Program to simulate the average amount of creal boxes needed to collect all
// six flag prizes.
//
// Written by: Carl Stevenson
// Date: 8/26/13
// **************************************************************************


// import the Random class and the PrintStream class
import java.util.*;
import java.io.*;

public class Flags{

	public static void main(String[] args){
		
		// PrintStream for laziness
		PrintStream out = new PrintStream(System.out);
		// initialize the random object
		Random r = new Random();
		// Keep track of whether you've gotten the flags
		Set<Integer> track = new HashSet<Integer>();
		// keep track of how many cereal boxes
		int count = 0;
		// keep track of each draw
		int temp = 0;
		// keep track of total boxes drawn from
		double total = 0;
		// array to keep track of data points as the simulation proceeds
		double[][] points = new double[20][2];
		// simulate
		for(int i=1; i<=10000; i++){

			while(true){
			
				temp = r.nextInt(6)+1;
				track.add(temp);
				count +=1;
				if(track.size()==6){
					total += count;
					count = 0;
					track.clear();
					if(i%500==0){
						points[(i/500)-1][0] = i;
						points[(i/500)-1][1] = total/i;

					}
					break;
				}
			}
		} 

	out.println("\nConverged to: "+total/10000);
	out.println("\nAverage every 500 trials in a format to be pasted into excel:  \n");
	
	for(int i=0;i<20;i++)
		out.println(points[i][0]);
	for(int i=0;i<20;i++)
		out.println(points[i][1]);
	// output the average of the average values
	double value = 0;
	for(int i=0;i<20;i++){

		value += points[i][1];
		if(i==19)
			out.println("\nThe average value is "+value/20);
	}


	}


}
