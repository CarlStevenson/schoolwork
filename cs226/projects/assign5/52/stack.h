// stack.h
// Carl Stevenson
// 3/31/14
// Stacks
// Assignment 5
// Problem being solved:
//   stack.h is the header file for stack.cpp, a stack class to be used
//   in assignment 52 and 53.
// *************************************************************************

using namespace std;

// the node struct used for storing values on the stack. Contains
// int item, the integer value that is stored
// node *next, pointer to the next value in the list
// re-used from assignment 4.
// *************************************************************************
struct node
{
  int item;
  node *next;
};

class stack
{
public:
  // constructor
  stack();
  // copy constructor
  //stack(const &stack theStack);
  // destructor
  ~stack();
  // check if the stack is empty
  bool isEmpty();
  // push an item onto the stack
  bool push(int item);
  // pop an item from the stack
  bool pop();
  // pop and return the item from the stack
  bool pop(int& slot);
  // report the top item of the stack, but do not pop it
  bool look(int& looker);



private:
  node *head;
};
