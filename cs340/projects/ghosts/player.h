// player.h
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:30:35 EST 1998

#ifndef player_h
#define player_h

#include "board.h"
#include <iostream>
using namespace std;

class player
{

public:

	player();
	player(owner p);

	virtual void setup(ghostboard & B) = 0;

	virtual void move(ghostboard B, int & X1, int & Y1, int & X2, int & Y2) = 0;

	owner whoiam;

private:


};

#endif

