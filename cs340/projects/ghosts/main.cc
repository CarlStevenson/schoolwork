#include <iostream>
#include "randplayer.h"
//#include "yourPlayer.h"
#include "player.h"
#include "ghost.h"
#include "board.h"

using namespace std;

int main() {
	owner w;
	endcode r;

	randplayer * j = new randplayer(playerA);
	randplayer * k = new randplayer(playerB);

        // instead replace k by saying:
        // yourPlayer * k= new yourPlayer(playerB);

	ghostgame G(j, k);

	delete j;
	delete k;
	return 0;
} // end main

