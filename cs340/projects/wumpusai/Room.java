

public class Room
{
    // variables
    Room[] neighbors = new Room[6];
    boolean squeek;
    boolean draft;
    boolean visited;
    int ID;
    
    // constructors
    public Room (int c, CaveMap map)
    {
    	visited = false;
    	squeek = false;
		draft = false;
		ID = c;
		map.rooms[ID] = this;
		
		cave thiscave = (cave) game.C.contents.get(ID);
		for (int i = 0; i < 6; i++)
		{
			if (thiscave.passages[i] != null) // make sure tunnel exists
			{
				tunnel workingtunnel = thiscave.passages[i];
				// check to see if the cave has already been created
				if (map.rooms[workingtunnel.leadsto(ID)] == null) 
				{
					neighbors[i] = new Room(workingtunnel.leadsto(ID), map);
				} else
				{
					neighbors[i] = map.rooms[workingtunnel.leadsto(ID)];
				}
			}
		}
    }
    
    // methods
    public void visit(CaveMap map)
    {
    	if (!visited)
    	{
	    	visited = true;
	
			cave thiscave = (cave) game.C.contents.get(ID);
			for (int i = 0; i < 6; i++)
			{
				if (thiscave.passages[i] != null) // make sure tunnel exists
				{
					tunnel workingtunnel = thiscave.passages[i];
					if (workingtunnel.haspit)
					{
						draft = true;
					}
					if (((cave) game.C.contents.get(workingtunnel.leadsto(ID))).has("Bats"))
					{
						squeek = true;
					}
				}
			}
    	}
    }
}
