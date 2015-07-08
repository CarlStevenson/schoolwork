// ghost.h - this *plays* the game.
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:30:35 EST 1998

#ifndef ghost_h
#define ghost_h

#include "board.h"
#include "player.h"

//int MAXMOVES = 100;

enum endcode { notoveryet, badstart, badmove, goodwin, badloss, gotout, tiegame};

class ghostgame 
{

	public:

		ghostgame();
		ghostgame(player * PA, player * PB);

		owner winner;	// who won the game	

	private:

	  void    endgame(owner, endcode);

	  endcode wincheck(owner, ghostboard);

          int	  movecount;  // how many moves have been played

	ghostboard B;

	bool	gameover;	// should throw exceptions, eh?


};  // end ghostgame

#endif
