// Mohit Patel
// Tuesday Nov 19 03:14:07 EST 2013

// original throw code by unknown student
// DOES NOT PLACE IN NEW CAVE

import java.lang.*;

class throwcommand extends playercommand
{
	public throwcommand()
	{
	  commandletter = 't';
	}
	
	public void docommand()
	{
	if ( game.P.contents.isEmpty() ) {
		gameio.putln("You have nothing to throw.");
	} else {
		for ( int i = 0; i < game.P.contents.size(); i++ ) {
			gameio.putln(i + " " + game.P.contents.get(i).name);
		}
		gameio.put("What would you like to throw? ");
		int throwingnum = Integer.parseInt(gameio.getstring());
		gameobject throwing = game.P.contents.remove( throwingnum );
		gameio.putln();
		gameio.put("Which direction? ");
		String command = gameio.getstring();
		switch (command.charAt(0))
		{
		    case 'n' : throwindir(0,throwing); break;
		    case 'e' : throwindir(1,throwing); break;
		    case 's' : throwindir(2,throwing); break;
		    case 'w' : throwindir(3,throwing); break;
		    case 'u' : throwindir(4,throwing); break;
		    case 'd' : throwindir(5,throwing); break;
		} // end switch
	}
    }
    
    public void throwindir( int direction, gameobject throwing ) {
        cave curcave = (cave)game.C.contents.get(game.P.location-1);
		
			// is this an illegal direction?
        if (curcave.passages[direction].destination == 0)
          {
			gameio.putln("No such tunnel! ");
		        game.P.contents.add(throwing);
          }
        else
	  {
	     cave destcave = 
              (cave)game.C.contents.get(
               curcave.passages[direction].leadsto(game.P.location)-1);
			
             // PLACE IN NEW CAVE
             destcave.contents.add(throwing);  // add to new cave

	     if (destcave.has("Wumpus") && throwing.name == "Grenade" )
		{
		  gameio.wumpuskill();
		  System.exit(1); // game over - you're a winner!
	        }
             else if (throwing.name == "Grenade" ) 
                // grenades have special behavior
		{
		  gameio.grenadeeffect();
                  // remove(String) not working?
                  destcave.contents.remove(throwing);
		}
			
			} // end else is a legal tunnel
    }
}
