// binarytree.cpp
// Written by: Carl Stevenson
// 4/14/14
// Binary Trees
// Assignment 7.
// Problem being solved:
//   Defining a binary tree for assign7
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************



using namespace std;

#include <iostream>
#include "binarytree.h"


binarytree::binarytree()
{
  // initialize tree to have all negative values
  for(int i=0; i<100;i++)
  {
    tree[i] = -1;
  }
  numberofelements = 0;
  height = 0;
}

binarytree::~binarytree()
{

}

bool binarytree::add(int element)
{
  bool success;
  // check for the root of the tree first
  if(tree[0] <0)
  {
    tree[0] = element;
    success = true;
    numberofelements++;
    height++;
  }else if(numberofelements==100)
  {
    // case for when the tree is full
    success = false;
  }else
  {
    // add the item, if the height changes, update accordingly
    tree[numberofelements] = element;
    if(numberofelements == ((2*(height-1))+1))
    {
      height++;
    }
    numberofelements++;
    success = true;
  }
  return success;
}

bool binarytree::remove(int &element)
{
  bool success;

  // can't remove from an empty tree
  if(tree[0]<0)
  {
    success = false;
  }else
  {
    // remove the element, adjust the heigh if neccessary
    numberofelements--;
    element = tree[numberofelements];
    tree[numberofelements] = -1;
    if(numberofelements==((2*(height-2))+1))
    {
      height--;
    }else if(numberofelements == 0)
    {
      height = 0;
    }
    success = true;

  }
  return success;
}
void binarytree::displayLevel(int level)
{
  cout<<"Level "<<level<<": ";
  int leftbound =(((level-2) * 2) + 1);
  if(level == 1)
  {
    cout<<tree[0]<<endl;
  }
  else
  {
    for(int i = leftbound; i<(leftbound+(level*2)); i++)
    {
      if(tree[i] <0)
      {
        cout<<"- ";
      }else
      {
        cout<<tree[i]<<" ";
      }
    }
    cout<<endl;
  }
}
void binarytree::inOrder()
{
  // left self right
  cout<<"In order traversal of the tree: ";
  
  if(tree[1]>=0)
  {
    inOrder(1);
  }
  if(tree[0]>=0)
  {
    cout<<tree[0]<<" ";
  }else
  {
    cout<<"No elements in the tree.";
  }
  if(tree[2]>=0)
  {
    inOrder(2);
  }
  cout<<endl;
}

void binarytree::inOrder(int root)
{
  // left self right
  int nextval = ((2*root)+1);
  if(nextval <= 100)
  {
    if(tree[nextval] >=0)
    {
      inOrder(nextval);
    }
    if(tree[root]>=0)
    {
      cout<<tree[root]<<" ";
    }
    if(tree[nextval+1]>=0)
    {
      inOrder(nextval+1);
    }
  }
}
void binarytree::preOrder()
{
  // self left right
  cout<<"Pre order traversal of the tree: ";
  if(tree[0]>=0)
  {
    cout<<tree[0]<<" ";
  }else
  {
    cout<<"No elements in the tree.";
  }
  if(tree[1]>=0)
  {
    preOrder(1);
  }
  if(tree[2]>=0)
  {
    preOrder(2);
  }
  cout<<endl;
}

void binarytree::preOrder(int root)
{
  // self left right
  int nextval = ((2*root)+1);
  if(nextval <= 100)
  {
    if(tree[root]>=0)
    {
      cout<<tree[root]<<" ";
    }
    if(tree[nextval] >=0)
    {
      preOrder(nextval);
    }
    
    if(tree[nextval+1]>=0)
    {
      preOrder(nextval+1);
    }
  }
}

void binarytree::postOrder()
{
  // left right self
  cout<<"Post order traversal of the tree: ";
  if(tree[1]>=0)
  {
    postOrder(1);
  }
  if(tree[2]>=0)
  {
    postOrder(2);
  }
  if(tree[0]>=0)
  {
    cout<<tree[0]<<" ";
  }else
  {
    cout<<"No elements in the tree.";
  }
  cout<<endl;
}

void binarytree::postOrder(int root)
{
  // left right self
  int nextval = ((2*root)+1);
  if(nextval <= 100)
  {
    
    if(tree[nextval] >=0)
    {
      postOrder(nextval);
    }
    
    if(tree[nextval+1]>=0)
    {
      postOrder(nextval+1);
    }
    if(tree[root]>=0)
    {
      cout<<tree[root]<<" ";
    }
  }
}

int binarytree::theHeight()
{
  return height;
}
int binarytree::numberOfElements()
{
  return numberofelements;
}
bool binarytree::isFull()
{
  return numberofelements == 100;
}