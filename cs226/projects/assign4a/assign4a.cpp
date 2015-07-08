// assign4a.cpp
// Written by: Carl Stevenson
// 3/12/14
// Introduction to Linked Lists
// Assignment 4a.
// Problem being solved:
//   Interfacing with the user in order to interact with the linked list class
//   defined in list.cpp. The interaction is menu-based.
// Flow of the program:
//   Loop, prompting for menu items and performing the corresponding function.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

// include required files
#include <cstring>
#include "list.cpp"

using namespace std;

// int inCheck(string prompt)
// inCheck loops until the user inputs a valid integer value.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
// re-used from assignment 2
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
  } // end while
  // return the gathered input.
  return choice;
} // end inCheck
// main function
// some code re-used from assignment 2

int main()
{
  // create the list to be interfacing with.
  list theList;
  // create the menu to be used in the program.
  string menu = "Your menu items are as follows\n\n\t1. Display the list\n\t2. Find an item in the list\n\t3. Insert an element in the list\n\t4. Delete an element from the list\n\t5. Display the number of elements in the list\n\t6. Exit\n\nPlease enter the number of the action you wish to take: ";

  // choice is the selection of menu item from the user.
  int choice;
  // go is a control variable for the menu loop.
  bool go = true;
  // mover is used to move input from the user to the list functions as 
  // neccessary. used as the added item in the insertElement function. it  
  // is assigned the desired item to be removed in the deleteElement function.
  // it is used as the item to find in the findElement function.
  int mover;

  // intro display

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Introduction to Linked Lists"<<endl;
  cout<<"This program takes input from the user to interface with\n"
      <<"an instantiated integer linked list.\n"<<endl;

  // loop until exit
  while(go)
  {
    
    //gather input
    choice = inCheck(menu);

    // process the input using a switch statement
    switch(choice)
    {
      // case 1 displays the items in the list
      case 1:
              cout<<"You chose menu item 1"<<endl;
              theList.displayList();
              break;
     // case 2 finds a user specified item in the list, if it contains it
      case 2:
              cout<<"You chose menu item 2"<<endl;
              mover = inCheck("Please enter the item to find in the list: ");
              cout<<"You chose "<<mover<<endl;
              mover = theList.findElement(mover);
              if(mover == 0)
              {
                cout<<"Item not found."<<"\n"<<endl;
              }else
              {
                cout<<"Item found in position "<<mover<<"\n"<<endl;
              }
              break;
      // case 3 adds a user specified item to the list
      case 3:
              cout<<"You chose menu item 3"<<endl;
              mover = inCheck("Please enter the item to add to the list: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              theList.insertElement(mover);
              break;
      // case 4 removes a user specified item from the list
      case 4:
              cout<<"You chose menu item 4"<<endl;
              mover = inCheck("Please enter the item to remove from the list: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              if(theList.deleteElement(mover))
              {
                cout<<mover<<" removed from the list.\n"<<endl;
              }else
              {
                cout<<mover<<" not found in the list.\n"<<endl;
              }
              break;
      // case 5 displays the number of elements in the list
      case 5:
              cout<<"You chose menu item 5"<<endl;
              cout<<"There are "<<theList.numberOfElements()
                  <<" elements in the list.\n"<<endl;
              break;
      // case 6 exits my prompt loop and ends the program.
      case 6:
              cout<<"You chose menu item 6"<<endl;
              cout<<"Exiting now.\n"<<endl;
              go = false;
              break;

    } // end switch
  } // end while
} // end main
