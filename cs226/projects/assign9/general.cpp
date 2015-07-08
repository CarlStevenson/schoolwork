// general.cpp
// 4/27/14
// Written by: Carl Stevenson
// General Trees
// Assignment 9.
// Definition for the generaltree class.
// *************************************************************************

#include "general.h"

using namespace std;

generaltree::generaltree()
{
  int value;
  string choice;
  cout<<"Initial tree creation: "<<endl;
  value = inCheck("Enter the root of the tree: ");
  cout<<"You chose: "<<value<<endl;
  root = new gennode;
  root->item = value;
  root->firstchild = NULL;
  root->siblinglist = NULL;
  root->prevsibling = NULL;
  root->parent = NULL;
  while(true)
  {
    cout<<"Would you like to add another node?";
    cin >> choice;
    if(choice == "y")
    {
      cout<<" y"<<endl;
      addNode();
    } else if(choice == "n")
    {
      cout<<" n"<<endl;
      break;
    }
  }

}

generaltree::~generaltree()
{
  removeAll(root);
}

bool generaltree::search(int value)
{
  gennode *temp;
  bool success;
  temp = getNode(value, root);
  if(temp != NULL)
  {
    success = true;
  } else
  {
    success = false;
  }
  return success;
  
}
bool generaltree::displayChildren(int value)
{
  gennode *finder;
  bool success;
  finder = getNode(value, root);
  if(finder != NULL)
  {

    cout<<"The children of "<< finder->item<<" are: ";
    if(finder->firstchild != NULL)
    {
      finder = finder->firstchild;
      cout<<finder->item<<" ";
      while(finder->siblinglist != NULL)
      {
        finder = finder->siblinglist;
        cout<<finder->item<<" ";
      }
      cout<<endl;
      success = true;
    } else
    {
      cout<<"none"<<endl;
    }
  } else
  {
    cout<<"Parent node not found."<<endl;
    success = false;
  }
  return success;
}

bool generaltree::displaySiblings(int value)
{
  gennode *finder;
  bool success;
  finder = getNode(value, root);
  if(finder != NULL)
  {
    if(finder->parent != NULL)
    {
      finder = finder->parent;
      cout<<"The siblings of "<< value <<" are: ";
      finder = finder->firstchild;
      if(finder->item != value)
      {
        cout<<finder->item<<" ";
      }
      while(finder->siblinglist != NULL)
      {
        finder = finder->siblinglist;
        if(finder->item != value)
        {
          cout<<finder->item<<" ";
        }
      }
      if(finder->parent->firstchild->item == value && finder->parent->firstchild->siblinglist == NULL)
      {
        cout<<"no siblings"<<endl;
      } 
    } else
    {
      cout<<"The node is the root and has no siblings."<<endl;
    }
    cout<<endl;
    success = true;
  } else
  {
    cout<<"Node not found."<<endl;
    success = false;
  }
  return success;
}

bool generaltree::displayLeftSiblings(int value)
{
  gennode *finder;
  bool success;
  finder = getNode(value, root);
  if(finder != NULL)
  {
    cout<<"The left siblings of "<< value <<" are: ";
    
    if(finder->prevsibling != NULL)
    {
      finder = finder->prevsibling;
      cout<<finder->item<<" ";
    } else
    {
      cout<<"no left siblings"<<endl;
    }
    while(finder->prevsibling != NULL)
    {
      finder = finder->prevsibling;
      cout<<finder->item<<" ";
    }
    cout<<endl;
    success = true;
  } else
  {
    cout<<"Node not found."<<endl;
    success = false;
  }
  return success;
}

bool generaltree::displayRightSiblings(int value)
{
  gennode *finder;
  bool success;
  finder = getNode(value, root);
  if(finder != NULL)
  {
  
    cout<<"The right siblings of "<< value <<" are: ";
    if(finder->siblinglist != NULL)
    {
      finder = finder->siblinglist;
      cout<<finder->item<<" ";
    }else
    {
      cout<<"no right siblings"<<endl;
    }
    while(finder->siblinglist != NULL)
    {
      finder = finder->siblinglist;
      cout<<finder->item<<" ";
    }
    cout<<endl;
    success = true;
  } else
  {
    cout<<"Node not found."<<endl;
    success = false;
  }
  return success;
}

