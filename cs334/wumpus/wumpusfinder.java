// Mason McIntyre
// Fall 2013

import java.util.*;

class wumpusfinder extends gameobject
{
  public int location;   // 1 to MAXCAVE
                         // is this updated as the player moves carrying it?
                         // no!  I should just use game.P.location
  
  Queue<Integer> tocheck;
  Stack<Integer> beento;
  
  int caveid;
  int backpath[];

  wumpusfinder()
  {
    name = "Wumpusfinder";
  }

  void findpath(int wumpuscave)
  {
gameio.putln("  finding path...");
    cave currcave;// = game.wumpuscave;
    cave prevcave;

    tocheck = new LinkedList<Integer>();
    beento = new Stack<Integer>();
    tocheck.add(wumpuscave);
    beento.push(wumpuscave);

    currcave = (cave)game.C.contents.get(tocheck.remove());
    prevcave = currcave;


    while(!checkneighbors(currcave))
      {
        // gameio.putln(prevcave+ "  " +currcave);
	prevcave = currcave;
	currcave = (cave)game.C.contents.get(tocheck.remove());
	beento.push(prevcave.idnumber-1);
	
      }

    /*
    while(!beento.empty())
      {
	caveid = beento.pop();
	gameio.putln(caveid);
      }
    */
    //caveid = beento.pop();
    //caveid = beento.pop();
    //prevcave = (cave)game.C.contents.get(caveid);

    // want to head to prevcave.name
    // need to figure out that direction

    // look at all tunnels near player
   int d; // direction to go
   for (d = 0; d<6; d++)
     if ((currcave.passages[d] == null)  // there is a tunnel
        && (currcave.passages[d].leadsto(currcave.idnumber) == prevcave.idnumber))
        break;  // d is set to correct tunnel #

    // local copy from cave.java - fix this!
    String[] DIRSTRING = {"North", "East", "South", "West", "Up", "Down", "Unknown"};

    if(currcave.has("Wumpus"))
      // gameio.putln("The Wumpus is next to you in cave: " +prevcave.name);
      gameio.putln("The Wumpus is next to you!  Fire " + DIRSTRING[d]);
    else
      // gameio.putln("Head to cave: " +prevcave.name);
      gameio.putln("Head " + DIRSTRING[d] + "!");

    
  }

  boolean checkneighbors(cave currcave)
  {
    boolean hasplayer = false;  
    cave tempc;

    for(int i = 0; i < 6; i++)
      {
	if ((currcave.passages[i] != null)
	      && (currcave.passages[i].destination >= 0))
	  {
	    //check if seen
	    if(beento.search(currcave.passages[i].destination) == -1)
	      {
		if(currcave.passages[i].destination == game.P.location)
		  {
		    hasplayer = true;
		    return hasplayer;
		  }
		  
		tocheck.add(currcave.passages[i].destination);
		//gameio.putln(game.P.location);
		//gameio.putln(currcave.passages[i].destination);
		//tempc = (cave)game.C.contents.get(currcave.passages[i].destination);
		//gameio.putln(tempc.idnumber);
		/*		if(tempc.idnumber == game.P.location)//.has("Player"))
		  {
		    hasplayer = true;
		    return hasplayer;
		    //break;
		  }
		*/
	      }
	  }
      }
    return hasplayer;
    
  }
  
  void act()
  {
	
gameio.putln("Wumpusfinder acting");

    cave playercave = (cave)game.C.contents.get(game.P.location-1);

gameio.putln(playercave.name);
gameio.putln(((cave)game.C.contents.get(location-1)).name);
    // only act if we are in the same cave as the player
    if ( (playercave.has(name)) || game.P.has(name) )
    	    findpath(game.W._position);

  } // end act()

}
