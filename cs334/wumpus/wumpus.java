// Wumpus code by McDevitt / McGovern / Tausenfreundt
// CS 225 Spring 2012

// hey - there are two classes in this one file!

// SkipMe implemented MAK Wed Nov 20 12:38:09 EST 2013

// not well commented!
// nervous that _position-1 code is off by 1


// note that students used leading underscores to indicate class vars

class wumpus extends gameobject
{
        boolean SkipMe = false;  // one action per loop

	int _position;
	int _home = 63;
                // _home is cavern specific.  for cavedata should be 63

	int _currState;
		// 0 = wandering
		// 1 = sleeping
		// 2 = hungry
		// 3 = sleepy
		// 4 = angry
	    
    public wumpus()
    {
    	name = "Wumpus";
    	_currState = 0;
    }

    public void act()
    {
      /*if (SkipMe)
            SkipMe=false;
      else
      {
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
				else if (rand<=95)	// Wumpus becomes hungry
				{
					_currState = 2;
				}
				else if (rand<=100)	// Wumpus becomes angry
				{
					_currState = 4;
					wander(rand);
				}
				break;
			
			case 1:		// sleeping
				if (rand<=45)		// Wumpus snores
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
			
			case 2:		// hungry
				if (rand<=80)		// Wumpus moves towards player
				{
					movetoplayer();
				}
				else if (rand<=90)	// Wumpus roars
				{
					roar();		
				}
				else if (rand<=100)	// Wumpus moves randomly
				{
					wander(rand);	
				}
				break;
			
			case 3:		// sleepy
				if (_position == _home)
				{
					_currState = 1;
				}
				else if (rand<=90)		// Wumpus moves towards his home
				{
					sleepy();
				}
				else if (rand<=100)	// Wumpus falls asleep
				{
					_currState = 1;
				}
				break;
			
			case 4:		// anger
				if (rand<=20)		// Wumpus goes back to wandering
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
      }*/
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
    	PathQueue open1 = new PathQueue();
    	PathQueue closed1 = new PathQueue();
    	PathQueue open2 = new PathQueue();
    	PathQueue closed2 = new PathQueue();
		int[] destinations = new int[6];
    	int[] curr_path = new int[1];
    	int[] temppath;
    	curr_path[0] = _position;
    	open1.queue(curr_path);
    	curr_path[0] = endcave;
    	open2.queue(curr_path);
    	while(!(open1.isEmpty() && open2.isEmpty()))
    	{
    		curr_path = open1.dequeue();
    		if(!(open1.exists(curr_path) || closed1.exists(curr_path)))
    		{
		    	cave tempcave = (cave)game.C.contents.get(_position-1);
		    	for(int i = 0;i < tempcave.passages.length;i++)
		    	{
	    			destinations[i] = tempcave.passages[i].destination;
    			}
    			for(int i = 0;i < destinations.length;i++)
    			{
    				if(destinations[i] != 0)
    				{
	    				temppath = new int[curr_path.length+1];
	    				for(int j = 0;j < curr_path.length;j++)
	    				{
	    					temppath[j] = curr_path[j];
	    				}
	    				temppath[temppath.length-1] = destinations[i];
	    				if(open2.exists(temppath) || closed2.exists(temppath))
	    				{
	    					return temppath[1];
	    				}
	    				open1.queue(temppath);
    				}
    			}
    		}
    		curr_path = open2.dequeue();
    		if(!(open2.exists(curr_path) || closed2.exists(curr_path)))
    		{
		    	cave tempcave = (cave)game.C.contents.get(_position-1);
		    	for(int i = 0;i < tempcave.passages.length;i++)
		    	{
	    			destinations[i] = tempcave.passages[i].destination;
    			}
    			for(int i = 0;i < destinations.length;i++)
    			{
    				if(destinations[i] != 0)
    				{
	    				temppath = new int[curr_path.length+1];
	    				for(int j = 0;j < curr_path.length;j++)
	    				{
		    				temppath[j] = curr_path[j];
	    				}
	    				temppath[temppath.length-1] = destinations[i];
	    				if(open1.exists(temppath) || closed1.exists(temppath))
	    				{
		    				return open1.findstart(temppath);
	    				}
	    				open2.queue(temppath);
    				}
    			}
    		}	
    	}
		return 0;
    }
    
    public void move(int finish)
    {
        if (finish > _position)
          SkipMe = true; // avoid repeated movement

    	System.out.println("moving from " + _position + " to " + finish);
    	cave tempc = (cave) game.C.contents.get(_position-1);
		tempc.remove(this);
		tempc = (cave) game.C.contents.get(finish-1);
		tempc.putinto((gameobject)this);
		_position = finish;
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



class PathQueue
{
	private int[][] _paths;					// List of paths yet to look through
											// Indexes to an array of ints, which refers to a path through the cave.
	public PathQueue()
	{
		 _paths = new int[0][0];			// Queues always start empty =D
	}   
		
    public boolean isEmpty()
    {
    	if(_paths.length == 0)
    		return true;
    	else
    		return false;
    }
    
    public void queue(int[] path)
    {
    	int[][] temppaths = new int[_paths.length+1][];
    	for(int i = 0;i < _paths.length;i++)
    	{
    		temppaths[i] = new int[_paths[i].length];
    		for(int j = 0;j < _paths[i].length;j++)
    		{
	    		temppaths[i][j] = _paths[i][j];
    		}    			
    	}
    	temppaths[temppaths.length-1] = path;
    	_paths = new int[temppaths.length][];
    	for(int i = 0;i < temppaths.length;i++)
    	{
    		_paths[i] = new int[temppaths[i].length];
    		for(int j = 0;j < _paths[i].length;j++)
    		{
	    		_paths[i][j] = temppaths[i][j];
    		}   
    	}
    }
    
    public int[] dequeue()										
    {
    	int[] x = new int[_paths[0].length];
    	for(int i = 0;i < _paths[0].length;i++)
    	{
    		x[i] = _paths[0][i];
    	}
    	int[][] temppaths = new int[_paths.length-1][];
    	for(int i = 0;i < temppaths.length;i++)
    	{
    		temppaths[i] = new int[_paths[i].length];
			for(int j = 0;j < temppaths[i].length;j++)
    		{
	    		temppaths[i][j] = _paths[i+1][j];
    		}    	
    	}
    	_paths = new int[temppaths.length][];
    	for(int i = 0;i < temppaths.length;i++)
    	{
    		_paths[i] = new int[temppaths[i].length];
    		for(int j = 0;j < _paths[i].length;j++)
    		{
    			_paths[i][j] = temppaths[i][j];
    		}
    	}
    	return x;
    }
    
    public int size()
    {
    	return _paths.length;
    }
    
    public boolean exists(int[] path)		// Used to check if a specific path exists in
    {										// the queue already (used to prune the tree
    	for(int i = 0;i < _paths.length;i++) // and prevent loops).
    	{
    		if(path[path.length-1] == _paths[i][_paths[i].length-1])
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    public int findstart(int[] path)
    {
    	for(int i = 0;i < _paths.length;i++)
    	{
    		if(path[path.length-1] == _paths[i][_paths[i].length-1])
    		{
    			return _paths[i][1];
    		}
    	}
    	return 0;
    }
}
