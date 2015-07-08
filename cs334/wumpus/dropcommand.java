// Anthony Kapolka
// Wed Nov 20 11:42:35 EST 2013

// hardcoded to work only for main player
// no error checking

class dropcommand extends playercommand
{
    public dropcommand()
    {
        commandletter = 'r'; //dRop
    }

    public void docommand() 
    {
        cave curcave = (cave)game.C.contents.get(game.P.location-1);

	if ( game.P.contents.size() == 0 ) 
          // no items
          gameio.putln("There's nothing to drop.");
	else 
         {
	    for ( int i = 0; i < game.P.contents.size(); i++ ) 
	      gameio.putln(i+1 + " " + game.P.contents.get( i ).name );
           
            // no error checking
            gameio.put("What # item  would you like to drop? ");
            int dropint = Integer.parseInt(gameio.getstring());

            // remove from player's inventory
            gameobject go = game.P.contents.remove( dropint-1 );
gameio.putln(go.name);
            // add to current cave
	    curcave.contents.add(go);
	 }
    }

}
