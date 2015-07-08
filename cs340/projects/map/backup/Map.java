// Map.java
// Map.java colors a map to emulate the 4 color problem.
// Written by Carl Stevenson
// **********************************************************************

import java.util.*;
import java.io.*;

public class Map{

	public static void main(String[] args)throws FileNotFoundException{
		
		// strMap records what was read in initially
		String[][] strMap = new String[48][10];
		// intMap makes the map easier to be processed
		int[][] intMap = new int[48][10];
		// initial filling of each 2Darray
		for(int i=0;i<48;i++){
			for(int j=0;j<10;j++){
			
				strMap[i][j] = "";
				intMap[i][j] = 0;
			}
		}	
		// my key to translate strMap to intMap
		HashMap<String, Integer> strToInt = new HashMap<String, Integer>();
		strToInt.put("",0);
		// record of the color of each state
		HashMap<Integer, Integer> mapColor = new Hashmap<String, Integer>();
		Scanner sc = new Scanner(new File("usa.txt"));
		String line = "";
		int liner;
		for(int i=0;i<48;i++){
			
			liner = 0;
			line= sc.nextLine();
			Scanner temp = new Scanner(line);
			temp.useDelimiter(",");
			while(temp.hasNext()){
				
				strMap[i][liner] = temp.next();
				liner++;
				
			}
		}		
		for(int i=0;i<48;i++){
	
			strToInt.put(strMap[i][0], i);
			

		}
		for(int i=0;i<48;i++){

			for(int j=0;j<10;j++){

				intMap[i][j]= strToInt.get(strMap[i][j]);
			}
		
		// Color the map. go down the list of states and connections,
		// coloring them as appropriate.
		}
	}





}
