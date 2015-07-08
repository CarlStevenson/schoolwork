
// Wumpus code by McDevitt / McGovern / Tausenfreundt
// CS 225 Spring 2012
// Edited by Carl Stevenson
// CS 225 Fall 2013

// not well commented!

// note that students used leading underscores to indicate 
// instance variables (which probably should have been private)
// some coders like this, many more dislike it.

import java.util.*;

class wumpus extends gameobject
{
	int _position;
	int _home;
	int _currState;
		// 0 = wandering
		// 1 = sleeping
		// 2 = hungry
		// 3 = sleepy
		// 4 = angry
    public boolean SkipMe = false;
	    
    public wumpus()
    {
    	name = "Wumpus";
    	_currState = 0;
        _home = 63;       // "A Wumpus Lair" cave in datafile    
    }

    public void act()
    {
        if(SkipMe){

            SkipMe = false;
        }
        else{

       int rand = game.R.nextInt(100) +1;

       switch (_currState)
	{
	  case 0:   // wandering
	            if (rand<=5)
		    {
		      roar();
		    }
		    else if (rand<=15)	// Wumpus becomes sleepy
		    {
		      sleepy();
		    }
		    else if (rand<=70)	// Wumpus wanders
		    {
		      wander(rand);
		    }
		      else if (rand<=95)// Wumpus becomes hungry
		    {
		      _currState = 2;
		    }
		    else if (rand<=100)	// Wumpus becomes angry
		    {
		      _currState = 4;
		      wander(rand);
		    }
		    break;
			
	  case 1:  // sleeping
		   if (rand<=45)	// Wumpus snores
		   {
		      snore(); 	
		   }
		   else if (rand<=70)	// Wumpus becomes hungry
		   {
		      _currState = 2;
		   }
		   else if (rand<=100)	// Wumpus wakes up
		   {
		      wander(rand);
		   }
		   break;   
			
	  case 2:  // hungry
		   if (rand<=80)	// Wumpus moves towards player
		   {
		     movetoplayer();
		   }
		   else if (rand<=90)	// Wumpus roars
		   {
		     roar();		
		   }
		     else if (rand<=100)// Wumpus moves randomly
		   {
		     wander(rand);	
		   }
		   break;
			
	   case 3: // sleepy
		   if (_position == _home)
		   {
		     _currState = 1;
		   }
		   else if (rand<=90)	// Wumpus moves towards his home
		   {
		     sleepy();
		   }
		   else if (rand<=100)	// Wumpus falls asleep
		   {
		     _currState = 1;
		   }
		   break;
			
	   case 4: // anger
		   if (rand<=20)	// Wumpus goes back to wandering
		   {
		     wander(rand);
		   }
		   else if (rand<=80)	// Wumpus wanders angrily
		   {
		     wander(rand);
		     _currState = 4;
		   }
		   else if (rand<=100)	// Wumpus roars
		   {
		     roar();
		   }
		   break;
        }		
        }
    }
    
    public void movetoplayer()
    {
    	System.out.print("Chasing the player, who is at " + game.P.location + " and ");
    	move(findpath(game.P.location));
    }
    
    public void snore()
    {
    	gameio.putln();
    	gameio.putln("You hear a loud rumbling, as if something large is snoring somewhere...");
    	gameio.putln();
    }
    
    public void sleepy()
    {
    	_currState = 3;
    	System.out.print("Going home (" + _home + ") and ");
    	move(findpath(_home));
    }
    
	public void roar()
	{
		gameio.putln();
		gameio.putln("You hear a gutwrenching roar from somewhere in the cavern...");
		gameio.putln();
	}
	
	public void wander(int rand)
	{
		_currState = 0;
		cave tempc = (cave)game.C.contents.get(_position-1);
		int randomdestination;
		randomdestination = tempc.passages[rand%6].destination;
		for(int i = 0;i < 6;i++)
		{
			if(randomdestination == 0)
			{
				rand++;
			}
			randomdestination = tempc.passages[rand%6].destination;
		}
		move(randomdestination);
	}
    
    private int findpath(int endcave)
    {
    	cave curcave = (cave)game.C.contents.get(_position-1);
    	// find the shortest path from current cave to endcave

    	// initialize the next step to be taken
    	int nextStep = 0;
    	// initialize the queue for queuing purposes in my 
    	// breadth-first algorithm
    	Queue<queuedcave> daQueue = new LinkedList<queuedcave>();
    	Set<cave> beenTo = new HashSet<cave>();
    	// some code re-used from the player.java class
    	// set up the first node
    	beenTo.add(curcave);
    	queuedcave nextCave;
    	// set up the initial queue
        for(int direction = 0; direction<6; direction++){
        
            if (curcave.passages[direction].destination == 0){
        		// do nothing
        	}else{
        	
        	    nextCave = new queuedcave((cave)game.C.contents.get(
        						(curcave.passages[direction].destination-1)),
        						direction);
        	    daQueue.add(nextCave);
        	}
        
        }
    	// breadth-first search
    	while(!daQueue.isEmpty()){

       		nextCave = daQueue.remove();
       		beenTo.add(nextCave.getCave());
        		
       				
       		if(nextCave.idnumber == endcave){
       			nextStep = curcave.passages[nextCave.getSource()].destination;
       			break;
       		}else{
       		    for(int i = 0; i<6; i++){
       		    
       		        if(nextCave.getCave().passages[i].destination == 0){
       		            // do nothing
       		        }else if(!beenTo.contains((cave)game.C.contents.get(
        					(nextCave.getCave().passages[i].destination-1))))
        		        {
        					
        			    		
       		        daQueue.add(new queuedcave((cave)game.C.contents.get(
        					(nextCave.getCave().passages[i].destination-1)),
        						        nextCave.getSource()));
        		    
                    }
       		    }
       		}
       	}	
    	return nextStep;
    }
    
    public void move(int finish)
    {
 
    	System.out.println("moving from " + _position + " to " + finish);
        if(finish> _position){
            SkipMe = true;
        }
        cave tempc = (cave) game.C.contents.get(_position-1);
		tempc.remove(this);
		tempc = (cave) game.C.contents.get(finish-1);
		tempc.putinto((gameobject)this);
		_position = finish;
        
		if(_position == game.P.location){
			game.P.playerdeath();
    	}
    }
    
    public void setposition(int position)
    {
    	_position = position;
    }
    
    public void sethome(int home)
    {
    	_home = home;
    }	
}

