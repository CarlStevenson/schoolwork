
// shootcommand by Zachary Berg & Muteb Alolayan
// Wed Nov 20 11:35:43 EST 2013
// based on original arrow code by Michael Bates Fall 2012
// note that arrows are *not* gameobjects as implemented

class shootcommand extends playercommand
{ 
    public shootcommand()
    {
	commandletter = 'a'; // a for arrow
    }
    
    public void docommand()
    {
	if (game.P.arrownumber > 0) // Check if user has a arrow
	    {
		// Get input on which direction to shoot arrow
		gameio.putln("Which cave would you like to shoot the arrow in? ");
		String input = gameio.getstring();
		switch (input.charAt(0))
		    {
		    case 'n' : shootarrow(0); break;
		    case 'e' : shootarrow(1); break;
		    case 's' : shootarrow(2); break;
		    case 'w' : shootarrow(3); break;
		    case 'u' : shootarrow(4); break;
		    case 'd' : shootarrow(5); break;
		    }
	    }
	else // User has no arrows!
		{
		    gameio.putln();
		    gameio.putln("You have no arrows!");
		    gameio.sleep(1);
		    // Ask user for another command
		    game.P.act();
		}
    }


 void shootarrow(int direction)
    {
   	int location = game.P.location;
    	cave curcave = (cave)game.C.contents.get(location-1);


    	if (curcave.passages[direction].destination == 0)
        // the default tunnel exists but has 0s for src/dest
    	{
          	gameio.putln("No tunnel exists there! ");
          	// Ask user for next command
			game.P.act();
		}
        else 
        {
			// Use grenade explosion effect
			gameio.arroweffect();
			// Get intended cave's object list
			
			cave destcave = (cave)game.C.contents.get(curcave.passages[direction].leadsto(location)-1);
			// Check if cave has wumpus
			if (destcave.has("Wumpus"))
			{
				gameio.putln("The stench of the wumpus seems to be gone!");
				gameio.putln("You killed the wumpus!");
				gameio.sleep(1);
				gameio.putln("YOU WIN!");
				System.exit(1);
			}
			else // No wumpus was in the cave
			{
				gameio.putln("Uh oh, wrong cave...");
				// Decrease arrow number
				game.P.arrownumber--;
				// Move the wumpus
				// game.W.startle();
			}
		}
    }
}
