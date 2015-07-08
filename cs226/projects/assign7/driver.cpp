#include <iostream>
#include "binarytree.cpp"

using namespace std;

int main()
{
  binarytree theTree;
  int mover;
  theTree.add(1);
cout<<theTree.theHeight()<<endl;
  theTree.add(2);
cout<<theTree.theHeight()<<endl;
  theTree.add(3);

  theTree.add(4);
cout<<theTree.theHeight()<<endl;
  theTree.add(10);


  theTree.add(6);
  theTree.add(7);
  theTree.inOrder();
  theTree.displayLevel(0);
  theTree.displayLevel(1);
  theTree.displayLevel(2);
  theTree.preOrder();
  theTree.postOrder();

  return 0;
}