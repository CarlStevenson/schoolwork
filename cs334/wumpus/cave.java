
// Grenades added by Mason McIntyre Fall '12
// Lighting by Kevin Splane Fall '12
// Anthony Kapolka
// Fri Nov 18 11:36:27 EST 2011
// started by Mitchell Frear 11/17/09 CS126  

import java.io.*;
import java.util.*;

public class cave extends container
{
    int      idnumber;
    tunnel[] passages = new tunnel[6];
    int      arrownumber;
    int      disfromwumpus; // distributed local variable used by wumpus.java
                            // added by Tyler Copeland

    boolean  haslight = false; // is cave lit? 
    int	     torchnumber;      // torches are supposed to be an object?
			       // why is this here?

    String[] description = new String[6];

    final String[] DIRSTRING = {"North", "East", "South", "West", "Up", "Down"};

    public int getrandomtunnel()
    // returns a random tunnel (that leads somewhere!)
    {
       // pick number 0-5
       int i = game.R.nextInt(6);
 
       // while no tunnel with that number  
       //    pick number 0-5 - 
       // looking at destination ok here because it leads *somewhere*
       while ((this.passages[i] == null) || (this.passages[i].destination == 0))
        {
           i = game.R.nextInt(6);
        }
  
       // return that tunnel number
       return i; 
    }

    public cave()
    {
       for (int i=0; i<6; i++)
        passages[i] = new tunnel();  // tunnels lead nowhere
    }

    public void printwarnings()
    {
       // check for anything the player should know
  
       // Check for each sequentially so no 
       // hints about the relative direction
       // of the dangers are given.

       for (int p = 0; p < 6; p++)
        {
          if ((passages[p] != null) && (passages[p].destination > 0))
          {

          cave myneighbor = (cave)game.C.contents.get(passages[p].leadsto(idnumber)-1);
          if (myneighbor.has("Wumpus"))
            gameio.putln("You smell a wumpus!");
          }
        }

/*
        for (int p = 0; p < 6; p++)
        // warning for all grenades - should just be for activated grenades
        {
          if ((passages[p] != null) && (passages[p].destination > 0))
          {
          cave myneighbor = (cave)game.C.contents.get(passages[p].leadsto(idnumber)-1);
            if(myneighbor.has("Grenade"))
              gameio.putln("You hear a ticking sound!");

          }
        }
*/

       for (int p = 0; p < 6; p++)
        {
          if ((passages[p] != null) && (passages[p].destination > 0))
          {
          cave myneighbor = (cave)game.C.contents.get(passages[p].leadsto(idnumber)-1);
          if (myneighbor.has("Bats"))
            gameio.putln("You hear bats!");
          }
        }

       for (int p = 0; p < 6; p++)
        {
          if ((passages[p] != null) && (passages[p].destination > 0))
          {
          if (passages[p].haspit)
            gameio.putln("You feel a draft!");
          else
          {
            // missing code, like that awkward conversational lull
          }
         }

        }
    }

    public void readcave(Scanner infile)
    {


	String lineS;


        // read in cave number
	this.idnumber = infile.nextInt();

        // read in six passages
	for(int i = 0; i < 6; i++)
	  {
             // for each, if tunnel !=0 need to 
             //     if destination > idnumber create a tunnel
             //        set source, destination
             //     else
             //        set to existing tunnel
	     int dest = infile.nextInt();
             
             if  (dest == 0) 
                this.passages[i] = new tunnel();  // no tunnel here
             else {
               if  (dest > idnumber)  // actually create the tunnel
                 {
                   tunnel tt = new tunnel(idnumber);
	           tt.setdestination(dest);
                   this.passages[i] = tt;
                   game.adjmatrix[idnumber-1][dest-1] = tt;
                 }
               else 
                 {
                    // force graph bidirectional by sharing tunnels
                    // dest is lower number, so tunnel already created
                    // but what if only one direction, high to low
                    //   then the passage not created by the code above

//System.out.println(idnumber + "-->" + dest);
                   this.passages[i] =   // flip to find the cave
                     game.adjmatrix[dest-1][idnumber-1];
     	         }  
             }
	  }

        // read weapon info
	this.arrownumber   = infile.nextInt();
	int grenadenumber = infile.nextInt();
        for (int g=0; g<=grenadenumber; g++)   // MAKEONE
          { grenade gr = new grenade();
            this.putinto((gameobject)gr);
          }

        //read in torches       
        this.torchnumber   = infile.nextInt();

	// be nice to permanently light some of the caves 

        lineS = infile.nextLine(); // kill newline

	// read in Name
        this.name = infile.nextLine();

        // read in the description.

	int di = 0; // description index
        lineS = infile.nextLine();
	while(lineS.charAt(0) != '^')
	{

		this.description[di] = lineS;
		di++;

	    if(infile.hasNextLine())
 		    lineS = infile.nextLine() + " ";
	}

    }

