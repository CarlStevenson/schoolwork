// assign53.cpp
// Carl Stevenson
// 4/1/14
// Stacks
// Assignment 53
// Problem being solved:
//   Creating an append function to append the contents of one stack to
//   another stack.
// Program flow:
//   Loop, prompting user for integer input for both stacks. The program  
//   appends the second stack to the first stack, then prints out both stacks.
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
// re-used from assignment 52
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

// append(stack& stack1, stack& stack2)
// append takes 2 stacks at reference paramters, appends stack2 to stack1
// *************************************************************************

void append(stack& stack1, stack& stack2)
{

  stack temp;
  // store is to preserve the correct order for the values to be given to
  // stack1, and then to store the correct values for stack2
  stack store;
  int mover;
  // put the values of stack1 onto temp
  while(stack1.pop(mover))
  {
    temp.push(mover);
  }
  // put the values of stack2 onto store
  while(stack2.pop(mover))
  {
    temp.push(mover);
    store.push(mover);
  }
  // assign the proper values to stack1
  while(temp.pop(mover))
  {
    stack1.push(mover);
  }
  // assign the proper values to stack2
  while(store.pop(mover))
  {
    stack2.push(mover);
  }
}


// main program

int main()
{
  // initialize the first stack
  stack stack1;
  // initialize the second stack
  stack stack2;
  // the value to store the desired item
  int item;

  // the prompt to display
  string prompt1 = "Please enter an item to put onto stack1.(Non-integer to stop):";
  string prompt2 = "Please enter an item to put onto stack2.(Non-integer to stop):";
  // intro output
  cout<<"\nCarl Stevenson"<<endl;
  cout<<"Stacks"<<endl;
  cout<<"This program defines 2 stacks based on user input, then appends"<<endl;
  cout<<"the second stack to the first stack.\n"<<endl;

  // loop to fill the stacks
  while(true)
  {
    if(!inCheck(prompt1, item))
    {
      break;
    }else
    {
      stack1.push(item);
    }
  }
  while(true)
  {
    if(!inCheck(prompt2, item))
    {
      break;
    }else
    {
      stack2.push(item);
    }
  }
  // append stack2 to stack1
  append(stack1, stack2);

  // generate output for both stacks
  cout<<"stack1(from top to bottom): [";
  while(true)
  {
    // item from stack1
    stack1.pop(item);
    cout<<item;
    // preperation for next item, or the end
    if(stack1.look(item))
    {
      cout<<", ";
    }else
    {
      cout<<"]\n"<<endl;
      break;
    }
  }

  // output the copy
  cout<<"stack2(from top to bottom): [";
  while(true)
  {
    // item from stack2
    stack2.pop(item);
    cout<<item;
    // preperation for next item, or the end
    if(stack2.look(item))
    {
      cout<<", ";
    }else
    {
      cout<<"]\n"<<endl;
      break;
    }
  }

  return 0;
} // end main