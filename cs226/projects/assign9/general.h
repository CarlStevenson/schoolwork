// general.h 
// header file for the general tree class defined in general.cpp

using namespace std;


struct gennode{
  int item;

  gennode *firstchild;

  gennode *siblinglist;

  gennode *prevsibling;

  gennode *parent;
};
class generaltree
{
public:

  generaltree();
  ~generaltree();
  bool search(int value);
  bool displayChildren(int value);

  bool displaySiblings(int value);
  bool displayLeftSiblings(int value);
  bool displayRightSiblings(int value);
  bool displayParent(int value);
  bool addNode();
private:
  gennode *root;
  gennode* getNode(int value, gennode *node);
  int inCheck(string prompt);
  void removeAll(gennode *node);
};