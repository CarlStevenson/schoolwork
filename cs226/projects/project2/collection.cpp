// collection.cpp
// Written by: Carl Stevenson
// Based on and edited from the template provided by Dr. Bracken
// 2/26/14
// Class Templates
// Assignment 2.
// Problem being solved:
//   Collection class that has the means of storing and performing functions
//    on a collection of items.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

#include <stddef.h>
#include <cassert>
#include "collection.h"

using namespace std;


// constructor for the collection
template<class T>
Collection<T>::Collection()
{
  //cout<<"Constructor Executing"<<endl;

  noValidEntries=0;
}

// destructor for the collection
template<class T>
Collection<T>::~Collection()
{

  //cout<<"Destructor Executing"<<endl;
  makeEmpty();
}//end destructor

//check to see if the collection is empty
template<class T>
bool Collection<T>::isEmpty()
{
  bool empty;
  // if the number of entries is greater than 0, it isn't empty
  if(noValidEntries>0)
  {
    empty = false;
  }
  else
  {
    empty = true;
  }
  return empty;
}

// remove the last item in the collection and put it into slot
template<class T>
bool Collection<T>::remove(T& slot)
{
  bool success;
  // if there aren't any valid entries, you can't remove anything
  if(noValidEntries>0)
  {
    // set slot equal to that last entry
    slot = items[noValidEntries-1];
    // update noValidEntries
    noValidEntries--;
    success = true;  
  }
  else
  {
    success = false;
  }
  
  return success;
}

// check to see if item is in the collection
template<class T>
bool Collection<T>::contains(T item)
{
  bool hasIt = false;
  // iteratively go through the collection to see if the collection has item
  for(int i=0; i<noValidEntries; i++)
  {
    if(items[i] == item)
    {
      hasIt = true;
      break;
    }
  }

  return hasIt;
}

// overloading the '==' operator to compare two collections
template<class T>
bool Collection<T>::operator == (const Collection<T>& rhs)
{
  bool equal = true;
  // if the noValidEntries differ, they aren't equal
  if(noValidEntries == rhs.noValidEntries)
  {
    // check each item side by side to see if they are equal
    for(int i=0; i<noValidEntries; i++)
    {
      // if two don't match up, they aren't equal
      if(items[i] != rhs.items[i])
      {
        equal = false;
        break;
      }
    }
  }
  else
  {
    equal = false;
  }
  return equal;
}

// clear the collection out
template<class T>
void Collection<T>::makeEmpty()
{

  for (int i=0;i<noValidEntries;i++)
  {
    //loop to remove all entries
    items[i] = 0;
  }          
  noValidEntries = 0;
}//end make empty


// insert an item at the end of the collection
template <class T>
bool Collection<T>::insert(T NewItem)
{   
  bool success;
  // if at the size limit, cannot add any more              
  if (noValidEntries==SIZE)
  {
    success=false;
  }
  else
  {
    //properly insert into its proper position
    //increment noValidEntries
    success=true;
    items[noValidEntries]=NewItem;
    noValidEntries++;
  }
  return success;
} // end insert

// display the contents of the collection
template <class T>
void Collection<T>::display()
{
  for (int i=0;i<noValidEntries;i++)
  {
    cout<<"next = "<<items[i]<<endl;
  }//end for
}//end display

// overloading the '=' operator to set one collection equal to another
template <class T>
Collection<T>& Collection<T>::operator=(const Collection <T>&rhs)
{

  // set the number of valid entries equal to the right hand side
  noValidEntries = rhs.noValidEntries;

  // set the items in this collection equal to the items in the right 
  // hand collection
  for(int i=0; i<SIZE; i++)
  {
    items[i] = rhs.items[i];
  }

}//end of =


//end of implementation file