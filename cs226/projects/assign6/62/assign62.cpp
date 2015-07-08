// assign62.cpp
// Written by: Carl Stevenson
// 4/9/14
// Queues
// Assignment 62.
// Problem being solved:
//   Providing a menu interface with a user to interact with a array based
//   queue class.
// Flow of the program:
//   Prompt user for input and perform the appropriate function.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
//   queuea.cpp has some bugs that are addressed in its header.
// Edited from code provided by Dr. Bracken
// *************************************************************************

#include <fstream>
#include <cassert>
#include <iostream>
#include "queuea.h"
#include <string>

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
  } // end while
  // return the gathered input.
  return choice;
} // end inCheck

// main function
// some code re-used from assignment 4a
int main()
{
  queueClass theQueue;
  string prompt = "Your menu items are as follows:\n\t1. Enqueue an item\n\t2. Dequeue and print an item\n\t3. Print the front of the queue\n\t4. Print the contents of the queue\n\t5. Print empty status\n\t6. Exit\n\nPlease enter the number of the action you wish to take: ";

  // choice is the selection of menu item from the user.
  int choice;
  // go is a control variable for the menu loop.
  bool go = true;
  // success is the success variable
  bool success;
  // mover is used to move input from the user to the queue functions
  int mover;

  // intro display

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Queues"<<endl;
  cout<<"This program takes input from the user to interface with a queue.\n"<<endl;

  // loop until exit

  while(go){

    //gather input
    choice = inCheck(prompt);

    // process the input using a switch statement
    switch(choice)
    {
      case 1:
        cout<<"You chose menu item 1."<<endl;
        mover = inCheck("Please enter an item to enqueue: ");
        cout<<"You chose "<<mover<<endl;
        theQueue.QueueInsert(mover, success);
        if(success)
        {
          cout<<mover<<" added to the queue.\n"<<endl;
        }else
        {
          cout<<"Unable to add "<<mover<<" to the queue.\n"<<endl;
        }
        break;
      case 2:
        cout<<"You chose menu item 2."<<endl;
        theQueue.QueueDelete(mover, success);
        if(success)
        {
          cout<<mover<<" removed from the queue.\n"<<endl;
        }else
        {
          cout<<"No items to remove.\n"<<endl;
        }
        break;
      case 3:
        cout<<"You chose menu item 3."<<endl;
        theQueue.GetQueueFront(mover, success);
        if(success)
        {
          cout<<"The front of the queue is: "<<mover<<"\n"<<endl;
        }else
        {
          cout<<"The queue is empty.\n"<<endl;
        }
        break;
      case 4:
        cout<<"You chose menu item 4."<<endl;
        theQueue.QueuePrint();
        cout<<endl;
        break;
      case 5:
        cout<<"You chose menu item 5."<<endl;
        if(theQueue.QueueIsEmpty())
        {
          cout<<"The queue is empty.\n"<<endl;
        }else
        {
          cout<<"The queue is not empty.\n"<<endl;
        }
        break;
      case 6:
        cout<<"You chose menu item 6."<<endl;
        cout<<"Exiting now."<<endl;
        go = false;
        break;
    }
  }
}//end of main
