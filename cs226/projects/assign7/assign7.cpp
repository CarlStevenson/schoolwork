// assign7.cpp
// Written by: Carl Stevenson
// 4/14/14
// Binary Trees
// Assignment 7.
// Problem being solved:
//   Providing a menu for a user to interact with a binary tree structure.
// Flow of the program:
//   Prompt for menu input, then perform the requested function
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// Edited from code provided by Dr. Bracken
// *************************************************************************

#include <iostream>
#include <cstring>
#include "binarytree.cpp"

using namespace std;

// int inCheck(string prompt)
// inCheck loops until the user inputs valid input.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
// re-used from pervious assignments
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
  binarytree theTree;
  int mover;
  int choice;
  string menu = "The menu items are as follows:\n\n\t1. Add an element to the tree\n\t2. Remove an element from the tree\n\t3. Display a level of the tree\n\t4. Display the in order traversal of the tree\n\t5. Display the preorder traversal of the tree\n\t6. Display the postorder traversal of the tree\n\t7. Print the height of the tree\n\t8. Print the number of elements in the tree\n\t9. Display if the tree is full\n\t10. Exit";
  bool go = true;


  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Binary Trees"<<endl;
  cout<<"This program takes input from the user to interface with an\n"
      <<"instantiated binary tree.\n"<<endl;


  while(go)
  {
    choice = inCheck(menu);

    switch(choice)
    {
      case 1: 
        cout<<"You chose menu item 1."<<endl;
        mover = inCheck("Please enter the item to add.");
        cout<<"You chose "<<mover<<endl;
        if(mover<0)
        {
          cout<<"You must choose a positive integer, please try again.\n"<<endl;
        }else if(theTree.add(mover))
        {
          cout<<mover<<" added to the tree.\n"<<endl;
        }else
        {
          cout<<"The tree is full.\n"<<endl;
        }
        break;
      case 2:
        cout<<"You chose menu item 2."<<endl;
        cout<<"Deleting an element from the tree."<<endl;
        if(theTree.remove(mover))
        {
          cout<<mover<<" removed from the tree.\n"<<endl;
        }else
        {
          cout<<"The tree is empty.\n"<<endl;
        }
        break;
      case 3:
        cout<<"You chose menu item 3."<<endl;
        mover = inCheck("Please enter the level to display.");
        cout<<"You chose "<<mover<<endl;
        if(mover<0)
        {
          cout<<"You must choose a positive integer.\n"<<endl;
        }else
        {
          theTree.displayLevel(mover);
          cout<<endl;
        }
        break;
      case 4:
        cout<<"You chose menu item 4."<<endl;
        theTree.inOrder();
        cout<<endl;
        break;
      case 5:
        cout<<"You chose menu item 5."<<endl;
        theTree.preOrder();
        cout<<endl;
        break;
      case 6:
        cout<<"You chose menu item 6."<<endl;
        theTree.postOrder();
        cout<<endl;
        break;
      case 7: 
        cout<<"You chose menu item 7."<<endl;
        cout<<"The height of the tree is "<<theTree.theHeight()<<"\n"<<endl;
        break;
      case 8:
        cout<<"You chose menu item 8."<<endl;
        cout<<"The number of elements in the tree is "<<theTree.numberOfElements()<<"\n"<<endl;
        break;
      case 9:
        cout<<"You chose menu item 9."<<endl;
        if(theTree.isFull())
        {
          cout<<"The tree is full.\n"<<endl;
        }else
        {
          cout<<"The tree is not full.\n"<<endl;
        }
        break;
      case 10:
        cout<<"Exiting now."<<endl;
        go = false;
        break;

    }


  }

  return 0;
}