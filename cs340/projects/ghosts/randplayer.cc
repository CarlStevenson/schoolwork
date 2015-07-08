// player.cc
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:30:35 EST 1998


using namespace std;
#include <iostream>
#include "randplayer.h"

randplayer::randplayer(owner ow) 
{ 
	whoiam = ow;
}



void randplayer::setup(ghostboard & B) 
{ 
	// static positioning of initial pieces is a bad plan.
	char start[4][2] = {'b', 'y', 'b', 'y', 'b', 'y', 'b', 'y'};

	B.placeghosts(start, whoiam);

}

void randplayer::move(ghostboard B, int & X1, int & Y1, int & X2, int & Y2)
{

	// example of getting last move
	B.getlastmove(X1, Y1, X2, Y2); 

        // cout << endl << "opponent's move was (" 
        //   << X1 << "," << Y1 << ") to (" << X2 << "," << Y2 << ")" << endl;

	// find a legal piece

	int one, two;
	B.getcapture(whoiam, one, two);
	int left = 8 - (one + two);

badbadbad:

	int dir = (int)((R.rnd()*left)+1);

	// find the dir'th piece I own

	int count = 0;
	for (X1 = 0; X1 < 6; X1++)	
	  for (Y1 = 0; Y1 < 6; Y1++)	
            {
		if (B.getowner(X1,Y1) == whoiam) { count++; }
		if (count == dir) goto mygod;
            }   

mygod:
	// choose direction to move it to.

	X2 = X1; Y2 = Y1; //illegal move
        count = 0;
	do {
 		count++;
	        if (count > 100) goto badbadbad;

		int dir = (int)((R.rnd()*4)+1);

		switch (dir)
		{
		    case 1:  X2 = X1 - 1;  Y2 = Y1; break;
		    case 2:  X2 = X1 + 1;  Y2 = Y1; break;
		    case 3:  X2 = X1;  Y2 = Y1 - 1; break;
		    case 4:  X2 = X1;  Y2 = Y1 + 1; break;
		}

	        // cout << ":";

	   } while ( (X2 < 0) || (X2 >= 6) ||
		     (Y2 < 0) || (Y2 >= 6) ||
                     (B.getowner(X2,Y2) == whoiam));

        //cerr << "A random response is (" 
        //<< X1 << "," << Y1 << ") to (" << X2 << "," << Y2 << ")" << endl;

}

void randplayer::winLose( owner winner ) { }
