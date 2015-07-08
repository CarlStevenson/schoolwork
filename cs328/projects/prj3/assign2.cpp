// assign2.cpp
// Driver program for the shortest class, class to calculate the shortest path
// given a user defined adjacency matrix using the Floyd and dijkstra
// algorithms.
// Written by Carl Stevenson
// 11/6/14
// ***************************************************************************

#include <cassert>
#include "shortest.h"
using namespace std;

int main()
{
	// intro
	cout<<"\nShortest Path"<<endl;
	cout<<"This program takes a user defined adjacency matrix, stores it,"<<endl;
	cout<<"and allows the user to interact with it via menu in order to "<<endl;
	cout<<"Perform Floyd's and Dijkstra's algorithm on it.\n"<<endl;
	shortest graph;
	string menu = "\nPlease choose a menu item:\n\t1. Dijkstra's shortest path\n\t2. Floyd's shortest path\n\t3. Exit\n";
	bool good=true;
	int chooser;
	int startvertex;
	while(good)
	{
		// print menu and get input
		cout<<menu<<endl;
		cin>>chooser;
		if(!cin.good())
		{
			good = false;
		}
		else switch(chooser)
		{
			case 1:
				cout<<"You chose 1.\nChoose the vertex to start with: "<<endl;
				cin>>startvertex;
				assert(cin.good());
				cout<<"You chose "<<startvertex<<endl;
				assert(startvertex>0 && startvertex<=graph.n);
				graph.dijkstra(startvertex);
				break;
			case 2:
				cout<<"You chose 2.\n"<<endl;
				graph.floyd2();
				break;
			case 3:
				cout<<"You chose 3.\nExiting now."<<endl;
				good = false;
				break;
			default:
				cout<<"Please choose an option between 1 and 3 inclusive."<<endl;
				break;
		}
	}
	return 0;

}
