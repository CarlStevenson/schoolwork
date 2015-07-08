
// who  M. Anthony Kapolka III
// what board.cc
// vers 1.5
// when Mon Oct 19 19:06:17 EDT 2009

using namespace std;

#include <iostream>
#include "board.h"

// ---------

ghostsquare::ghostsquare()
{
	col = none; // empty
	own = empty; // empty
}

void ghostsquare::display()
{
	// makes use of enum type numbering to map to char.

	char map[6] = {' ', ' ', 'A', 'a', 'B', 'b'};
	//              0    1    2    3    4    5


        // blue = 1, yellow = 2, playerA = 1, playerB = 3
        // uppercase = blue (good) ghost, lowercase = yellow (bad) ghost

        if ((int)col)
          cout <<  map[(int)col+(int)own];
	else
	  cout << map[0];
}


// ---------

ghostboard::ghostboard()
{
	lastmove[0] =		// where have all the for loops gone?
	lastmove[1] =
	lastmove[2] =
	lastmove[3] =
	capturecount[0][0] =
	capturecount[0][1] =
	capturecount[1][0] =
	capturecount[1][1] = 0;

}

ghostsquare ghostboard::getsq(int X, int Y)
{
   return board[X][Y];
}

/* ghostboard &ghostboard::operator=(ghostboard &b)
{
  int i, j;
  for (i = 0; i < 6; i++)
    for (j=0; j<6; j++)
      board[i][j] = b.board[i][j];
  for (i=0; i<2; i++)
    capturecount[i][j] = b.capturecount[i][j]; 
  for (i=0; i<4 ; i++)
    lastmove[i] = b.lastmove[i];
 return b; // correct?
} */

void ghostboard::display()
{
	// display the entire board
	
        //cout << endl << "uppercase = good (blue) ghosts, lowercase = bad (yellow) ghosts" << endl;
	cout << endl << "-------------" << endl;
	for (int row = 0; row < 6; row++)
	  {  for (int column = 0; column < 6; column++)
 		{
		     ghostsquare gh = board[row][column];
		     cout << '|';
                     gh.display();

		} // end for column
	     cout << '|' <<  endl;
	     cout << "-------------" << endl;
  	  } // end for row
}

/*
color ghostboard::getcolor(int X, int Y)
{

	return board[X][Y].getcolor();

} */

owner ghostboard::getowner(int X, int Y)
{

	return board[X][Y].getowner();

}

void ghostboard::getlastmove(int & x1, int & y1, int & x2, int & y2)
{
	x1 = lastmove[0];
	y1 = lastmove[1];
	x2 = lastmove[2];
	y2 = lastmove[3];
}

void ghostboard::getcapture(owner p, int & good, int & bad)
{
	int x;
	if (p == playerA) x = 0; else x = 1;

	good = capturecount[blue-1][x];   // blue-1 = 0
	bad  = capturecount[yellow-1][x]; // yellow-1 = 1

}

int ghostboard::placeghosts(char ghosts[4][2], owner player)
{
	// expects a 2D array of chars with 4 ghosts of each color,
	// denoted by b or y, for example:
        // 
        //     bbby
        //     yyyb

	int bottom;
	int left = 1;

	color c;

	int countyell = 0;	// check for correct number of each color
	int countblue = 0;

	if (player == playerA) 
	  { 
            bottom = 0; 
	  }

	if (player == playerB) 
	  { 
            bottom = 4; 
	  }

	for (int row = bottom; row < bottom+2; row++)
	  {  
	     for (int column = left; column < left+4; column++)
 		{
		   if (ghosts[column-1][row%2] == 'y')
		     { c = yellow;
                       countyell++; }
		   else
		     { c = blue;
		       countblue++; }

		     board[row][column].setcolor(c); 
		     board[row][column].setowner(player); 
		}
	  }

	if ((countblue != 4) || (countyell != 4)) return 0;
	 else return 1;
	
}

int ghostboard::move(int startX, int startY, int finishX, int finishY, owner p)
{

	// check for win.

	if ((startX < 0) || (startX > 5)
	 || (startY < 0) || (startY > 5)) return 0; // off board.

	if ((finishX < 0) || (finishX > 5)
	 || (finishY < 0) || (finishY > 5)) return 0; // off board.

	if (board[startX][startY].getowner() == p) 
	{
	   if (board[finishX][finishY].getowner() == p) 
		{ 
		  return 0; 
                }
	   else
	        if (board[finishX][finishY].getowner() == empty) 
	          {
		    board[finishX][finishY].setowner(board[startX][startY].getowner());
		    board[finishX][finishY].setcolor(board[startX][startY].getcolor());
		    board[startX][startY].mkempty();
		    lastmove[0] = startX;
		    lastmove[1] = startY;
		    lastmove[2] = finishX;
		    lastmove[3] = finishY;
		    return 1;
	          }
	        else // capture
		    { int sp;
		      if (p == playerA) sp = 1; else sp = 0; // could left shift player.
		      capturecount[(int)board[finishX][finishY].getcolor()-1][sp]++; 

//cout << "capturing " <<  sp << " " << board[finishX][finishY].getcolor()-1 << endl; 
//int g, b;
//getcapture((owner)0, g, b);
//cout << "Player A captured" << g << " " << b << endl;
//getcapture((owner)1, g, b);
//cout << "Player B captured" << g << " " << b << endl;

		      board[finishX][finishY].setowner(board[startX][startY].getowner());
		      board[finishX][finishY].setcolor(board[startX][startY].getcolor());
		      board[startX][startY].mkempty();
		      lastmove[0] = startX;
		      lastmove[1] = startY;
		      lastmove[2] = finishX;
		      lastmove[3] = finishY;
		      return 1;
		    }

	}
	else return 0;

}

