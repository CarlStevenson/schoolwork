// stack.h
// Carl Stevenson
// 3/31/14
// Updated 12/1/14 by Carl Stevenson
//
// Problem being solved:
//   stack.h is the header file for stack.cpp, a stack class.
// *************************************************************************

using namespace std;

// the stackNode struct used for storing values on the stack. Contains
// int item, the integer value that is stored
// stackNode *next, pointer to the next value in the list
// re-used from assignment 4.
// *************************************************************************
struct stackNode
{
  int item;
  stackNode *next;
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
  stackNode *head;
};
