// player.h
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:30:35 EST 1998


#ifndef randplayer_h
#define randplayer_h

#include "myrandom.h"
#include "board.h"
#include "player.h"


class randplayer : public player
{

public:

	randplayer(owner o);

	virtual void setup(ghostboard & B);

	virtual void move(ghostboard B, int & X1, int & Y1, int & X2, int & Y2);
	virtual void winLose( owner winner );

private:

	myrandom R;

};

#endif

