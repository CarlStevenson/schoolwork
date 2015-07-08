// assign52.cpp
// Carl Stevenson
// 3/31/14
// Stacks
// Assignment 52
// Problem being solved:
//   Creating a copyStack function ro make a copy of a filled stack,
//   defined by the user.
// Program flow:
//   Loop, prompting user for integer input. The program copies the stack, 
//   then prints out both stacks.
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

// include required files
#include <cstring>
#include <iostream>
#include "stack.cpp"

using namespace std;

// bool inCheck(string prompt, int& choice)
// inCheck loops until the user inputs a valid integer value.
// it takes the prompt that is to be displayed as an argument.
// it takes the reference variable to be gathered.
// edited from code by Dr. Bracken
// re-used & edited from assignment 2
// ************************************************************************* 

bool inCheck(string prompt, int& choice)
{
  bool go = true;
  int  temp;
  // loop until valid input given.
  while(go)
  {
    cout<<prompt<<endl;

    cin >> temp;

    if(cin.good())
    {
      choice = temp;
      break;
    }

    go = false;

    cin.clear();

    cin.ignore(120,'\n');
  } // end while

  return go;
} // end inCheck

// copyStack(stack& theStack, stack& theCopy)
// copyStack takes 2 stacks at reference paramters, makes a copy of theStack
// and returns the copy as theCopy
// *************************************************************************

void copyStack(stack& theStack, stack& theCopy)
{
  stack temp;
  int mover;
  while(theStack.pop(mover))
  {
    temp.push(mover);
  }
  while(temp.pop(mover))
  {
    theStack.push(mover);
    theCopy.push(mover);
  }
}


// main program

int main()
{
  // initialize the main stack
  stack theStack;
  // initialize the copy stack
  stack theCopy;
  // the value to store the desired item
  int item;

  // the prompt to display
  string prompt = "Please enter an item to put onto the stack.(Non-integer to stop):";
  // intro output
  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Stacks"<<endl;
  cout<<"This program defines a stack based on user input, then makes "<<endl;
  cout<<"a copy of it.\n"<<endl;

  // loop to fill the stack
  while(true)
  {
    if(!inCheck(prompt, item))
    {
      break;
    }else
    {
      theStack.push(item);
    }
  }

  //make a copy of the defined stack
  copyStack(theStack, theCopy);

  // generate output for both stacks
  cout<<"The original stack(from top to bottom): [";
  while(true)
  {
    // item for the copy
    theStack.pop(item);
    cout<<item;
    // preperation for next item, or the end
    if(theStack.look(item))
    {
      cout<<", ";
    }else
    {
      cout<<"]\n"<<endl;
      break;
    }
  }

  // output the copy
  cout<<"The copy stack(from top to bottom): [";
  while(true)
  {
    // item for the copy
    theCopy.pop(item);
    cout<<item;
    // preperation for next item, or the end
    if(theCopy.look(item))
    {
      cout<<", ";
    }else
    {
      cout<<"]\n"<<endl;
      break;
    }
  }

  return 0;
}