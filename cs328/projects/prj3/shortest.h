// shortest.h
// class definition for shortest
// Written by: Carl Stevenson
// **************************************************************************

#include <cstring>
#include <iostream>
#include <fstream>
//#include <cmath>
using namespace std;

#define INFINITY 1000000
struct edgenode {
int e1;
int e2;
edgenode *next;
}; 
class shortest
{
public:
	int n;		// number of vertices
	shortest();
	~shortest();
	void floyd2();
	void dijkstra(int V);

private:
	int** w;	// for the adjacency matrix
	int** d;
	int** p;
	edgenode * F;
	void path(int q, int r);
};