bool generaltree::displayParent(int value)
{
  gennode *finder;
  bool success;
  finder = getNode(value, root);
  if(finder != NULL)
  {
    finder = finder->parent;
    if(finder != NULL)
    {
    cout<<"The parent of "<<value<<" is "<<finder->item<<endl;
    success = true;
    } else
    {
      cout<<"This node has no parent."<<endl;
    }
  } else
  {
    cout<<"Node not found."<<endl;
    success = false;
  }
  return success;
}

bool generaltree::addNode()
{
  bool success;
  gennode *parent;
  gennode *finder;
  int value;
  value = inCheck("Please enter the parent of the node to add: ");
  cout<<"You chose "<<value<<endl;
  parent = getNode(value, root);
  if(parent != NULL)
  {
    while(true)
    {
      value = inCheck("Please enter the value of the child: ");
      cout<<"You chose: "<<value<<endl;
      if(search(value))
      {
        cout<<"Item already present. Please select a non-existent item."<<endl;
      }
      else
      {
        break;
      }
    }
    // case of first child for his parent
    if(parent->firstchild == NULL)
    {
      parent->firstchild = new gennode;
      parent->firstchild->item = value;
      parent->firstchild->firstchild = NULL;
      parent->firstchild->siblinglist = NULL;
      parent->firstchild->prevsibling = NULL;
      parent->firstchild->parent = parent;
      success = true;
    } 
    // parent already has at least one child, find the last child slot
    else if(parent->firstchild != NULL)
    {
      finder = parent->firstchild;
      while(true)
      {
        // if the next slot is already filled, keep searching
        if(finder->siblinglist != NULL)
        {
          finder = finder->siblinglist;
        }
        // if we found the next slot, place it here
        else if(finder->siblinglist == NULL)
        {
          finder->siblinglist = new gennode;
          finder->siblinglist->item = value;
          finder->siblinglist->firstchild = NULL;
          finder->siblinglist->siblinglist = NULL;
          finder->siblinglist->prevsibling = finder;
          finder->siblinglist->parent = parent;
          success = true;
          break;
        }
      }
    }
  } else
  {
    cout<<"Parent node not found."<<endl;
    success = false;
  }
  cout<<endl;
  return success;
}



gennode* generaltree::getNode(int value, gennode *node)
{
  gennode *desiredNode = NULL;
  if(node==NULL)
  {
    // if the node being searched is null, not the desired node
    return desiredNode;
  }
  // if the node's value is the value we're searching for, we found it
  else if(node->item == value)
  {
    // set return value equal to the node we're at and return it
    desiredNode = node;
    return desiredNode;
  } 
  // otherwise, keep looking
  else  
  {
    // if the node is contained in one of the node's offspring, return that
    desiredNode = getNode(value, node->firstchild);
    if(desiredNode != NULL)
    {
      return desiredNode;
    }
    // otherwise, check it's siblings
    else
    {
      desiredNode = getNode(value, node->siblinglist);
      if(desiredNode != NULL)
      {
        return desiredNode;
      }
    }
  }
  // if it wasn't found, return desiredNode which equals null
  return desiredNode;
}

void generaltree::removeAll(gennode *node)
{
  if(node->firstchild!= NULL)
  {
    removeAll(node->firstchild);
  }
  if(node->siblinglist!= NULL)
  {
    removeAll(node->siblinglist);
  }
  delete node;
}



// int inCheck(string prompt)
// inCheck loops until the user inputs valid input.
// it takes the prompt that is to be displayed as an argument.
// used in addNode to find the value of the node to add
// edited from code by Dr. Bracken
// re-used from previous assignments
// *************************************************************************
int generaltree::inCheck(string prompt)
{
  int choice;
  // loop until valid input given.
  while(true)
  {
    cout<<prompt<<endl;

    cin >> choice;

    if(cin.good())
    {
      break;
    }

    cout<<"You provided a non-integer. Please try again."<<endl;

    cin.clear();

    cin.ignore(120,'\n');
  } 
  // return the gathered input.
  return choice;
}

