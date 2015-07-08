// testfile.cpp
// 4/9/14
// Written by Carl Stevenson
//
// testfile defines a function called testlist that tests the validity
// of a doubly-linked list.
//
// *************************************************************************

#include "testlist.h"
using namespace std;

bool testlist(nodePtr head, nodePtr tail,int size)
{

  nodePtr tracer;

  // check if the size is valid
  if(size<0)
  {
    return false;
  }
  // check if the head pointer is NULL
  if(head == NULL)
  {
    return false;
  }
  // check if the tail pointer is NULL
  if(tail == NULL)
  {
    return false;
  }
  // check head's prev pointer
  if(head->prev != NULL)
  {
    return false;
  }
  // check tail's next pointer
  if(tail->next != NULL)
  {
    return false;
  }

  // check all pointers point to correct next node
  // check for holes
  tracer = head;
  for(int i=1; i<size; i++)
  {
    if(tracer->next == NULL)
    {
      return false;
    }
    tracer = tracer->next;
  }
  // leaves it at what should be the tail node
  // test if so
  if(tracer != tail)
  {
    return false;
  }
  // check all pointers point to the correct previous node
  // check for holes
  tracer = tail;
  for(int i=1; i<size; i++)
  {
    if(tracer->next == NULL)
    {
      return false;
    }
    tracer = tracer->prev;
  }
  // leaves it at what should be the head node
  // test if so
  if(tracer != head)
  {
    return false;
  }

  // if it passes all that, must be valid
  tracer = head;
  cout<<"The list: [";
  for(int i=0; i<size; i++)
  {
    cout<<tracer->item;
    if(i != size-1)
    {
      cout<<",";
    }
  }
  cout<<"]"<<endl;
  return true;
} 