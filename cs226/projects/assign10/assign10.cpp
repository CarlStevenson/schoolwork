// assign10.cpp
// 5/1/14
// Written by: Carl Stevenson
// Graphs
// Assignment 10.
// Problem being solved:
//   This program provides a menu-based interaction with the graph class.
// Program flow:
//   Prompt for menu input, then perform the requested function.
// Program testing:
//   I have not tested my code and am unsure if it works properly.
//   I have not implemented a copy constructor in graph.cpp
// *************************************************************************

#include <cstring>
#include "graph.cpp"

using namespace std;

// int inCheck(string prompt)
// inCheck loops until the user inputs valid input.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
// re-used from previous assignments
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

    cout<<"You provided a non-integer or an integer not corresponding to "
        <<"a menu item. Please try again."<<endl;

    cin.clear();

    cin.ignore(120,'\n');
  } 
  // return the gathered input.
  return choice;
}


int main()
{
  int choice;
  int mover;
  graph theGraph;
  string menu = "The menu items are as follows:\n\n\t1. Do a depth-first search\n\t2. Do a topological sort\n\t3. Do a breadth-first search\n\t4. Exit\nPlease enter a menu item: ";
  bool go = true;

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Graphs"<<endl;
  cout<<"This program takes input from the user to interface with an\n"
      <<"instantiated graph.\n"<<endl;


  while(go)
  {
    choice = inCheck(menu);
    cout<<"Please enter a menu item: "<<endl;
    switch(choice)
    {
      case 1: 
        cout<<"You chose menu item 1."<<endl;
        mover = inCheck("Please enter the starting index:");
        cout<<"You chose "<<mover<<endl;
        theGraph.DFS(mover);
        cout<<endl;
        break;
      case 2:
        cout<<"You chose menu item 2."<<endl;
        theGraph.topoSort();
        cout<<endl;
        break;
      case 3:
        cout<<"You chose menu item 3."<<endl;
        mover = inCheck("Please enter the starting index:");
        cout<<"You chose "<<mover<<endl;
        theGraph.BFS(mover);
        cout<<endl;
        break;
      case 4:
        cout<<"Exiting now."<<endl;
        go = false;
        break;
      default:
        cout<<"You provided a value not corresponding to a menu item.\n"<<endl;
        break;

    }
  }
  return 0;
}