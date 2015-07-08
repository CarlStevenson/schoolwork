

public class CaveMap
{
    // variables
    Room current;
    Room[] rooms;
    
    // constructors
    public CaveMap (int c, int caveMax)
    {
    	rooms = new Room[caveMax];
    	current = new Room(c, this);
    }
    
    // methods
    public int cautiousStep() // explore, but try to avoid bats and pits
    {
    	int dest = -1;
    	double min = 36;
    	
    	for (int i = 0; i < 6; i++)
    	{
    		if ((current.neighbors[i] != null)&&(!current.neighbors[i].visited))
    		{
    			int squeeks = 0;
    			int drafts = 0;
    			double mysteries = 0;
    			
    			for (int j = 0; j < 6; j++)
    	    	{
    	    		if (current.neighbors[j] != null)
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
    			if (((squeeks+drafts)/mysteries) < min)
    			{
    				dest = i;
    				min = ((squeeks+drafts)/mysteries);
    			}
    		}
    	}
    	
    	if (dest == -1) // if no good answer pick random tunnel
    	{
    		for (int i = 3; true; i++)
    		{
    			if (current.neighbors[i] != null)
    			{
    				dest = i;
    				break;
    			}
    		}
    	}
    	
    	return dest; // return tunnel index
    }
    
    public int safeStep() // choose somewhere safe with as many exits as possible
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
    	{
    		for (int i = 3; true; i++)
    		{
    			if (current.neighbors[i] != null)
    			{
    				dest = i;
    				break;
    			}
    		}
    	}
    	
    	return dest; // return tunnel index
    }
    
    public int exploreStep() // choose the cave that gives the most new information
    {
    	int dest = -1;
    	int max = -1;
    	int counter;
    	
    	for (int i = 0; i < 6; i++)
    	{
    		counter = 0;
    		if (current.neighbors[i] != null)
    		{
    			if (!current.neighbors[i].visited)
    			{
    				counter++;
    			} else
    			{
	    			for (int j = 0; j < 6; j++)
	    			{
		    			if ((current.neighbors[i].neighbors[j] != null)&&(!current.neighbors[i].neighbors[j].visited))
			    		{
			    			counter++;
			    		}
		    		}
    			}
        	}
    		if (counter > max)
    		{
    			max = counter;
    			dest = i;
    		}
    	}
    	
    	if (dest == -1) // if no good answer pick random tunnel
    	{
    		for (int i = 3; true; i++)
    		{
    			if (current.neighbors[i] != null)
    			{
    				dest = i;
    				break;
    			}
    		}
    	}
    	
    	return dest; // return tunnel index
    }

    public void moveDirection(int c)
    {
    	current.neighbors[c].visit(this);
    	current = current.neighbors[c];
    }
}