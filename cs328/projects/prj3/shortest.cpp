// shortest.cpp
// Contains the class definition for shortest, a class that stores adjacency
// matrix information and can calculate the shortest path using the floyd
// or dijkstra shortest path algorithm
// Written by: Carl Stevenson
// **************************************************************************


#include "shortest.h"
#include <cassert>

shortest::shortest()
{

	// prompt user for n
	cout<<"Please enter the number of vertices: "<<endl;
	cin>>n;
	//assert(cin.good());
	cout<<"You chose "<<n<<" vertices."<<endl;

	// dynamically allocate the arrays
	w = new int*[n+1];
	d = new int*[n+1];
	p = new int*[n+1];
	for(int i = 0; i<n+1; i++)
	{
		w[i] = new int[n+1];	
		d[i] = new int[n+1];
		p[i] = new int[n+1];
	}
	
	for(int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			if(i == j)
			{
				w[i][j] = 0;
			} else
			{
				w[i][j] = INFINITY;
			}
		}
	}

	// loop for edge information, the first node, then the second node, then
	// the weight
	bool good = true;
	int node1;
	int node2;
	int weight;
	string instring;
	while(good)
	{
		cout<<"Do you have an edge to enter? (Y/N): "<<endl;
		cin>>instring;
		if(!cin.good())
		{
			good = false;
		}
		else if(instring == "Y" || instring == "y")
		{
			cout<<"Enter node 1: "<<endl;
			cin>>node1;
			assert(cin.good());
			cout<<"You entered node "<<node1<<endl;
			cout<<"Enter node 2: "<<endl;
			cin>>node2;
			assert(cin.good());
			cout<<"You entered node "<<node2<<endl;
			cout<<"Enter weight: "<<endl;
			cin>>weight;
			assert(cin.good());
			cout<<"You entered weight = "<<weight<<endl;
			w[node1][node2] = weight;


		}else if(instring == "N"|| instring == "n")
		{
			good = false;
		}
	}
	
	// print w
	cout<<"The adjacency matrix:"<<endl;
	for(int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			printf("%7d|", w[i][j]);
		}
		cout<<endl;
	}
	cout<<endl;
}
shortest::~shortest()
{

	for(int i = 0; i<n+1; i++)
	{
		delete [] w[i];	
		delete [] d[i];
		delete [] p[i];
	}
	delete w;
	delete d;
	delete p;
}

// From Foundations of Algorithms, p108
// floyd2 "Compute[s] the shortest paths from each vertex in a weighted graph
// to each of the other vertices."
// algorithm taken from the book
// *****************************************************************
void shortest::floyd2()
{
	// make sure graph is properly initialized, for both p[][] and d[][]
	for (int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			p[i][j] = 0;
			d[i][j] = w[i][j];
		}
	}

	for(int k = 1; k<=n; k++)
	{
		for(int i = 1; i<=n; i++)
		{
			for(int j = 1; j<=n; j++)
			{
				if(d[i][k] + d[k][j] < d[i][j])
				{
					p[i][j] = k;
					d[i][j] = d[i][k] + d[k][j];
				}
			}
		}
	}
	cout<<"D: "<<endl;
	for(int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			printf("%3d|", d[i][j]);
		}
		cout<<endl;
	}
	cout<<endl;
	cout<<"P: "<<endl;
	for(int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			printf("%3d|", p[i][j]);
		}
		cout<<endl;
	}
	cout<<"Printing Paths:"<<endl;
	for(int i = 1; i<=n; i++)
	{
		for(int j = 1; j<=n; j++)
		{
			cout<<"Calling path "<<i<<" "<<j<<endl;
			path(i, j);
		}
		
	}
}
// adopted from Foundations of Algorithms
// ***************************************************************************
void shortest::dijkstra(int V)
{
	cout<<"Calculating shortest path from "<<V<<" to all other vertices."<<endl;
	int near;
	edgenode *e;
	int touch[n+1];
	int length[n+1];
	int min;
	// variable initialization
	F = NULL;
	for(int i = 1; i<V; i++)
	{
		touch[i] = V;
		length[i] = w[V][i];
	}
	for(int i = V+1; i<=n; i++)
	{
		touch[i] = V;
		length[i] = w[V][i];
	}
	// do algorithm n-1 times
	for(int i = 2; i<=n; i++)
	{
		min = INFINITY;
		// do operations to avoid the V index
		for(int j = 1; j<V; j++)
		{
			if(0<=length[j] && length[j]< min)
			{
				min = length[j];
				near = j;
			}
		}
		for(int j = V+1; j<=n; j++)
		{
			if(0<=length[j] && length[j]< min)
			{
				min = length[j];
				near = j;
			}
		}

		e = new edgenode;
		e->e1 = touch[near];
		e->e2 = near;
		e->next = F;
		F = e;
		// do operations to avoid the V index
		for(int j = 1; j<V; j++)
		{
			if(length[near] + w[near][j] < length[j])
			{
				length[j] = length[near] + w[near][j];
				touch[j] = near;
			}
		}		
		for(int j = V+1; j<=n; j++)
		{
			if(length[near] + w[near][j] < length[j])
			{
				length[j] = length[near] + w[near][j];
				touch[j] = near;
			}
		}
		length[near] = -1;
	}
	cout<<"Starting at Vertex "<<V<<endl;
	cout<<"Edges:"<<endl;
	edgenode *finder = F;
	while(finder != NULL)
	{
		cout<<"V"<<finder->e1<<" V"<<finder->e2<<endl;
		finder = finder->next;
	}
	cout<<"Touch:"<<endl;
	for(int i = V+1; i<=n; i++)
	{
		cout<<i<<" "<<touch[i]<<endl;
	}
	for(int i = 1; i<V; i++)
	{
		cout<<i<<" "<<touch[i]<<endl;	
	}
}

// From Foundations of Algorithms, p109:
// path "Print[s] the intermediate vertices on a shortest path from one vertex
// to another vertex in a weighted graph"
// algorithm taken from the book.
// *****************************************************************
void shortest::path(int q, int r)
{

	if (p[q][r] !=0)
	{
		path(q, p[q][r]);
		cout<<"v"<<p[q][r]<<endl;
		path(p[q][r], r);
	}
}