    public void showcave(){
	gameio.putln("Cave Number " + this.idnumber);
	gameio.putln(this.name);
	for(int i = 0; i < 6; i++)
            {	
		if(this.description[i] != null)
		    gameio.putln(this.description[i]);
	    }

	if(this.arrownumber == 0)
	    gameio.putln("This cave contains no arrows.");
	else
	    gameio.putln("Number of arrows: " + this.arrownumber);

        if(this.torchnumber == 1){

             gameio.putln("You found a torch!");
             torch.torchnumber();}

	for(int i = 0; i < 6; i++)
            {
		if(this.passages[i].destination !=0)
		    {
			if(i == 0)
			    gameio.put("Passage north to ");
			if(i == 1)
			    gameio.put("Passage east to ");
			if(i == 2)
			    gameio.put("Passage south to ");
			if(i == 3)
			    gameio.put("Passage west to ");
			if(i == 4)
			    gameio.put("Passage up to ");
			if(i == 5)
			    gameio.put("Passage down to ");
                        // why this next line?
			gameio.putln(this.passages[i].leadsto(idnumber));
		    }
	    }


	gameio.putln();
    }

    public void describecave()
    {       // POST  prints Name and description, appropriate warnings
        // this function only works for the PLAYER P

        // the way this is implemented a cave's lighting flickers on and off
        // would be better to decide once in the constructor. 
        int randomnumber = game.R.nextInt(100); 

        if(torch.islit() || randomnumber <= 100){  // no more darkness

	gameio.sleep(1);  // sleep for 1 second
        gameio.putln();
        gameio.put(" -- ");
        gameio.put(name);
        gameio.put(" -- ");
        gameio.putln();

        int i, dir; // temporary loop vars
	for(i = 0; i < 6; i++)
            {	
		if(description[i] != null)
		    gameio.putln(description[i]);
	    }

        gameio.putln();
        gameio.putln("\tPassages lead:");
        gameio.putln();

        int exits = 0;
        for (dir=0; dir <= 5; dir++)
          {
            if ((passages[dir] != null) && (passages[dir].destination != 0)) 
                // there is a neighboring cave
              { gameio.put("\t\t" + DIRSTRING[dir] + ": ");
                exits++;
                if (game.P.cavemap[passages[dir].source]
                     [passages[dir].destination] == true)
                {
                 cave tc = 
                   (cave)game.C.contents.get(passages[dir].leadsto(idnumber)-1);
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
}

        else{

                gameio.putln("Its to dark to see!");}

        gameio.putln();
	printwarnings();

	// how come I can see the stuff below even when it is dark?
        if (contents.size() > 1) // something besides the player is there
          gameio.put("You see: ");
        for (int co = 0; co < contents.size(); co++)
         { 
          gameobject tempo = contents.get(co);
          if (tempo.name != "Player")
            gameio.put(tempo.name + " ");
         }
        gameio.putln();
    } // end describe()

    void movedowntunnel(int dir)
    { // player wants to move in direction dir
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

    void act()
    {

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
