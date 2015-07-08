// assign2.cpp
// Written by: Carl Stevenson
// 2/26/14
// Class Templates
// Assignment 2.
// Problem being solved:
//   Interfacing with the user in order to interact with the collection class
//    to perform rudimentory functions on a collection of items. 
// Flow of the program:
//   Loop, prompting for menu items and performing the corresponding function.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

// include the required header files
#include <cassert>
#include <iostream>
#include "collection.cpp"


using namespace std;


// int inCheck(string prompt)
// inCheck loops until the user inputs valid input.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
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

// main function
// runs the menu and drives the collection class.
// *************************************************************************

int main()
{
  // create the two collections to be used.
  Collection<int> collection1;
  Collection<int> collection2;
  // store the menu as a string for easier access and compactness in later code.
  string menu = "The menu items are as follows:\n\n\t1. Add an item to collection 1.\n\t2. Add an item to collection 2.\n\t3. Remove an item from collection 1.\n\t4. Remove an item from collection 2.\n\t5. Display collection 1.\n\t6. Display collection 2.\n\t7. Compare collection 1 and collection 2.\n\t8. Assign collection 1 to collection 2.\n\t9. Assign collection 2 to collection 1.\n\t10. Display if collection 1 is empty.\n\t11. Display if collection 2 is empty.\n\t12. Make collection 1 empty.\n\t13. Make collection 2 empty.\n\t14. Find an item in collection 1.\n\t15. Find an item in collection 2.\n\t16. Exit\n\nPlease enter the number of the action you wish to take: ";

  // choice is the selection of menu item from the user.
  int choice;
  // go is a control variable for the menu loop.
  bool go = true;
  // mover is used to move input from the user to the collection functions as neccessary.
  // used as the added item in the insert function. it is assigned the removed item in
  // the remove function. it is used as the item to find in the contains function.
  int mover;

  // intro display

  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Class Templates"<<endl;
  cout<<"This program takes input from the user to interface with two\n"
      <<"instantiated integer collections.\n"<<endl;

  // loop until exit
  while(go)
  {
    
    //gather input
    choice = inCheck(menu);

    // process the input using a switch statement
    switch(choice)
    {
      // case 1 and 2 are the insert functions
      case 1:
              // get the item to add
              mover = inCheck("Please enter the item to add to collection 1: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              // try to insert the item
              if(!collection1.insert(mover))
              {
                cout<<"Collection 1 is full.\n"<<endl;
              }
              break;
      case 2:
              mover = inCheck("Please enter the item to add to collection 2: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              if(!collection2.insert(mover))
              {
                cout<<"Collection 2 is full.\n"<<endl;
              }
              break;
      // case 3 and 4 are the remove functions
      case 3:
              // try and remove the last item
              if(collection1.remove(mover))
              {
                cout<<mover<<" removed from collection 1.\n";
              }
              else
              {
                cout<<"There is nothing to remove from collection 1.\n"<<endl;
              }
              
              break;
      case 4:
              if(collection1.remove(mover))
              {
                cout<<mover<<" removed from collection 2.\n";
              }
              else
              {
                cout<<"There is nothing to remove from collection 2.\n"<<endl;
              }

              break;
      // case 5 and 6 are the display functions
      case 5:
              cout<<"Collection 1: "<<endl;
              // display the collection
              collection1.display();
              break;
      case 6:
              cout<<"Collection 2: "<<endl;
              collection2.display();
              break; 
      // case 7 is the equality function
      case 7:
              // compare the two collections
              if(collection1.operator==(collection2))
              {
                cout<<"Collection 1 is equal to Collection 2."<<endl;
              }
              else
              {
                cout<<"Collection 1 is not equal to Collection 2."<<endl;
              }
              break;
      // case 8 and 9 are the assignment functions
      case 8:
              // assign collection1 to collection2
              collection2.operator=(collection1);
              cout<<"Collection 1 assigned to Collection 2.\n"<<endl;
              break;
      case 9:
              collection1.operator=(collection2);
              cout<<"Collection 2 assigned to Collection 1.\n"<<endl;
              break;
      // case 10 and 11 are the checks of empty collections
      case 10:
              // check to see if the collection is empty
              if(collection1.isEmpty())
              {
                cout<<"Collection 1 is empty.\n"<<endl;
              }
              else
              {
                cout<<"Collection 1 is not empty.\n"<<endl;
              }
              break;
      case 11:
              if(collection2.isEmpty())
              {
                cout<<"Collection 2 is empty.\n"<<endl;
              }
              else
              {
                cout<<"Collection 2 is not empty.\n"<<endl;
              }
              break;
      // case 12 and 13 are the functions to clear the collections
      case 12:
              // clear the collection
              collection1.makeEmpty();
              cout<<"Collection 1 emptied.\n"<<endl;
              break;
      case 13:
              collection2.makeEmpty();
              cout<<"Collection 2 emptied.\n"<<endl;
              break;
      // case 14 and 15 are the functions to find an item in the collections
      case 14:
              // get the item to find in the collection
              mover = inCheck("Please enter the item to find in collection 1: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              // check to see if that item is in the collection
              if(collection1.contains(mover))
              {
                cout<<"Collection 1 contains "<<mover<<"\n"<<endl;
              }
              else
              {
                cout<<"Collection 1 does not contain "<<mover<<"\n"<<endl;
              }
              break;
      case 15:
              mover = inCheck("Please enter the item to find in collection 2: ");
              cout<<"You chose "<<mover<<"\n"<<endl;
              if(collection2.contains(mover))
              {
                cout<<"Collection 2 contains "<<mover<<"\n"<<endl;
              }
              else
              {
                cout<<"Collection 2 does not contain "<<mover<<"\n"<<endl;
              }
              break;
      // case 16 exits the menu loop
      case 16: 
              cout<<"Exiting now."<<endl; 
              go = false; 
              break;
      default: 
              go = false; 
              break;

    }
  }

  return 0;

} // end main
