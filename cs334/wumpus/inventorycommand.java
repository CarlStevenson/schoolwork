// Anthony Kapolka
// Wed Nov 20 12:04:58 EST 2013

// hardcoded to work only for main player
// no error checking

// consider generalizing to list container contents 
// we seem to be doing this in a lot of commands


class inventorycommand extends playercommand
{
    public inventorycommand()
    {
        commandletter = 'i';
    }

    public void docommand() 
    {
        cave curcave = (cave)game.C.contents.get(game.P.location-1);

	if ( game.P.contents.size() == 0 ) 
          // there are no items
          gameio.putln("You carry nothing.");
	else 
         {
            gameio.putln("You carry ");
	    for ( int i = 0; i < game.P.contents.size(); i++ ) 
		gameio.putln(game.P.contents.get( i ).name );
           
	 }
        gameio.sleep(5);
    }

}
