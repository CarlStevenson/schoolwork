// assign9.cpp
// 4/27/14
// Written by: Carl Stevenson
// General Trees
// Assignment 9.
// Problem being solved:
//   This program provides a menu-based interaction with the general
//   tree class.
// Program flow:
//   Prompt for menu input, then perform the requested function.
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

#include <iostream>
#include <cstring>
#include "general.cpp"

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
  generaltree tree;
  int mover;
  int choice;
  string menu = "The menu items are as follows:\n\n\t1. Search for an item in the tree\n\t2. Display all children of a node\n\t3. Display all siblings of a node\n\t4. Display all left siblings of a node\n\t5. Display all right siblings of a node\n\t6. Display the parent of a node\n\t7. Add a node to the tree\n\t8. Exit";
  bool go = true;

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"General Trees"<<endl;
  cout<<"This program takes input from the user to interface with an\n"
      <<"instantiated general tree.\n"<<endl;


  while(go)
  {
    choice = inCheck(menu);

    switch(choice)
    {
      case 1: 
        cout<<"You chose menu item 1."<<endl;
        mover = inCheck("Please enter the desired item to search for:");
        cout<<"You chose "<<mover<<endl;
        if(tree.search(mover))
        {
          cout<<"The item was found.\n"<<endl;
        } else
        {
          cout<<"The item was not found.\n"<<endl;
        }
        break;
      case 2:
        cout<<"You chose menu item 2."<<endl;
        mover = inCheck("Please enter the desired node:");
        cout<<"You chose "<<mover<<endl;
        tree.displayChildren(mover);
        cout<<endl;
        break;
      case 3:
        cout<<"You chose menu item 3."<<endl;
        mover = inCheck("Please enter the desired node:");
        cout<<"You chose "<<mover<<endl;
        tree.displaySiblings(mover);
        cout<<endl;
        break;
      case 4:
        cout<<"You chose menu item 4."<<endl;
        mover = inCheck("Please enter the desired node:");
        cout<<"You chose "<<mover<<endl;
        tree.displayLeftSiblings(mover);
        cout<<endl;
        break;
      case 5:
        cout<<"You chose menu item 5."<<endl;
        mover = inCheck("Please enter the desired node:");
        cout<<"You chose "<<mover<<endl;
        tree.displayRightSiblings(mover);
        cout<<endl;
        break;
      case 6:
        cout<<"You chose menu item 6."<<endl;
        mover = inCheck("Please enter the desired node:");
        cout<<"You chose "<<mover<<endl;
        tree.displayParent(mover);
        cout<<endl;
        break;
      case 7: 
        cout<<"You chose menu item 7."<<endl;
        tree.addNode();
        break;
      case 8:
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