// graph.h
// header file for graph.cpp, an adjancency list-based general graph
// *************************************************************************
#include "stack.cpp"
#include <stddef.h>  // for NULL
#include <iostream>
using namespace std;

struct node
{
  int name;
  node *next;
};
class graph
{
public:
  graph();
  ~graph();
  graph(graph &theGraph);
  bool DFS(int V);
  void adjList(node ** &list, int &big);

private:
  node **adj;
  stack theStack;
  int size;
  int inCheck(string prompt);
  void DFS_visit(int V, int *visited);
  void printCycle(int nodenum);
};