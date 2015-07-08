// Anthony Kapolka
// Fri Nov 18 12:27:16 EST 2011
// Edited by Carl Stevenson for clarity

class player extends container{
	
	public int location;   // 1 to MAXCAVE
    public boolean SkipMe = false; // ensures only 1 move per loop through cavern

    player(){
       
    	name = "Player";
    }

    void move(int finish){
        
    	// add player to finish cave
        cave tempc = (cave) game.C.contents.get(finish-1);
        tempc.putinto((gameobject)this);
        location = finish;
        // gameio.putln("Player in cave #" + location);
    }

    void move(int start, int finish){
    	// remove player from start cave 
        // start, finish == 1 to MAXCAVE

        cave tempc = (cave) game.C.contents.get(start-1);
        tempc.remove(this);
        move(finish);
        if (finish > start)
        	SkipMe = true; // avoid repeated movement

        // map/mark travelled for return passage to true
        // sometime marks second passage incorrectly
        // move all movement code to cave class.
        tempc = (cave) game.C.contents.get(finish-1);
        for (int p=0; p<6; p++){
        	
        	if ((tempc.passages[p].destination == start)
        			&& !tempc.passages[p].travelled){
        		tempc.passages[p].travelled = true;
        		break;
        	}
        }

    }

    void moveindir(int direction){
    	
    	// move movement to cave class
        // gameio.put("Your feet are glued to the floor! ");
        cave curcave = (cave)game.C.contents.get(location-1);

        // is this an illegal direction?
        if (curcave.passages[direction].destination == 0)
        	gameio.putln("No such tunnel! ");
        else{
        	
        	// check for bats (and other things)
        	cave destcave = (cave)game.C.contents.get(
        			curcave.passages[direction].destination-1);

        	if (destcave.has("Wumpus")){
        		playerdeath();
        	}

        	if (destcave.has("Bats")){
            
        		// print message
        		gameio.bateffect();

        		// this messes up mapping b/c 
        		// the back edge still gets set 
        		// in this move function call
        		// so I might as well also map the 
        		// front edge...
        		curcave.passages[direction].travelled = true;
        		move(location, game.R.nextInt(game.MAXCAVE));
        	}
        	else{
        		// map/mark forward direction as travelled
        		curcave.passages[direction].travelled = true;

        		move(location, curcave.passages[direction].destination);
        	}
        } // end else is a legal tunnel
    }

    void act(){
    	
    	// prompt user for action (e.g. "move")
    	if (SkipMe)
    		SkipMe=false;
        else{
          
        	// if the Wumpus is in the same place as you, sorry bucko!
        	if(game.W._position == location){
        		
        		playerdeath();
        	}
        	gameio.put("Command? ");
        	String command = gameio.getstring();

        	// perform action
        	// call move if moving
        	// call pickUp if picking an item up
        	// call throwItem if throwing an item
        	
        	switch (command.charAt(0)){
        	
        	case 'n' : moveindir(0); break;
        	case 'e' : moveindir(1); break;
        	case 's' : moveindir(2); break;
        	case 'w' : moveindir(3); break;
        	case 'u' : moveindir(4); break;
        	case 'd' : moveindir(5); break;
        	case 'p' : this.pickUp(); break;
        	case 't' : this.throwItem(); break;
        	case 'q' : System.exit(1); break;
        	} // end switch

        }
    } // end act()

    public void pickUp() {
    	cave curcave = (cave)game.C.contents.get(location-1);
    	if ( curcave.contents.size() == 1 ) { // theres only the player, no items
    		gameio.putln("There's nothing to pickup.");
    	} else {
    		for ( int i = 0; i < curcave.contents.size(); i++ ) {
    			if ( curcave.contents.get( i ).name != "Player" ) 
    				gameio.putln(i + " " + curcave.contents.get( i ).name );
    		}
    		gameio.put("What would you like to pickup? ");
    		int pickupint = Integer.parseInt(gameio.getstring());
    		this.contents.add(curcave.contents.remove( pickupint ) );
    	}

    }

    public void throwItem() {
    	if ( this.contents.isEmpty() ) {
    		gameio.putln("You have nothing to throw.");
    	} else {
    		for ( int i = 0; i < this.contents.size(); i++ ) {
    			gameio.putln(i + " " + this.contents.get( i ).name);
    		}
    		gameio.put("What would you like to throw? ");
    		int throwingnum = Integer.parseInt(gameio.getstring());
		    gameobject throwing = this.contents.remove( throwingnum );
		    gameio.putln();
		    gameio.put("Which direction? ");
		    String command = gameio.getstring();
		    switch (command.charAt(0)){
		    
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
    	cave curcave = (cave)game.C.contents.get(location-1);

        // is this an illegal direction?
    	if (curcave.passages[direction].destination == 0)
    		gameio.putln("No such tunnel! ");
        else{
        	// check for bats (and other things)
        	cave destcave = (cave)game.C.contents.get(
        			curcave.passages[direction].destination-1);

        	if (destcave.has("Wumpus") && throwing.name == "grenade" ){
        		
        		gameio.wumpuskill();
        		System.exit(1); // game over - you're a winner!
        	}
        	else if (throwing.name == "grenade" ){ // just empty grenade
        		gameio.grenadetoss();
        	}

        } // end else is a legal tunnel
    }
    
    public void playerdeath(){
    	gameio.wumpuseffect();
    	// game over - try again next time!
    	System.exit(1);
    }

}
