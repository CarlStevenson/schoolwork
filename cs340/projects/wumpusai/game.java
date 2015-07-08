// Anthony Kapolka
// Fri Nov 18 12:27:02 EST 2011

import java.util.Random;
 

// need to place random pits -> 

class game
{
    public static final int MAXCAVE    = 125;  // "constant"
    public static final int NUMBATS    = 5;  
    public static final int NUMTUNNELS = 10;  
    static cavern C; 
    static player P;
    static wumpus W;
    static Random R = new Random();
    static aiplayer A;

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
    A = new aiplayer();

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
          int pass = tempc.getrandomtunnel();
	  tempc.passages[pass].haspit = true;
gameio.put("Pit between ");
gameio.put(tempc.name);
gameio.put(" and ");
gameio.putln(C.contents.get(tempc.passages[pass].leadsto(cavewpit)-1).name);
        }
gameio.putln();


        // randomly place Wumpus
	tempc = (cave) C.contents.get(R.nextInt(MAXCAVE));
	tempc.putinto(W);            // but wumpus doesn't know
        W.location = tempc.idnumber;  // now wumpus knows
gameio.put("Wumpus placed in ");
gameio.putln(tempc.name);
gameio.putln();

	int startcave = R.nextInt(MAXCAVE);

        // don't start in cave w/ wumpus or bats
	while ((startcave == W.location) || (tempc.has("Bats"))) 
        {
	    startcave = R.nextInt(MAXCAVE);
	    tempc = (cave) C.contents.get(startcave);
        }

        P.move(startcave);

        // an example of generic object creation
        gameobject thing = new gameobject("Roku");
	tempc = (cave) C.contents.get(4);
	tempc.putinto(thing); 
	
	startcave = R.nextInt(MAXCAVE);
	
	// making a startcave for the aiplayer
	while ((startcave == W.location) || (tempc.has("Bats")) || (tempc.has("Player")))
	    {
	    startcave = R.nextInt(MAXCAVE);
	    tempc = (cave) C.contents.get(startcave);
        }

        A.move(startcave);


	C.act();  // play game

    }
}
