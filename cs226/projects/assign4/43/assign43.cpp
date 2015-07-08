// assign43.cpp
// Written by: Carl Stevenson
// 3/24/14
// Linear and Circular Linked Lists
// Assignment 43.
// Problem being solved:
//   Interfacing with the user in order to interact with the circular linked 
//   list class defined in listc.cpp. In order to play the Josephus game
// Flow of the program:
//   Prompt for M and N, then play the Josephus game.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// Edited from code from assignment 41.
// *************************************************************************

// include required files
#include <cstring>
#include "listj.cpp"

using namespace std;

// int inCheck(string prompt)
// inCheck loops until the user inputs a valid integer value.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
// re-used & edited from assignment 2
// ************************************************************************* 

int inCheck(string prompt)
{
  int choice;
  // loop until valid input given.
  while(true)
  {
    cout<<prompt<<endl;

    cin >> choice;

    if(cin.good())
    {
      break;
    }

    cout<<"You provided a non-integer. Please try again."<<endl;

    cin.clear();

    cin.ignore(120,'\n');
  } // end while
  // return the gathered input.
  return choice;
} // end inCheck

int main()
{
  list theList;
  int people;
  int passes;
  int winner;
  cout<<"Carl Stevenson"<<endl;
  cout<<"Linear and Circular Linked Lists"<<endl;
  cout<<"This program takes N and M from a user and displays the result";
  cout<<"of the Josephus game with N players and M passes.\n"<<endl;

  people = inCheck("Please enter the amount of people in the game:");
  if(people<1)
  {
    cout<<"Invalid number of people."<<endl;
    assert(people>=1);
  }
  cout<<"You chose "<<people<<" people."<<endl;
  passes = inCheck("Please enter the number of passes required for elimination:");
  if(passes<0)
  {
    cout<<"Invalid number of passes."<<endl;
    assert(passes>=0);
  }
  cout<<"You chose "<<passes<<" passes per elimination."<<endl;

  // populate the list
  for(int i=1; i<=people; i++)
  {
    theList.insertElement(i);
  }
  // play the Josephus game
  winner = theList.Josephus(passes);
  cout<<"\nPlayer "<<winner<<" wins!"<<endl;

  return 0;
} // end main