// binarytree.h
// Written by: Carl Stevenson
// 4/14/14
// Binary Trees
// Assignment 7.
// Problem being solved:
//   Header file for the binary tree used in assign7.
// *************************************************************************


using namespace std;

class binarytree
{
  public:
    // constructor
    binarytree();
    // destructor
    ~binarytree();
    // add an element to the tree
    bool add(int element);
    // delete an element from the tree
    bool remove(int &element);
    // display level n
    void displayLevel(int level);
    // inorder traversal
    void inOrder();
    // preorder traversal
    void preOrder();
    // postorder traversal
    void postOrder();
    // returns the height of the tree
    int theHeight();
    // returns the number of elements
    int numberOfElements();
    // returns if the tree is full
    bool isFull();

  private:
    // traversal functions to be used by the public functions
    void inOrder(int root);
    void preOrder(int root);
    void postOrder(int root);

    int tree[100];
    int numberofelements;
    int height;
};