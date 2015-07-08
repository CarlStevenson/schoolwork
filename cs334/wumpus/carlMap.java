
// part of the aiplayer
// Edited by Carl Stevenson
// Fri Nov 15 11:31:19 EST 2013

public class carlMap
{
    // variables
    carlRoom current;
    carlRoom[] rooms;
    
    // constructors
    public carlMap (int c, int caveMax)
    {
    	rooms = new carlRoom[caveMax];
    	current = new carlRoom(c, this);
    }
    
    // methods
    public int cautiousStep() // explore, but try to avoid bats and pits
    {
        // if we've recently shot at and missed wumpus, we don't chase it.

    	int dest = -1;
    	double min = 36;  // starting max value
    	
    	for (int i = 0; i < 6; i++)
    	{
                // dump the !visited => make visited
    		if ((current.neighbors[i] != null)&&(!current.neighbors[i].visited))
    		{
    			int squeeks = 0;
    			int drafts = 0;
    			double mysteries = 0;
    			
    			for (int j = 0; j < 6; j++)
    	    	{
    	    		if (current.neighbors[j] != null)
    	    		{
			   //recall squeek & draft only set for caves I've visted
                           // so I have visited that cave  
                           // BUT I shouldn't know the tunnel leads there!
    	    		    if (current.neighbors[i].neighbors[j] != null) 
                             {
    	    			if (current.neighbors[i].neighbors[j].squeek)
    	    			{
    	    				squeeks++;
    	    			}
    	    			if (current.neighbors[i].neighbors[j].draft)
    	    			{
    	    				drafts++;
    	    			}
    	    			for (int k = 0; k < 6; k++)
    	    	    	{
    	    	    		if ((current.neighbors[i].neighbors[j].neighbors[k] != null)&&(!current.neighbors[i].neighbors[j].neighbors[k].visited))
    	    	    		{
    	    	    			mysteries++;
    	    	    		}		
    	    	    	}
                             }
    	    		}
    	    	}
    			if (((squeeks+drafts)/mysteries) < min)
    			{
    				dest = i;
    				min = ((squeeks+drafts)/mysteries);
    			}
    		}
    	}
    	
    	if (dest == -1) // if no good answer pick random tunnel
    	   dest = getrandomtunnel();	
    	
    	return dest; // return tunnel index
    }
    
    public int safeStep() 
    // choose somewhere safe with as many exits as possible
    {
    	int dest = -1;
    	int best = -1;
    	int count;
    	
    	for (int i = 0; i < 6; i++)
    	{
    		if ((current.neighbors[i] != null)&&(current.neighbors[i].visited))
    		{
        		count = 0;
    			for (int j = 0; j < 6; j++)
    	    	{
    	    		if ((current.neighbors[i].neighbors[j] != null)&&(current.neighbors[i].neighbors[j].visited))
    	    		{
    	    			count++;
    	    		}
    	    	}
    			if (count > best)
    			{
    				best = count;
    				dest = i;
    			}
    		}
    	}
    	
    	if (dest == -1) // if no good answer pick random tunnel
    	   dest = getrandomtunnel();	
    	
    	return dest; // return tunnel index
    }
    
  public int exploreStep() 
  // choose the cave that gives the most new information
  // something in this code is giving the wrong count!
  {
    int dest = -1;
    int max = 0;    // 0 means no new information
    int counter;
 /*   	
    for (int i = 0; i < 6; i++)  // loop over all tunnels
     {
       counter = 0;  // no tunnel
       if (current.neighbors[i] != null)  // if there is a tunnel
    	{
 //   	  if (!current.neighbors[i].visited)
 //   	   {
 //   	     counter++;  // count tunnel unvisited 
//    	   } 
//          else  // we have visited it
    	   {
	     for (int j = 0; j < 6; j++)  // loop over its tunnels
	       {
		if ((current.neighbors[i].neighbors[j] != null)
                   &&(!current.neighbors[i].neighbors[j].visited))
		  {
		    counter++;  // count all unvisited neighbors' neighbors
	  	  }
	       }
           }
        }

        // counter now equals number of neighboring unvisited caves

        // if this tunnel exists and has the most so far, pick it
       if (current.neighbors[i] != null)  // if there is a tunnel
    	if (counter > max)
    	  {
gameio.put(counter);	
gameio.putln(i);	
    	    max = counter;
    	    dest = i;
    	  }
     }

gameio.put(dest);	
*/
    	if (dest == -1) // if no good answer pick random tunnel
    	   dest = getrandomtunnel();	
    	
    	return dest; // return tunnel index
    }

    public int getrandomtunnel()
    {
         int dest = game.R.nextInt(6); // 0=5
         
         while (current.neighbors[dest] == null)    
            dest = game.R.nextInt(6); // 0=5

         return dest;
    }

    public void moveDirection(int c)
    {
    	current.neighbors[c].visit(this);
    	current = current.neighbors[c];
    }
}
