// Anthony Kapolka

// Fri Nov 18 12:24:32 EST 2011
// started by Mitchell Frear 11/17/09 CS126 
// Edited by Carl Stevenson for clarity

import java.io.*;
import java.util.*;

public class cavern extends container
{


    public cavern()
    {
        super(game.MAXCAVE); 
        // call the container constructor to allocate contents
        readcavern("cavedata");
    }

    void readcavern(String infileName)
    {
    	try{
    		File infile = new File(infileName);
    		Scanner F = new Scanner(infile);
    		for(int i = 1; i <= game.MAXCAVE; i++){

    			cave tempcave = new cave();
    			tempcave.readcave(F);
    			contents.add(tempcave);
    		}
    	}catch(IOException e){
    		System.err.println(e);
    		System.exit(1); //bad
    	}
    }    

    void showcavern()
    {
    	for(int i = 0; i < contents.size(); i++){
    		gameio.putln("showing cave " + i);
    		cave tempcave = (cave)contents.get(i);
    		tempcave.showcave();
    	}
    }

    void act()
    {
    	gameio.putln("Starting the Game");
        while (true){
        	super.act();
        }
    }
}
