// queuei.cpp
// Written by: Carl Stevenson
// 4/9/14
// Queues
// Assignment 61.
// Problem being solved:
//   Defining a queue class for use in assign61.cpp
// Program testing:
//   I have thoroughly tested my code and it works properly.
// Edited from code provided by Dr. Bracken
// *************************************************************************


// *********************************************************
// Implementation file QueueP.cpp for the ADT queue.
// Pointer-based implementation.
// *********************************************************
#include "queuei.h"  // header file
#include <stddef.h>  // for NULL
#include <iostream>
// The queue is implemented as a circular linked list
// with one external pointer to the back of the queue.
struct queueNode
{  queueItemType Item;
   ptrType       Next;
};  // end struct

queueClass::queueClass()  
{
  numberofqueueelements=0;
  BackPtr=NULL;

}  // end default constructor



queueClass::~queueClass()
{
  bool holder;
  while(numberofqueueelements>0)
  {
    QueueDelete(holder);
  }
}  // end destructor

bool queueClass::QueueIsEmpty() const
{
	return bool(BackPtr == NULL);

}  // end QueueIsEmpty

void queueClass::QueueInsert(queueItemType NewItem,
                             bool& Success)
{
  queueNode* newElement = new queueNode;
  newElement->Item = NewItem;
  if(BackPtr == NULL)
  {
    BackPtr = newElement;
    BackPtr->Next = BackPtr;
  }else
  {
  newElement->Next = BackPtr->Next;
  BackPtr->Next = newElement;
  BackPtr = newElement;
  }
  numberofqueueelements++;
  Success = true;
}  // end QueueInsert

void queueClass::QueueDelete(bool& Success)
{
  queueNode* holder;
  if(BackPtr == NULL)
  {
    Success = false;
  }else if(BackPtr == BackPtr->Next)
  {
    delete BackPtr;
    BackPtr = NULL;
    Success = true;
    numberofqueueelements--;
  }else
  {
    holder = BackPtr->Next;
    BackPtr->Next = holder->Next;
    delete holder;
    Success = true;
    numberofqueueelements--;
  }
}  // end QueueDelete

void queueClass::QueueDelete(queueItemType& QueueFront, 
                             bool& Success)
{
  queueNode* holder;
  if(BackPtr == NULL)
  {
    Success = false;
  }else if(BackPtr == BackPtr->Next)
  {
    QueueFront = BackPtr->Item;
    delete BackPtr;
    BackPtr = NULL;
    Success = true;
    numberofqueueelements--;
  }else
  {
    holder = BackPtr->Next;
    BackPtr->Next = holder->Next;
    QueueFront = holder->Item;
    delete holder;
    Success = true;
      numberofqueueelements--;
  }
}  // end QueueDelete

void queueClass::GetQueueFront(queueItemType& QueueFront, 
                               bool& Success) const
{
  if(BackPtr == NULL)
  {
    Success = false;
  }else
  {
    QueueFront = BackPtr->Next->Item;
    Success = true;
  }

}  // end GetQueueFront

void queueClass::QueuePrint() const
{
  queueNode* traverser;
  if(BackPtr!=NULL)
  {
    traverser = BackPtr->Next;
  }
  cout<<"The queue: [";
  for(int i=0; i<numberofqueueelements; i++)
  {
    cout<<traverser->Item;
    if(i!= numberofqueueelements-1)
    {
      cout<<",";
    
      traverser = traverser->Next;
    }
  }
  cout<<"]"<<endl;
}
int queueClass::NumberOfQueueElements() const
{
    return numberofqueueelements;    
}
// End of implementation file.
 













