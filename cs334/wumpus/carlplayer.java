
// initial shell by Anthony Kapolka
// Fri Nov 15 11:31:19 EST 2013
// Edited by Carl Stevenson & Troy & Tyl

// mystery 3 uses connectivity info for cautiousstep player doesn't know
// doesn't check entire cavern explored so far & use shortest path

class carlplayer extends player
{
   // use location, SkipMe variables from player.java
   // use moveindir(tunnel) from player.java to move

   // carlplayer will need own traveled map of the cave
   // this may not be the best representation/idea
    carlMap cavemap;

    carlplayer()
    {
       name = "Carlsbad";
    }

    boolean nextto(String S) 
    // is there an S in an adjoining cave?
    // probably should only call with "Wumpus" and "Bats" (and "Player"?)
    // remember, Pits are in tunnels, not caves
    {
       cave mycave = (cave)game.C.contents.get(location-1); 

       for (int p = 0; p < 6; p++)
        {
          if ((mycave.passages[p] != null) && (mycave.passages[p].destination > 0))
          // ok to just use destination because we use leadsto below
          {
            cave myneighbor 
             = (cave)game.C.contents.get(mycave.passages[p].leadsto(location)-1);
            if (myneighbor.has(S))
             return true;
          }
        }
       return false;
    }

    boolean nexttopit() 
    // returns true if there is a pit in an adjoining tunnel
    {
       cave mycave = (cave)game.C.contents.get(location-1); 

       for (int p = 0; p < 6; p++)
        {
          if ((mycave.passages[p] != null) && (mycave.passages[p].destination > 0))
          // ok to just use destination because we are not moving but
          // just making sure that the tunnel exists.
            if (mycave.passages[p].haspit)
              return true;
        }

       return false;
     }





    // *************************************************************
    // act function
    // *************************************************************
    
    void act()
    {
      System.out.println(cavemap);
gameio.put("Carlsbad is acting from cave ");
gameio.putln(location);
	// decide & perform AI action (e.g. "move")
        if (cavemap == null){
        
            cavemap = new carlMap(location-1, game.MAXCAVE); 
        }
        if (SkipMe)
            SkipMe=false;
        else
        {
            boolean shoot = shouldShootArrow();
            boolean pits = nexttopit();
            if(shoot){
                // shootarrow is overriding player.java's function,
                // but also calls super.shootarrow()
                shootarrow();
            }else{
            
            
                if(pits){
gameio.put(" ... pit near Carl ");
                    int cautious = cavemap.cautiousStep();
gameio.putln(cautious);
                    moveindir(cautious);
                    cavemap.moveDirection(cautious);
                }
                
                else{
gameio.put(" ... exploring ");
                    int explore = cavemap.exploreStep();
gameio.putln(explore);
                    moveindir(explore);
                    cavemap.moveDirection(explore);
            
                }    
            }
            
          

        }
    } // end act()
    
    // check to see if the ai should shoot an arrow
    boolean shouldShootArrow(){
        // leave room for better decision making in the future
        
        if(nextto("Wumpus"))
            return true;
        else return false;
    }
    
    // shoot the arrow down completely random passage
    // should we consider where we've been?
    void shootarrow(){
    
        cave temp = (cave)game.C.contents.get(location-1); 
        int numcaves = 0;
        for(int i = 0; i<6; i++){

            if(temp.passages[i] != null) numcaves++;
        }
        int direction =  game.R.nextInt(numcaves);
        
        for(int i = 0; i>6; i++){
            if(temp.passages[i] != null){
                direction--;
                if(direction < 0) 
                  gameio.putln("Carlsbad wants to shoot!");
                  // super.shootarrow(i);
                  // player.shootarrow has changed... 
                  // need to reimplement
            }
        }
    }
    
}
