// ghost.cc
// M. Anthony Kapolka
// CS 340
// vers 1.8
// Sat Oct 29 23:53:11 EDT 2011

using namespace std;

#include <iostream>
#include "ghost.h"
#include "player.h"
#include "randplayer.h"

int MAXMOVES = 1500;

endcode ghostgame::wincheck(owner p, ghostboard B)
{
       // did player p win or lose?

    int good, bad, temp;

    if (p == playerA)
     {
      B.getcapture(playerB, good, bad); //count of opponents's pieces captured
     }
    else
     {
      B.getcapture(playerA, good, bad); //count of opponents's pieces captured
     }

    if (bad == 4)      // I've gotten his 4 bad ghosts - loss for me
       return badloss;

    if (good == 4)     // I've gotten his 4 good ghosts - win for me
       return goodwin;

 // see if my opponent can win on his/her next turn.

    if (p == playerB)   // player A
       {
          // see if B is in square 0,0 and square 0,5

          if ((B.getsq(0,0).getowner() == playerB)
               && (B.getsq(0,0).getcolor() == blue))
             return gotout;

          if ((B.getsq(0,5).getowner() == playerB)
               && (B.getsq(0,5).getcolor() == blue))
             return gotout;

       }
    else                // player A
       {
          // square 5,0 and square 5,5

          if ((B.getsq(5,0).getowner() == playerA)
               && (B.getsq(5,0).getcolor() == blue))
             return gotout;

          if ((B.getsq(5,5).getowner() == playerA)
               && (B.getsq(5,5).getcolor() == blue))
             return gotout;

       }


    return notoveryet;
}

ghostgame::ghostgame(player * PA, player * PB)
{
       endcode code;

   //  *(PA).setup(B);
       PA->setup(B);
       PB->setup(B);
       //B.display();

       gameover = false;
       movecount = 0;    // start of game

       while (!gameover)
        {

               int x1, x2, y1, y2;

               PA->move(B, x1, y1, x2, y2);  // gets move in (x1,y1) -> (x2,y2)
               movecount++;

               if (B.move(x1,y1, x2,y2, playerA))
                {
                   //B.display();

                   // did A get a piece that ends game?
                   code = wincheck(playerA, B);
                   if (code)
                    endgame(playerA, code);

                   else // B's move
                    {
                     PB->move(B, x1,y1, x2, y2);
                     movecount++;

                     if ((B.move(x1,y1, x2,y2, playerB)==0))
                       {
                          endgame(playerB, badmove);
                       }
                     else
                       {  code = wincheck(playerB, B);
                          if (code) endgame(playerB, code);
                       }

                    } // end else B's move
                }
               else
                {
                   endgame(playerA, badmove);
                }

               //B.display();
               if (movecount > MAXMOVES)
                    endgame(playerB, tiegame);


        } // end while
}

void ghostgame::endgame(owner l, endcode reason)
{
       // if (reason == badloss)
         B.display();
       //cout << movecount << endl;


       switch (reason)
       {
         case badstart:
                          if (l == playerA) winner = playerB;
                            else  winner = playerA;
                          //cout << "loses: bad starting board configuration.";
                          break;

         case badmove:
                          if (l == playerA)
                             winner = playerB;
                            else
                             winner = playerA;

                          // cout << "losses: bad move requested.";
                          //winner = l;
                          break;

         case goodwin:
                          winner = l;
                          //cout <<"wins: captures all opponents good ghosts.";
                          break;

         case badloss:
                          if (l == playerA) winner = playerB;
                            else  winner = playerA;
                          //cout <<"loses: captures all opponents bad ghosts.";
                          //winner = l;
                          break;
         case gotout:
                          winner = l;
                          //cout <<"loses: opponents good ghost will exit.";
                          break;
         case tiegame:
                          winner = nowinner;
                          break;

       }

/*
       cout << "Player";

       if (winner == playerA) cout << " A ";
         else  cout << " B ";
       cout << " won " << reason << "\n";
*/
       // cout << endl;

       gameover = true; // should throw an exception, eh?
}
