// listj.h
// Written by Carl Stevenson
// 3/24/14
// Linear and Circular Linked Lists
// Assignment 43.
// The header file for listj.cpp, a linked list class.
// Program testing:
//   I have thoroughly tested my code and it works properly
// Adapted from code written by Dr. Bracken.
// *************************************************************************

// include required header files
#include <cassert>
#include <iostream>
#include <string>
#include <fstream>

using namespace std;

// the node struct used for storing values in the list. Contains
// int item, the integer value that is stored
// node *next, pointer to the next value in the list
// *************************************************************************
struct node
{
  int item;
  node *next;
};

// class list
// list is the structure that manages nodes in order to have a linked list.
// *************************************************************************
class list
{
  public:
  // constructors
  list();
  list(const list& theList);
  // destructor
  ~list();
  //list operations

  // displays the items in the list
  void displayList() const;
  // finds an element in the list
  int findElement(int element) const;
  // inserts an element into the list
  void insertElement(int element);
  // deletes an element from the list
  bool deleteElement(int element);
  // reads in elements from a file
  void readIn();
  // displays the number of elements in the list
  int numberOfElements() const;
  // plays the Josephus game
  int Josephus(int passes);

  private:
  // pointer to the head node
  node *head;
  // integer recording the number of elements in the list
  int numberofelements;


};
