// Map.java
// Map.java colors a map to emulate the 4 color theorem.
// Written by Carl Stevenson
// 
// Reasoning: I chose this particular representation and search method
//            (recursive)
//            because it seemed the most like how someone would fill in a
//            map by hand.
// **********************************************************************

import java.util.*;
import java.io.*;

public class Map{

    // global variables for ease
    // strMap records what was read in initially
	public static String[][] strMap = new String[48][10];
	
	// intMap makes the map easier to be processed
	public static int[][] intMap = new int[48][10];
	
	// mapColor records the color of each state
	public static HashMap<Integer, Integer> mapColor = 
	                       new HashMap<Integer, Integer>();
	
	
	// global variables for use in the recursive coloring method
	// order keeps track of the order of the states as they are
	// processed
	public static LinkedList<Integer> order = new LinkedList<Integer>();
	// que is a queue to keep track of what states need to be colored
	public static Queue<Integer> que = new LinkedList<Integer>();
    
	
    public static void main(String[] args)throws FileNotFoundException{
		
		// put all the setup into one function to clear up main
		setUp();

		// Color the map. go down the list of states and connections,
		// coloring them as appropriate. Starts at 0 (al), will work
		// for any starting state
		start(0);
        // Output colors to screen
        theOutput();
		
	} // end main
	
	public static void setUp()throws FileNotFoundException{
	    
	    // initial filling of each 2Darray
		for(int i=0;i<48;i++){
			for(int j=0;j<10;j++){
			
				strMap[i][j] = "";
				intMap[i][j] = 0;
			}
		}	
	    // my key to translate strMap to intMap
		HashMap<String, Integer> strToInt = new HashMap<String, Integer>();
		strToInt.put("",-1);
		// pull each line from the file and assign it all in my arrays
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
		// set up my map to translate the string array &
		// initialize the mapColor map
		for(int i=0;i<48;i++){
	
			strToInt.put(strMap[i][0], i);
			mapColor.put(i,0);
			

		}
		// set up the int map that I will use more extensively to keep
		// track of the connections between the states,
		// using the strToInt translator set up before
		for(int i=0;i<48;i++){

			for(int j=0;j<10;j++){

				intMap[i][j]= strToInt.get(strMap[i][j]);
			}

	    }
    } // end setUp


    // badCounter lets start() know how many to delete off order,
    // in order to accurately go back far enough when color() fails
    // more than once in a row.

    public static int badCounter=0;
    // start decides what to color, recursively
    // try-catch's in place in case it fails so much that it tries
    // to remove everything from order
	public static void start(int state){

        order.add(state);
        if(color(state)){

            badCounter++;
            for(int i=0; i<=badCounter;i++){
                try{
                    order.removeLast();
                }catch(NoSuchElementException e){
                
                    order.add(state);
                    start(state+1);
                }
            }
            try{
                start(order.getLast());
            }catch(NoSuchElementException e){
            
                start(state+1);
            }
        }
        if(!que.isEmpty()){
            badCounter = 0;
            start(que.remove());
        }
        else{
          
            return;
        }

    } // end start

    // color() serves as a means to actually give a color value to the states,
    // as well as serving to control start() by returning whether it can
    // color the state given to it.
    public static boolean color(int state){
        
        int marker = 1;
        List<Integer> neighbors = new ArrayList<Integer>(0);
        Set<Integer> noGood = new HashSet<Integer>(0);
        noGood.add(mapColor.get(intMap[state][0]));

        while(marker>0){
            if((intMap[state][marker]) <0)break;
            else{
             
                int color = mapColor.get(intMap[state][marker]);
                noGood.add(color);
                if(color==0){
                    neighbors.add(intMap[state][marker]);
                }
                marker++;
            }        
        }
        for(int i=1; i<5; i++){
            if(noGood.contains(i));
            else{
                for(int j=0; j<neighbors.size(); j++){

                    que.add(neighbors.get(j));
                }
                mapColor.remove(state);
                mapColor.put(state, i);
                return false;
            }
        }
        return true;
    } // end color


    // print the states and their assigned color
    public static void theOutput(){

        System.out.println("\nThe map:\n");
        for(int i =0; i<48; i++){

            System.out.print(strMap[i][0]+": ");
            switch (mapColor.get(i)){
            
                case 1: System.out.println("Green");
                        break;
                case 2: System.out.println("Blue");
                        break;
                case 3: System.out.println("Red");
                        break;
                case 4: System.out.println("Brown");
                        break;
            }
        }
        System.out.println();

    } // end theOutput

} // end class
