// collection.h
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

#include <iostream>
using namespace std;
#define SIZE 20
// *********************************************************
// Header file Collection.h generic list
// Pointer-based implementation.
// *********************************************************template <class T>

template <class T> class Collection
{
public:
// constructors and destructor:
  Collection();                     // default constructor
     
  ~Collection();                    // destructor
  
  bool isEmpty();

  //display the contents of the collection
  void display();
  
  // insert NewItem into the collection
  bool insert(T NewItem);
 
  // remove the last item in the collection and put it into slot
  bool remove(T& slot);

  // check to see if item is in the collection
  bool contains(T item);

  // overloading the '=' operator to assign a collection to a collection
  Collection<T> & operator=(const Collection<T>& rhs);
  
  // overloading the '==' operator to commpare two collections
  bool operator==(const Collection<T>& rhs);

  // clear all data from a collection
  void makeEmpty();      

private:
  
  // array of the items in the collection
  T items[SIZE];
  
  // number of valid entries in the collection
  int noValidEntries;
   
  // points to first element
};  // end class