
// Anthony Kapolka

// Fri Nov 18 11:36:27 EST 2011
// started by Mitchell Frear 11/17/09 CS126  
// Edited by Carl Stevenson for clarity

import java.io.*;
import java.util.*;

public class cave extends container{
	
	int      idnumber;
    tunnel[] passages = new tunnel[6];
    int      arrownumber;
    int      grenadenumber;
    String   name;
    String[] description = new String[6];

    final String[] DIRSTRING = {"North", "East", "South", "West", "Up", "Down"};

    public cave()
    {
       for (int i=0; i<6; i++)
        passages[i] = new tunnel();
    }

    public void printwarnings()
    {
    	// check for anything the player should know
  
    	// Check for each sequentially so no 
    	// hints about the relative direction
    	// of the dangers are given.

    	for (int p = 0; p < 6; p++){
    		if (passages[p].destination > 0){
    			cave myneighbor = (cave)game.C.contents.get(passages[p].destination-1);
    			if (myneighbor.has("Wumpus"))
    				gameio.putln("You smell a wumpus!");
          	}
        }

       for (int p = 0; p < 6; p++){
    	   if (passages[p].destination > 0){
    		   cave myneighbor = (cave)game.C.contents.get(passages[p].destination-1);
    		   if (myneighbor.has("Bats"))
    			   gameio.putln("You hear bats!");
    	   }
       }
    }

    public void readcave(Scanner infile){
    	//cave newCave = new cave();
    	String lineS;

        // read in cave number
    	this.idnumber = infile.nextInt();

        // read in six passages
    	for(int i = 0; i < 6; i++){
    		tunnel tt = passages[i];
    		tt.setdestination(infile.nextInt());
    	}

        // read weapon info
    	this.arrownumber   = infile.nextInt();
    	this.grenadenumber = infile.nextInt();
        for (int g=1; g<=grenadenumber; g++){ 
        	gameobject gr = new gameobject("grenade");
            putinto(gr);
        }

        lineS = infile.nextLine(); // kill newline

        // read in Name
        this.name = infile.nextLine();

        // read in the description.

        int di = 0; // description index
        lineS = infile.nextLine();
        while(lineS.charAt(0) != '^'){

        	this.description[di] = lineS;
        	di++;

        	if(infile.hasNextLine())
        		lineS = infile.nextLine() + " ";
        }

    }

    public void showcave(){
    	gameio.putln("Cave Number " + this.idnumber);
    	gameio.putln(this.name);
    	for(int i = 0; i < 6; i++){	
    		if(this.description[i] != null)
    			gameio.putln(this.description[i]);
	    }

    	if(this.arrownumber == 0)
    		gameio.putln("This cave contains no arrows.");
    	else
    		gameio.putln("Number of arrows: " + this.arrownumber);
    	if(this.grenadenumber == 0)
    		gameio.putln("This cave contains no grenades.");
    	else
    		gameio.putln("Number of grenades: " + this.grenadenumber);

    	for(int i = 0; i < 6; i++){
    		if(this.passages[i].destination !=0){
    			if(i == 0)
    				gameio.put("Passage north to ");
    			if(i == 1)
    				gameio.put("Passage east to ");
    			if(i == 2)
    				gameio.put("Passage south to ");
    			if(i == 3)
    				gameio.put("Passage west to ");
    			if(i == 4)
    				gameio.put("Passage Up to ");
    			if(i == 5)
    				gameio.put("Passage Down to ");
    			gameio.putln(this.passages[i].destination);
		    }
	    }


    	gameio.putln();
    }

    public void describecave(){
    	// POST  prints Name and description, appropriate warnings
    	gameio.sleep(1);  // sleep for 1 second
        gameio.putln();
        gameio.put(" -- ");
        gameio.put(name);
        gameio.put(" -- ");
        gameio.putln();

        int i, dir; // temporary loop vars
        for(i = 0; i < 6; i++){	
        	if(description[i] != null)
        		gameio.putln(description[i]);
	    }
/*
          while (description[i].contains('^'))
            cout << description[i++] << endl;
*/
        gameio.putln();
        gameio.putln("\tPassages lead:");
        gameio.putln();

        int exits = 0;
        for (dir=0; dir <= 5; dir++)
          {
            if (passages[dir].destination != 0){ // neighboring cave
            	gameio.put("\t\t" + DIRSTRING[dir] + ": ");
                exits++;
                if (passages[dir].travelled){
                	cave tc = 
                		(cave)game.C.contents.get(passages[dir].destination-1);
                	gameio.putln(tc.name);
                }
                else
                	gameio.putln("Unknown");
              	}
          	}
/*
          cout << endl;

          if (! exits)
            { cout << "\t\tNowhere!" << endl << endl
                   << "You better quit!" << endl;
            }
*/
        gameio.putln();
        printwarnings();

        if (contents.size() > 1) // player is there
        	gameio.put("You see: ");
        for (int co = 0; co < contents.size(); co++){ 
        	gameobject tempo = contents.get(co);
        	if (tempo.name != "Player")
        		gameio.put(tempo.name + " ");
        }
        gameio.putln();
    } // end describe()

    void movedowntunnel(int dir){ 
    	// player wants to move in direction dir
    	// this code should handle the tunnel
    	// and actually move the player...

    	// figure out cave # we are moving to.
    	/*  int dest = passages[dir].destination[0];
      	if (dest == idnumber) 
       	dest = passages[dir].destination[1];

      	gameio.put("moving to ");
      	gameio.putln(dest);
*/
    }

    void act(){
    	

    		// POST cave description may be displayed, 
    		//      warning messages may be displayed,
    		//      container act() is called.

    		if (contents.size() > 0)
    		{
    			// if player is in this cave
    			if (contents.contains(game.P))
    			{
    				if (!game.P.SkipMe)
    					describecave();
    			}

    			super.act();
    		}
    	}
    }
/*
    void move(int finish)
    {
        // add player to finish cave
        cave tempc = (cave) game.C.contents.get(finish);
	tempc.putinto((gameobject)this);
        gameio.putln("Player in cave #" + finish);
        location = finish+1;
    }

    void move(int start, int finish)
    {
	// remove player from start cave 
        cave tempc = (cave) game.C.contents.get(start);
	tempc.remove(this);
        move(finish);
        if (finish > start)
	  SkipMe = true; // avoid repeated movement
    }

    void moveindir(int direction)
    {
	// move movement to cave class
        // gameio.put("Your feet are glued to the floor! ");
        // mark up travelled passages
        curcave.passages[direction].travelled = true;
        //cave descave = curcave.passages[direction];
        //descave.passages[direction].travelled = true;
        move(location, curcave.passages[direction].destination[0]-1);
    }

*/
