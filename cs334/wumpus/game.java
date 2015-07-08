
// Wumpus Hunt driver

// Composite of code written in
// Kapolka's CS 126 / CS 225 / CS 340 classes
// 2011 - 2013

import java.util.Random;

// some initialization of things is not optimised for game play 
// designed to assist testing.  
// (e.g. too many objects, object placed with player, etc.)

class game
{
    public static final int MAXCAVE    = 125;  // "constant"
    public static final int NUMBATS    = 5;  
    public static final int NUMTUNNELS = 10;  
    static cavern C; 
    static player P;
    static wumpus W;
    static carlplayer Carlsbad;
    static Random R = new Random();

    static tunnel adjmatrix[][];  // holds tunnels during cavern construction

    static torch T = new torch(); // this should not be here


    public static void main(String[] args)
    {

	cave tempc; 

	gameio.putln("Hello Wumpus adventurer.");

//gameio.putln(MAXCAVE);

	// this is cludgy
        adjmatrix = new tunnel[MAXCAVE][MAXCAVE];
        for (int x = 0; x < MAXCAVE; x++)
          for (int y = 0; y < MAXCAVE; y++)
            {
              adjmatrix[x][y] = null;
            }

    C = new cavern();
    P = new player();
    W = new wumpus();
    Carlsbad = new carlplayer();

	for (int b=1; b<=NUMBATS; b++)
        {
          gameobject bats = new gameobject("Bats");
	  tempc = (cave) C.contents.get(R.nextInt(MAXCAVE));
	  tempc.putinto(bats); 
gameio.put("Bats placed in ");
gameio.putln(tempc.name);
        }
gameio.putln();

        // place pits in tunnels
	for (int t=1; t<=NUMTUNNELS; t++)
        {
          int cavewpit = R.nextInt(MAXCAVE); // already 0-MAXCAVE-1
	  tempc = (cave) C.contents.get(cavewpit); 
gameio.put("Pit between ");
gameio.put(tempc.name);
          int pass = tempc.getrandomtunnel();
	  tempc.passages[pass].haspit = true;
gameio.put(" and ");
gameio.putln(C.contents.get(tempc.passages[pass].leadsto(cavewpit)-1).name);
        }
gameio.putln();


        // randomly place Wumpus
	tempc = (cave) C.contents.get(0);
	tempc.putinto(W);            // but wumpus doesn't know
        W._position= tempc.idnumber;  // now wumpus knows
gameio.put("Wumpus placed in ");
gameio.putln(tempc.name);
gameio.putln(W._position);
gameio.putln();

        // randomly place Carlsbad
	int startcave = R.nextInt(MAXCAVE);
	tempc = (cave) C.contents.get(startcave);
	while ((startcave == W._position) || (tempc.has("Bats"))) 
        {
	    startcave = R.nextInt(MAXCAVE);
	    tempc = (cave) C.contents.get(startcave);
        }
        Carlsbad.move(2);
gameio.put("Carlsbad placed in ");
gameio.putln(tempc.name);
gameio.putln(Carlsbad.location);
gameio.putln();

	startcave = R.nextInt(MAXCAVE);

        // don't start in cave w/ wumpus or bats
	while ((startcave == W._position) || (tempc.has("Bats"))) 
        {
	    startcave = 0;
	    tempc = (cave) C.contents.get(startcave);
        }

if (tempc.has("Bats"))
  gameio.put("placed with bats");

        P.move(2);

        // put the wumpusfinder into the cavern with the player (for now)
        wumpusfinder wf = new wumpusfinder();
	tempc = (cave) C.contents.get(startcave);
        wf.location = startcave;
	tempc.putinto(wf); 

    System.out.println(Carlsbad.cavemap);

	C.act();  // play game

    }
}
