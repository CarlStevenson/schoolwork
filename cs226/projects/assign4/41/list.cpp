// list.cpp
// Written by: Carl Stevenson
// 3/19/14
// Linear and Circular Linked Lists
// Assignment 41.
// Problem being solved:
//   List interfaced to by assign41.cpp. List is ordered in ascending order.
//   List is a linked list data structure.
// Program testing:
//   I have thoroughly tested my code and it works properly.
//
//  Adapted from code provided by Dr. Bracken.
// *************************************************************************

// include required header files
#include "list.h"

// constructor sets head to NULL and the number of elements to 0, because
// nothing has been stored yet.
list::list()
{
 head=NULL;
 numberofelements = 0;
}

// constructor makes a copy of the list given in the function call

list::list(const list& theList)
{
  node *creator;
  node *copier;
  numberofelements = theList.numberofelements;
  if(numberofelements == 0)
  {
    head = NULL;
  }else
  {
    copier = theList.head;
    head = new node;
    head->item = copier->item;
    creator = head;
    while(true)
    {
      if(copier->next != NULL)
      {
      creator->next = new node;
      creator->next->item = copier->next->item;
      creator = creator->next;
      copier = copier->next;
      }else
      {
        break;
      }
    }
  }
}

// the destructor goes through the entire list, deleting each sotred node.
list::~list()
{
  while (numberofelements>0)
  {
    deleteElement(head->item); //delete next element
  }
}

//list operations

// displayList
// displays all the elements in the list.
void list::displayList() const
{

  // if the numberofelements equals 0, there are no elements in the list
  if(numberofelements==0)
  {
    cout<<"The list does not contain any elements.\n"<<endl;
  }else{
    // otherwise, go iteratively through, displaying each value
    node *tracker = head;
    cout<<"List contents: [";
    for(int i=0; i<numberofelements; i++)
    {
      cout<<tracker->item;
      if(i!=numberofelements-1)
      {
        cout<<", ";
      }
      tracker = tracker->next;
    }
    cout<<"]\n"<<endl;
  }
}

// findElement
// find the user specifid integer element, and returns the slot it's in.
int list::findElement(int element) const
{
  // slot is the return value
  int slot = 1;
  // finder is used to keep track of position
  node *finder = head;

  // if head doesn't point to anything, the list is empty and does not
  // contain the element.
  if(head == NULL)
  {
    slot = 0;
  }else if(head->item == element)
  {
    // if the head is the element we're looking for, return that.
    slot = 1;
  }else
  {
    // otherwise, loop to see if the element is in the list
    while(true)
    {
      // if the next slot is null, the list has no more elements and it has
      // not found it, return 0
      if(finder->next == NULL)
      {
        slot = 0;
        break;
      }
      // if the item of the next node is the element we're looking for,
      // we found the slot it's in.
      else if(finder->next->item == element)
      {
        slot++;
        break;
      }
      // set finder to point to the next node.
      finder = finder->next;
      slot++;
    }
  }
  // return the slot we found element to be in, 0 if it isn't in the list.
  return slot;
}

// insertElement
// inserts the specified element into the list in ascending order
void list::insertElement(int element)
{
  // make a new node for the new element, setting it equal to the
  // user specification, and it's next pointer to NULL for now.
  node *newElement = new node;
  newElement->item = element;
  newElement->next = NULL;
  // if there is no head pointer, the list is empty and we start with this
  // element
  if(head == NULL)
  {
    head = newElement;

  }
  // otherwise, if the head is greater than the element we're trying to
  // put in, the new element needs to be the head, and the current head
  // needs to be pointed at by the new element's next
  else if(head->item > element)
  {
    newElement->next = head;
    head = newElement;
  }
  else{
    // make a pointer to go through the list
    node *tracker = head;
    // loop to find the appropriate place for the node.
    while(true)
    {
      // if we're at the end of the list, the new node gets appended to
      // the list
      if(tracker->next == NULL)
      {
        tracker->next = newElement;
        break;
      
      }
      // otherwise, if the next item is greater than or equal to the item we're
      // attempting to insert, insert it here
      else if(tracker->next->item >= element)
      {
        newElement->next = tracker->next;
        tracker->next =  newElement;
        break; 
      }
      // update tracker to point to the next node
      tracker = tracker->next;
    } // end while
  }
  // update the number of elements in the list
  numberofelements++;
}//end of function

bool list::deleteElement(int element)
{
  // return variable reporting if it was found and deleted,
  // or not found
  bool success;
  // deleter is used to delete the desired node
  node *deleter;
  // finder is used to find the node to delete. and update the pointers
  node *finder = head;
  // if there are no elements, you can't delete anything
  if(head == NULL)
  {
    success = false;

  }
  // if the item you're looking to delete is the head node, delete it and
  // update the new head
  else if(head->item == element)
  {
    finder = head->next;
    head->item = 0;
    head->next = NULL;
    delete head;
    head = finder;
    // update the number of elements in the list, set return variable to
    // true.
    numberofelements--;
    success = true;
  }else 
  {
    // otherwise, loop to find the node to delete
    while(true)
    {
      // if we get to the end of the list without finding it, set the return
      // variable to false
      if(finder->next == NULL)
      {
        success = false;
        break;
      }
      // otherwise check to see if the next items element is the one we're
      // looking for, delete and update pointers if it is.
      else if(finder->next->item == element)
      {
        deleter = finder->next;
        finder->next = deleter->next;
        deleter->item = 0;
        deleter->next = NULL; 
        // set return variable to true
        success = true;
        // update the number of elements in the list
        numberofelements--;
        break;
      }
      // update finder to point to the next node
      finder = finder->next;
    } // end while
  } // end else
  // return whether it successfully deleted the item or not
  return success;
}//end of function

// readIn
// readIn reads in the integer elements described within a file.
// modified on code provided by Dr. Bracken.
void list::readIn()
{
  string infile;
  ifstream in;
  int data;
  while(true)
  {

    cin >> infile;

    if(cin.good())
    {
      in.open(infile.c_str());
      assert (in.is_open());
      break;
    }

    cout<<"You provided an invalid file name. Please try again."<<endl;

    cin.clear();

    cin.ignore(120,'\n');
  } // end while
  cout<<"You chose to import "<< infile<<"\n"<<endl;

  while(true)
  {
    in>>data;
    if(in.eof())
    {
      break;
    }
    insertElement(data);
  }
}

// numberOfElements
// number of elements returns the number of elements currently in the list
int list::numberOfElements() const
{
  return(numberofelements);
}
