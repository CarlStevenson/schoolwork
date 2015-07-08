// stack.cpp
// Carl Stevenson
// 3/31/14
// updated 12/1/14 by Carl Stevenson
// 
// Problem being solved:
//   stack.cpp contains the definition for a stack class.
// *************************************************************************


#include "stack.h"
using namespace std;

  // constructor
  stack::stack()
  {
    head = NULL;
  }
  // copy constructor
  //stack(const &stack theStack);
  // destructor
  stack::~stack()
  {
    while(pop())
    {
     // pops items until pop returns false 
    }
  }

  // check if the stack is empty
  bool stack::isEmpty()
  {
    bool empty;
    if(head == NULL)
    {
      empty = true;
    }else
    {
      empty = false;
    }
    return empty;
  }
  // push an item onto the stack
  bool stack::push(int item)
  {
    stackNode *newElement = new stackNode();
    newElement->item = item;
    newElement->next = head;
    head = newElement;
  }
  // pop an item from the stack
  bool stack::pop()
  {
    stackNode *holder;
    bool success;
    if(head == NULL)
    {
      success = false;
    }else
    {
      holder = head;
      head = head->next;
      delete holder;
      success = true;
    }
    return success;
  }
  // pop and return the item from the stack
  bool stack::pop(int& slot)
  {
    stackNode *holder;
    bool success;
    if(head == NULL)
    {
      success = false;
    }else
    {
      holder = head;
      head = head->next;
      slot = holder->item;
      delete holder;
      success = true;
    }
    return success;
  }
  // report the top item of the stack, but do not pop it
  bool stack::look(int &looker)
  {
    bool success;
    if(head == NULL)
    {
      success = false;
    }else
    {
      success = true;
      looker = head->item;
    }
    return success;
  }
