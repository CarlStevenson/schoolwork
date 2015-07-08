// bst.cpp
// Written by: Carl Stevenson
// 4/25/14
// Binary Search Trees
// Assignment 8.
// Problem being solved:
//   bst.cpp is the definition for a binary search tree used in assign8.cpp.
// Program testing:
//   I have not tested my program extensively and am unsure if it works
//   properly.
// *************************************************************************
#include "bst.h"

using namespace std;


BinarySearchTree::BinarySearchTree()
{
  root = NULL;
}
BinarySearchTree::~BinarySearchTree()
{
  makeEmpty(root);
}

bool BinarySearchTree::insert(int element)
{
  bool success;
  success = insert(element, root);

  return success;
}

bool BinarySearchTree::remove(int element)
{
  bool success;
  if(root == NULL)
  {
    success = false;
  }
  else
  {
    success = remove(element, root);
  }
  return success;
}

bool BinarySearchTree::FindMin(int &element)
{
  bool success;
  success = FindMin(element, root);

  return success;

}

bool BinarySearchTree::FindMax(int &element)
{
  bool success;
  success = FindMax(element, root);

  return success;
  
}

bool BinarySearchTree::isEmpty()
{
  bool success;
  success = isEmpty(root);

  return success;
}

void BinarySearchTree::printTree()
{
  if(root == NULL)
  {
    cout<<"The tree is empty."<<endl;
  } else
  {
    printTree(root);
    cout<<endl;
  }
}

bool BinarySearchTree::FindItem(int element)
{
  bool success;
  success = FindItem(element, root);

  return success;

}

bool BinarySearchTree::insert(int element, BinaryNode *&node)
{
  
  if(node == NULL)
  {
    // only triggers if root == NULL
    node = new BinaryNode();
    node->element = element;
    node->left = NULL;
    node->right = NULL;
    return true;
  } else if(element == node->element)
  {
    // element to be inserted and this node have the same value. return
    // successful, but ignore the insert
    return true;
  } else if(element < node->element)
  {
    if(node->left == NULL)
    {
      // insert the element in the left sub tree of this node
      node->left = new BinaryNode();
      node->left->element = element;
      node->left->left = NULL;
      node->left->right = NULL;
      return true;
    } else
    {
      // otherwise, start the insert process again, there
      return insert(element, node->left);
    }
  } else if(element > node->element)
  {
    if(node->right == NULL)
    {
      // insert the element in the right sub tree of this node
      node->right = new BinaryNode();
      node->right->element = element;
      node->right->left = NULL;
      node->right->right = NULL;
      return true;
    } else
    {
      // otherwise, start the insert process again, there
      return insert(element, node->right);
    }
  } else
  {
    // if all else fails, something is wrong
    return false;
  }

}

