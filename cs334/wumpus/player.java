
// Fall 2013
// modified in CS126 to integrate playercommand class
// this conversion needs to be completed

// Anthony Kapolka
// Fri Nov 18 12:27:16 EST 2011

// currently does not call methods from commandlist properly (in act)

import java.util.*;              // for ArrayList


class player extends container
{
    public int location;   // 1 to MAXCAVE
    public boolean SkipMe = false; // ensures only 1 move per loop through cavern

   // each player will need own traveled map of the cave
   // this may not be the best representation/idea
    boolean cavemap[][] = new boolean [game.MAXCAVE+1][game.MAXCAVE+1];
   // waste to loose column/row 0?

    int arrownumber = 3; // can't get more as implemented

    ArrayList<playercommand> commandlist;
      // This will hold all commands player can perform


    player()
    {
       playercommand pc;

       name = "Player";
       for (int x = 0; x<=game.MAXCAVE; x++)
         for (int y = 0; y<=game.MAXCAVE; y++)
           cavemap[x][y] = false;

       commandlist = new ArrayList<playercommand>();

       pc = new pickupcommand();
       commandlist.add(pc);
       pc = new dropcommand();
       commandlist.add(pc);
       pc = new throwcommand();
       commandlist.add(pc);
       pc = new shootcommand();
       commandlist.add(pc);
       pc = new inventorycommand();
       commandlist.add(pc);
       pc = new quitcommand();
       commandlist.add(pc);

    }

    void move(int finish)
    {
        // add player to finish cave
        cave tempc = (cave) game.C.contents.get(finish-1);
	tempc.putinto((gameobject)this);
        location = finish;
        // gameio.putln("Player in cave #" + location);
    }

    void move(int start, int finish)
    {
	// remove player from start cave 
        // start, finish == 1 to MAXCAVE

        cave tempc = (cave) game.C.contents.get(start-1);
	tempc.remove(this);
        move(finish);
        if (finish > start)
	  SkipMe = true; // avoid repeated movement

    }

    void moveindir(int direction)
    {
	// move movement to cave class
        // gameio.put("Your feet are glued to the floor! ");
        cave curcave = (cave)game.C.contents.get(location-1);

        // is this an illegal direction?
        if ((curcave.passages[direction] == null) || 
            (curcave.passages[direction].destination == 0))
          gameio.putln("No such tunnel! ");
        else 
        {
        // check for pit
        if (curcave.passages[direction].haspit)
          // fall in pit, end game.
         {
            if (name == "Player")
             {
              gameio.piteffect();
              System.exit(1); // game over - how rude!
             }
            else
             {
               // death of AIplayer 
               // remove him from game
             }
         }

	// check for bats (and other things)
        cave destcave = (cave)game.C.contents.get(
               curcave.passages[direction].leadsto(location)-1);

        if (destcave.has("Wumpus"))
         {
            gameio.wumpuseffect();
            System.exit(1); // game over - how rude!
         }

        if (destcave.has("Bats"))
         {
            // print message
            gameio.bateffect();

            this.cavemap[curcave.passages[direction].source]
                     [curcave.passages[direction].destination] = true;
            move(location, game.R.nextInt(game.MAXCAVE)+1);
         }
        else
         {
            // map/mark forward direction as travelled
            this.cavemap[curcave.passages[direction].source]
                     [curcave.passages[direction].destination] = true;

            move(location, curcave.passages[direction].leadsto(location));
         }
       } // end else is a legal tunnel
    }

    void pullpin()
      {
  	// check if the player has a grenade
  	// if so, "pull" it's pin 
      }

    void act()
    {
	// prompt user for action (e.g. "move")
        if (SkipMe)
            SkipMe=false;
        else
        {

          boolean badcommand;
          do {
          badcommand = false;

          System.out.print('[');
          for (int x = 0; x < commandlist.size(); x++)
            {
              System.out.print(commandlist.get(x).commandletter);
            }
          System.out.print(']');

          gameio.put("Command? ");
          String command = gameio.getstring();

        boolean didcmd = false; // process one command only

        // try new command first
        int x = 0;
        while ((didcmd == false) && (x < commandlist.size())) 
        {
            if (command.charAt(0) == commandlist.get(x).commandletter)
             { didcmd = true;
               commandlist.get(x).docommand();
             }
            x++;
        }

        // old way of processing commands
	// perform action
	// call move if moving
        if (didcmd == false)
          switch (command.charAt(0)) 
          {
            case 'n' : moveindir(0); break;
            case 'e' : moveindir(1); break;
            case 's' : moveindir(2); break;
            case 'w' : moveindir(3); break;
            case 'u' : moveindir(4); break;
            case 'd' : moveindir(5); break;
            //case 'p' : pullpin(); break;       // pull pin in grenade
            default  : gameio.putln("Commands are [neswudpaq]");
                       badcommand = true;
          } // end switch
          } while (badcommand);

        }

      super.act();
    } // end act()

}
