// board.h - includes two classes
// M. Anthony Kapolka
// CS 340
// vers 1.0
// Wed Feb 25 01:29:10 EST 1998

#ifndef board_h
#define board_h

enum color { none , blue, yellow }; 
enum owner { empty, playerA, playerB = 3, nowinner }; 

class ghostsquare
{
	public:
		ghostsquare();	// default squares 
		void display();

		void setcolor(color c) { col = c; };
		void setowner(owner o) { own = o; };

		owner const getowner() { return own; }
		color const getcolor() { return col; }

		void mkempty() { setcolor(none); 
			    setowner(empty); };

	private:
		color col; 
		owner own; 

};  // end ghostsquare


class ghostboard
{

	public:

		ghostboard();

		void display();


	  owner getowner(int X, int Y);

	  int	move(int startX, int startY, int finishX, int finishY, owner p);

	  int   placeghosts(char ghosts[4][2], owner o);

	  void  getlastmove(int & startX, int & startY, 
			    int & finishX, int & finishY);

	   void getcapture(owner p, int & good, int & bad);

          ghostsquare getsq(int X, int Y);

	ghostsquare 	board [6][6];

	private:


	// for each player, keep a count of the number of each color
	// captured. 

	int   	capturecount[2][2];	// [# captured][player]

	int 	lastmove[4]; // x1 y1 x2 y2 

};  // end ghostboard

#endif
