

// converted the pickup command to out new command class
// original author uncredited
// Tue Nov 19 15:11:42 EST 2013

// hardcoded to work only for main player
// no error checking
// currently can pick up anything in the cave (except self)
// are there things you should not be able to pick up?



class pickupcommand extends playercommand
{
    public pickupcommand()
    {
        commandletter = 'p';
    }

    public void docommand() 
    {
        int pickupint;

        cave curcave = (cave)game.C.contents.get(game.P.location-1);
	if ( curcave.contents.size() == 1 ) 
         {
          // there is only the player, no items
          gameio.putln("There's nothing to pickup.");
          gameio.putln();
         }
	else 
         {
	  if ( curcave.contents.size() == 2 ) 
          // player + one object only
            {
	      if ( curcave.contents.get( 0 ).name != "Player" ) 
                pickupint = 0;
              else
                pickupint = 1;
              gameio.putln();
            }
            else
            {
              // input is strange; player item is skipped, 
              // so # may be missing
	      for ( int i = 0; i < curcave.contents.size(); i++ ) 
		    if ( curcave.contents.get( i ).name != "Player" ) 
		    	gameio.putln(i + " " + curcave.contents.get( i ).name );
           
               // no error checking
               gameio.put("What # item  would you like to pickup? ");
               pickupint = Integer.parseInt(gameio.getstring());
            }

            // remove from current cave
            gameio.putln("Picking up " +  curcave.contents.get(pickupint).name );
            gameobject go = curcave.contents.remove( pickupint );
            // add to player's inventory
	    game.P.contents.add(go);
	 }
    }

}
