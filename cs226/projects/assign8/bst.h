// bst.h
// Written by: Carl Stevenson
// 4/25/14
// Binary Search Trees
// Assignment 8.
// Problem being solved:
//   bst.h is the header file for bst.cpp
// Program testing:
//   I have not tested my program extensively and am unsure if it works
//   properly.
// Adapted from code in the textbook, figure 4.16
// *************************************************************************



using namespace std;

class BinarySearchTree
{
public:
  BinarySearchTree();
  ~BinarySearchTree();

  bool insert(int element);

  bool remove(int element);

  bool FindMin(int &element);

  bool FindMax(int &element);

  bool isEmpty();

  void printTree();

  bool FindItem(int element);

private:
  struct BinaryNode
  {
    int element;
    BinaryNode *left;
    BinaryNode *right;
  };

  BinaryNode *root;

  bool insert(int element, BinaryNode *&node);

  bool remove(int element, BinaryNode *&node);

  bool FindMin(int &element, BinaryNode *&node);

  bool FindMax(int &element, BinaryNode *&node);

  bool isEmpty(BinaryNode *&node);

  void printTree(BinaryNode *&node);

  bool FindItem(int element, BinaryNode *&node);  

  void makeEmpty(BinaryNode *&node);

};