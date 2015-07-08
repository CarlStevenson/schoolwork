// graph.h
// header file for graph.cpp, an adjancency list-based general graph
// *************************************************************************

#include "queuei.cpp"
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
  graph(const graph &theGraph);
  bool DFS(int V);
  bool BFS(int V);
  bool topoSort();

private:
  node **adj;
  queueClass topsort;
  bool cycle;
  int size;
  int inCheck(string prompt);
  void DFS_visit(int V, int *visited);

};