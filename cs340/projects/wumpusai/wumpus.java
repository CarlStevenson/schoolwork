// Java version (not quite right) by Tyler Copeland
// shortpath and howfar functions designed in c by Mark D. Hulme
// Anthony Kapolka
// Fri Nov 18 12:27:41 EST 2011

import java.util.Random;

class wumpus extends gameobject
{

    int MAXCAVES= game.MAXCAVE;
    static Random Ran = new Random();
    int location;
    boolean mature = false;	
                              // becomes matured after it's laid an egg 
                              // so each wumpus can only lay one egg per game
    boolean awake;
    int disoriented = 0;      // baby wumpii are disoriented after being 
                              // hatched and will stay put for 5 turns.
    // they can still eat a player that steps into the same room as them

    public wumpus()
    {
	name = "Wumpus";
	awake= true;
    }

    /*
    public wumpus(int count, String newname)
		{
		name = newname;
		awake = true;
		disoriented = count;
		}
    public wumpus(int count)
    {
	name = "Wumpus";
	awake = true;
	disoriented = count;
    }*/

    public void act()
    {
	
	int percent = Ran.nextInt(99) + 1;
	if ((awake == true) && (disoriented == 0))  	
                //wumpus is awake and not just hatched
	    {
		
      		// 35% will be hungry and move towards player
		// 20% will fall asleep
		// 45% will do nothing
		if (percent <= 35) 
		    {  	
                      // couldn't get the move towards player function to work. 
		      // so wumpus moves in a random direction. 
		      //	moveindirection(shortpath(game.P.location-1));
		      //	if(location == game.P.location-1)
		      //	gameio.wumpuseffect(); 
			moverandom();
		    }
		else if (percent <= 55)
		    {//go to sleep
			awake = false;
		    }
	    }
	 else if (disoriented ==0)	// wumpus is asleep
	    { 		            // half time wake up, half time stay asleep
		if (percent <= 50)
		    awake = true;
		else 
		    {	gameio.putln("You hear the Wumpus snoring loudly somewhere... Seems like it's safe.");
			gameio.sleep(1);
			gameio.putln(" For now...");
		    }
	    }
	else 	//wumpus ticks down towards being able to interact
	    disoriented--;
	
	if (location == game.P.location)
		    {
			gameio.wumpuseffect();
			System.exit(1);
		    }
gameio.put("wumpus is at: ");
cave c = (cave) game.C.contents.get(location-1);
gameio.putln(c.name);
    }

    public void moveindirection(int path)
    {
	cave c = (cave) game.C.contents.get(location-1);
	if(c.passages[path].destination!=0)
	    move(location,c.passages[path].leadsto(location));
    }

    public void moverandom()
    {
	// this picks a *legal* direction
        
	cave mycave = (cave)game.C.contents.get(location-1);
gameio.put(mycave.name);
	int direction = mycave.getrandomtunnel();
gameio.putln(direction);


	moveindirection(direction);
	
    }
    
/* not currently working

    public int shortpath(int destination)  //returns tunnel number towards destination
    {					// integers 11 and 10 are mysterious
        int shortest=MAXCAVES+11;
	int path=7;
	int shorter=MAXCAVES+11;
        for(int j=1;j<=MAXCAVES-1;j++)
	    {
		cave c = (cave) game.C.contents.get(j);
		c.disfromwumpus=MAXCAVES+10;
	    }
        if(location!=destination)
	    for (int i=0;i<6;i++)
                {
		    cave c = (cave) game.C.contents.get(location);
		    int adjacentcave= c.passages[i].leadsto(location);
		    
		    if (adjacentcave>0)
			{
			    if (adjacentcave==destination)
				shorter=1;
			    else
				shorter=howfar(adjacentcave,destination)+1;
			    if (shorter<=shortest)
				{
				    shortest=shorter;
				    path=i;
				}
			}
		}
	return path;
    }
    
    
    public int howfar(int current,int destination)
    {
	cave c = (cave) game.C.contents.get(current);
	c.disfromwumpus=MAXCAVES+5;
	int shorter=MAXCAVES+11;
	for (int i=0;i<6;i++)
	    {
		cave ca = (cave) game.C.contents.get(location);
		int adjacentcave= ca.passages[i].leadsto(location);
		if (adjacentcave>0)
		    {
			if (adjacentcave==destination)
			    shorter=1;
			else
			    {
				cave cb = (cave) game.C.contents.get(adjacentcave);
				if (cb.disfromwumpus==(MAXCAVES+10))
				    shorter=howfar(adjacentcave,destination)+1;
				else
				    {
					cave cc = (cave) game.C.contents.get(adjacentcave);
					if (cc.disfromwumpus<(MAXCAVES+5))
					    shorter=cc.disfromwumpus+1;
				    }		
			    }
			cave cd = (cave) game.C.contents.get(current);
			if (shorter<=cd.disfromwumpus)
			    cd.disfromwumpus=shorter;
		    }
	    }
	cave ce = (cave) game.C.contents.get(current);
	return ce.disfromwumpus;
    }
*/
    
    public void wakeup()
    {
	awake = true;
    }
    
    public void fallasleep()
    {
	awake = false;
    }
    
    public void startle()
    {
	moverandom();			
    }       

    public void move(int finish)
    {
        // add wumpus to finish cave
	cave tempc = (cave) game.C.contents.get(finish-1);
	tempc.putinto((gameobject)this);
    }
    
    public void move(int start, int finish)
    {
        if (finish >= 0) // catacombs have one way passages.
	  { cave tempc = (cave) game.C.contents.get(start-1);
	    tempc.remove(this);
	    move(finish);
          }
    }
}
