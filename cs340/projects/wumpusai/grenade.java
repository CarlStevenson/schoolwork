// Mason McIntyre CS 126
// Wed Dec 12 12:48:33 EST 2012


class grenade extends gameobject
{
	boolean np;
	int timer;
	
	public grenade()
	{
		if (game.R.nextInt(2)==1)  // since we can't pull the pin yet
		   np = true;
                else
		   np = false;

		timer = game.R.nextInt(3) + 2;
		name = "Grenade"; 
	}

	public boolean nopin()
	{
		return np;
	}

	public void act()
	{

		if(np)
		{
		    gameio.putln();
		    gameio.putln("Tick... the grenade ticks!!");
		    gameio.putln();
		    timer--;
		}	    
	    if(timer == 0)
	    {
		// will this also kill the wumpus and/or player?  it should
                // should we see this effect even when we are not near it?
		gameio.grenadeeffect();

		// the grenade should also be rendered inoperative.
		// suggestion: remove it from the cave
                //             insert a gameobject named "grenade shrapnel"
	    }
	}
}
