// queuea.cpp
// Written by: Carl Stevenson
// 4/9/14
// Queues
// Assignment 62.
// Problem being solved:
//   Defining a array based queue class for use in assign62.cpp
// Program testing:
//   I encounter a bug when expanding the array. The values seem to get
//   changed to the address values of the temporary array.
//   Time spent debugging: 3 hours
// Edited from code provided by Dr. Bracken
// *************************************************************************


// *********************************************************
// Implementation file QueueP.cpp for the ADT queue.
// Pointer-based implementation.
// *********************************************************
#include "queuea.h"  // header file
#include <stddef.h>  // for NULL
#include <iostream>

queueClass::queueClass()  
{
  noelements=0;
  queueSize = 10;
  theQueue = new int[queueSize];
  head = 0;
  tail = 0;

}  // end default constructor



queueClass::~queueClass()
{
  delete theQueue;
}  // end destructor

bool queueClass::QueueIsEmpty() const
{
	return noelements == 0;

}  // end QueueIsEmpty

void queueClass::expandQueue()
{
  int temp[queueSize+5];
  int holder = 0;
  for(int i=head; i!=tail; i++)
  {
    if(i==queueSize-1)
    {
      i = 0;
    }

    temp[holder] = theQueue[i];
    holder++;
  }

  delete theQueue;
  theQueue = new int[queueSize+5];
  for(int i=0; i<queueSize; i++)
  {
    theQueue[i] = temp[i]; 
  }

  head = 0;
  tail = queueSize;
  queueSize = queueSize+5;

}

void queueClass::shrinkQueue()
{
  delete theQueue;
  theQueue = new int[10];
  queueSize = 10;
  head = 0;
  tail = 0;

}
void queueClass::QueueInsert(int item,
                             bool& Success)
{
  if(noelements == queueSize)
  {
    expandQueue();
  }
  theQueue[tail] = item;
  noelements++;
  if(tail!=queueSize-1)
  {
    tail++;
  }else
  {
    tail = 0;
  }
  Success = true;
}  // end QueueInsert

void queueClass::QueueDelete(bool& Success)
{
  if(noelements == 0)
  {
    Success = false;
  }else if(head == queueSize-1)
  {
    theQueue[head] = 0;
    head = 0;
    noelements--;
    Success = true;
  }else
  {
    theQueue[head] = 0;
    if(head!=queueSize-1)
    {
      head++;
    }else
    {
      head = 0;
    }
    noelements--;
    Success = true;
  }
  // check to see if the queue can be shrunk
  if(noelements == 0)
  {
    shrinkQueue();
  }
}  // end QueueDelete

void queueClass::QueueDelete(int& QueueFront, 
                             bool& Success)
{
  if(noelements == 0)
  {
    Success = false;
  }else if(head == queueSize-1)
  {
    QueueFront = theQueue[head];
    theQueue[head] = 0;
    head = 0;
    noelements--;
    Success = true;
  }else
  {
    QueueFront = theQueue[head];
    theQueue[head] = 0;
    head++;
    noelements--;
    Success = true;
  }
  // check to see if the queue can be shrunk
  if(noelements == 0)
  {
    shrinkQueue();
  }

}  // end QueueDelete

void queueClass::GetQueueFront(int& QueueFront, 
                               bool& Success) const
{
  if(noelements == 0)
  {
    Success = false;
  }else
  {
    QueueFront = theQueue[head];
    Success = true;
  }
}  // end GetQueueFront

void queueClass::QueuePrint() const
{
  cout<<"The queue: [";
  for(int i=head; i!=tail; i++)
  {
    if(i==queueSize)
    {
      i = 0;
    }
    cout<<theQueue[i];
    if(i!=tail-1)
    {
      cout<<",";
    }
  }
  cout<<"]"<<endl;
}
int queueClass::noElements() const
{
    return noelements;    
}
// End of implementation file.
 













