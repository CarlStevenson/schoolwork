// assign8.cpp
// Written by: Carl Stevenson
// 4/25/14
// Binary Search Trees
// Assignment 8.
// Problem being solved:
//   assign8.cpp uses the binary search tree defined in bst.cpp to provide
//   menu based interaction with a instance of the binary search tree.
// Flow of the program:
//   Prompt for menu input, then perform the requested function.
// Program testing:
//   I have not tested my program extensively and am unsure if it works
//   properly.
// *************************************************************************

#include <iostream>
#include <cstring>
#include "bst.cpp"
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
  BinarySearchTree tree;
  int mover;
  int choice;
  string menu = "The menu items are as follows:\n\n\t1. Add an element to the tree\n\t2. Remove an element from the tree\n\t3. Find the minimum value\n\t4. Find the maximum value\n\t5. Find a specific item\n\t6. Print an inorder traversal of the tree\n\t7. Print the empty status of the tree\n\t8. Exit";
  bool go = true;

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Binary Search Trees"<<endl;
  cout<<"This program takes input from the user to interface with an\n"
      <<"instantiated integer binary search tree.\n"<<endl;


  while(go)
  {
    choice = inCheck(menu);

    switch(choice)
    {
      case 1: 
        cout<<"You chose menu item 1."<<endl;
        mover = inCheck("Please enter the item to add.");
        cout<<"You chose "<<mover<<endl;
        if(tree.insert(mover))
        {
          cout<<mover<<" added to the tree.\n"<<endl;
        }else
        {
          cout<<"The value was not added to the tree.\n"<<endl;
        }
        break;
      case 2:
        cout<<"You chose menu item 2."<<endl;
        mover = inCheck("Please enter the item to remove.");
        cout<<"You chose "<<mover<<endl;
        if(tree.remove(mover))
        {
          cout<<mover<<" removed from the tree.\n"<<endl;
        }else
        {
          cout<<"The tree is empty.\n"<<endl;
        }
        break;
      case 3:
        cout<<"You chose menu item 3."<<endl;
        if(tree.FindMin(mover))
        {
          cout<<"The minimum value is: "<<mover<<"\n"<<endl;
        } else 
        {
          cout<<"The tree is empty.\n"<<endl;
        }
        break;
      case 4:
        cout<<"You chose menu item 4."<<endl;
        if(tree.FindMax(mover))
        {
          cout<<"The maximum value is: "<<mover<<"\n"<<endl;
        } else 
        {
          cout<<"The tree is empty.\n"<<endl;
        }
        break;
      case 5:
        cout<<"You chose menu item 5."<<endl;
        mover = inCheck("Please enter the item to find.");
        cout<<"You chose "<<mover<<endl;
        if(tree.FindItem(mover))
        {
          cout<<mover<<" was found in the tree.\n"<<endl;
        }else
        {
          cout<<mover<<" was not found in the tree.\n"<<endl;
        }
        break;
      case 6:
        cout<<"You chose menu item 6."<<endl;
        tree.printTree();
        cout<<endl;
        break;
      case 7: 
        cout<<"You chose menu item 7."<<endl;
        if(tree.isEmpty())
        {
          cout<<"The tree is empty.\n"<<endl;
        } else
        {
          cout<<"The tree is not empty.\n"<<endl;
        }
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