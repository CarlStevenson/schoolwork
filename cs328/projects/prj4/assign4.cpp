// assign4.cpp
// 12/1/14
// Written by: Carl Stevenson
// Graphs
// Assignment 4.
// Uses code from Assignment 10 of CS 226
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

  


  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Graphs"<<endl;
  cout<<"This program takes input from the user to create a directed "<<endl;
  cout<<"graph, and then detects and prints all cycles in that graph."<<endl;
  
  graph theGraph;

  // perform the depth first search to detect cycles from index 0
  theGraph.DFS(0);
  // call copy constructor, and perform the same operation
  cout<<"Calling copy constructor and printing cycles: "<<endl;
  graph secondGraph(theGraph);
  secondGraph.DFS(0);
  cout<<"Exiting now."<<endl;  
  return 0;
}
