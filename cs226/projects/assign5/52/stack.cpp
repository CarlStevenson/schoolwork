// stack.cpp
// Carl Stevenson
// 3/31/14
// Stacks
// Assignment 5
// Problem being solved:
//   stack.cpp contains the definition for a stack class. It will be used for
//   assignment 52 and 53.
// *************************************************************************


#include "stack.h"


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
    node *newElement = new node();
    newElement->item = item;
    newElement->next = head;
    head = newElement;
  }
  // pop an item from the stack
  bool stack::pop()
  {
    node *holder;
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
    node *holder;
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