bool BinarySearchTree::remove(int element, BinaryNode *&node)
{
  bool success = false;
  BinaryNode *holder;
  if(node == NULL)
  {
    return success;
  }
  if(element == node->element)
  {
    // if the element is the root
    if(node->left == NULL && node->right == NULL)
    {
      // if the child is a leaf node, just delete it
      delete node;
      node = NULL;
      success = true;
    }
    // case of desired node having one child
    else if(node->right == NULL && node->left != NULL)
    {
      // has one left child
      // overwrite the node to be deleted with the child
      holder = node->left;
      delete node;
      node = holder;
    } else if(node->left == NULL && node->right != NULL)
    {
      // has one right child
      // overwrite the node to be deleted with the child
      holder = node->right;
      delete node;
      root = holder;
    }else if(node->left != NULL && node->right != NULL)
    {
      // case of desired node having 2 children
      holder = node->right;
      // if we start on the smallest element of the right subtree, we're done
      if(holder->left == NULL)
      {
        // copy the element
        node->element = holder->element;
        // overwrite the husk
        holder = holder->right;
      } else
      {
        while(true)
        {
          // iteratively go to the smallest element of the right subtree
          if(holder->left->left != NULL)
          {
            holder = holder->left;
          } else
          {
            break;
          }
        }

      // copy the element 
      node->left->element = holder->left->element;
      // overwrite the husk
      holder->left = holder->left->right;   
      }
    }
    success = true;
  } 

  // case of left child
  else if(node->left != NULL && node->left->element == element)
  {
    // short circuit evaluation to check the left child of this node
    // case of desired node being a leaf node
    if(node->left->left == NULL && node->left->right == NULL)
    {
      // if the child is a leaf node, just delete it
      delete node;
      success = true;
    }
    // case of desired node having one child
    else if(node->left->right == NULL && node->left->left != NULL)
    {
      // has one left child
      // overwrite the node to be deleted with the child
      holder = node->left->left;
      delete node;
      node = holder;
      success = true;
    } else if(node->left->left == NULL && node->left->right != NULL)
    {
      // has one right child
      // overwrite the node to be deleted with the child
      holder = node->left->right;
      delete node;
      node = holder;
      success = true;
    }else if(node->left->left != NULL && node->left->right != NULL)
    {
      // case of desired node having 2 children
      holder = node->left->right;
      // if we start on the smallest element of the right subtree, we're done
      if(holder->left == NULL)
      {
        // copy the element
        node->left->element = holder->element;
        // overwrite the husk
        holder = holder->right;
        success = true;
      } else
      {
        while(true)
        {
          // iteratively go to the smallest element of the right subtree
          if(holder->left->left != NULL)
          {
            holder = holder->left;
          } else
          {
            break;
          }
        }

      // copy the element 
      node->left->element = holder->left->element;
      // overwrite the husk
      holder->left = holder->left->right;
      success = true;   
      }
    }
  } 

  // case of right child
  else if(node->right != NULL && node->right->element == element)
  {
    // short circuit evaluation to check the right child of this node
    // case of desired node being a leaf node
    if(node->right->left == NULL && node->right->right == NULL)
    {
      // if the child is a leaf node, just delete it
      delete node;
      success = true;
    }// case of desired node having one child
    else if(node->right->right == NULL && node->right->left != NULL)
    {
      // has one left child
      // overwrite the node to be deleted with the child
      holder = node->right->left;
      delete node;
      node = holder;
    } else if(node->right->left == NULL && node->right->right != NULL)
    {
      // has one right child
      // overwrite the node to be deleted with the child
      holder = node->right->right;
      delete node;
      node = holder;
    } else if(node->right->left != NULL && node->right->right != NULL)
    {

      // case of desired node having 2 children
      holder = node->right->right;
      // if we start on the smallest element of the right subtree, we're done
      if(holder->left == NULL)
      {
        // copy the element
        node->left->element = holder->element;
        // overwrite the husk
        holder = holder->right;
      } else
      {
        while(true)
        {
          // iteratively go to the smallest element of the right subtree
          if(holder->left->left != NULL)
          {
            holder = holder->left;
          } else
          {
            break;
          }
        }

      // copy the element 
      node->right->element = holder->left->element;
      // overwrite the husk
      holder->left = holder->left->right;   
      }
    }
  }





  else if(element > node->element)
  {
    if(node->right != NULL)
    {
      // continue the search on the right sub tree
      success = remove(element, node->right);
    }
  } else if(element < node->element)
  {
    if(node->left != NULL)
    {
      // continue the search on the left sub tree
      success = remove(element, node->left);
    }
  }
  
}

bool BinarySearchTree::FindMin(int &element, BinaryNode *&node)
{
  bool success = false;
  if(node == NULL)
  {
    return success;
  }
  if(node->left == NULL)
  {
    // this is the last node in the leftmost subtree, this must be the 
    // lowest value
    element = node->element;
    success = true;
  } else
  {
    // otherwise keep looking
    success = FindMin(element, node->left);
  }
  return success;
}

bool BinarySearchTree::FindMax(int &element, BinaryNode *&node)
{
  bool success = false;
  if(node == NULL)
  {
    return success;
  }
  if(node->right == NULL)
  {
    // this is the last node in the rightmost subtree, this must be the 
    // highest value
    element = node->element;
    success = true;
  } else
  {
    // otherwise keep looking
    success = FindMax(element, node->right);
  }
  return success;
}

bool BinarySearchTree::isEmpty(BinaryNode *&node)
{
  bool success;
  if(node == NULL)
  {
    success = true;
  } else 
  {
    success = false;
  }

  return success;
}

void BinarySearchTree::printTree(BinaryNode *&node)
{
  // in order printing
  // left self right
  if(node == NULL)
  {
    return;
  }
  if(node->left != NULL)
  {
    // left
    printTree(node->left);
  }
  // self
  cout<<node->element<<" ";
  if(node->right != NULL)
  {
    //right
    printTree(node->right);
  }
}

bool BinarySearchTree::FindItem(int element, BinaryNode *&node)
{
  bool success = false;
  if(node == NULL)
  {
    return success;  
  } else if(element == node->element)
  {
    // found it
    success = true;
  } else if(element > node->element)
  {
    if(node->right != NULL)
    {
      // continue the search on the right sub tree
      success = FindItem(element, node->right);
    }
  } else if(element < node->element)
  {
    if(node->left != NULL)
    {
      // continue the search on the left sub tree
      success = FindItem(element, node->left);
    }
  }
  // return the value received
  return success;
}

void BinarySearchTree::makeEmpty(BinaryNode *&node)
{
  int remover;
  while(true)
  {
    // removes until empty
    if(root != NULL)
    {
      FindMin(remover, node);
      remove(remover);
    }
    else
    {
      break;
    }
  }

}