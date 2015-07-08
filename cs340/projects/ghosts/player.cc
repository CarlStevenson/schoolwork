// player.cc
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:30:35 EST 1998

using namespace std;

#include <iostream>
#include "player.h"

player::player()
{

};

player::player(owner p)
{ cout << "Creating Player!" << endl;
  whoiam = p;
};

