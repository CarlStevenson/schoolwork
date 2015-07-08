// Anthony Kapolka
// Fri Nov 18 12:27:02 EST 2011
// Edited by Carl Stevenson for clarity

import java.util.*;
 

// need to place random pits -> 

class game{
	public static final int MAXCAVE = 125;  // "constant"
    public static final int NUMBATS = 3;  
    static cavern C = new cavern();
    static player P = new player();
    static wumpus W = new wumpus();
    static Random R = new Random();

    public static void main(String[] args){

    	cave tempc; 

    	gameio.putln("Hello Wumpus adventurer.");

    	// place bats in cave
    	for (int b=1; b<=NUMBATS; b++){
    		
    		gameobject bats = new gameobject("Bats");
    		tempc = (cave) C.contents.get(R.nextInt(MAXCAVE));
    		tempc.putinto(bats); 
    		gameio.put("Bats placed in cave ");
    		gameio.putln(tempc.name);
    	}

    	// extra bat removed

    	tempc = (cave) C.contents.get(0);
    	// place the Wumpus into the cave, and update it with its position
    	tempc.putinto(W);
        W.setposition(tempc.idnumber);
        gameio.put("Wumpus placed in cave ");
        gameio.putln(tempc.name);

        int startcave = 5; // R.nextInt(MAXCAVE);
        P.move(startcave);

        // play game
        C.act();

    }
}
