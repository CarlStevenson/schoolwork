// plane.cpp
// Written by: Carl Stevenson
// plane.cpp contains the class definition for the plane class, used in the 
// closest pair assignment
// Skeleton code generously provided by Dr. Bracken.
// Uses the mergesort pseudocode for implementation fo mergesort
// **************************************************************************



#include "plane.h"
#include <cassert>

plane::plane()
{
	noPoints = 0;
	string filename;
	ifstream infile;
	cout<<"Please enter the name of the file containing the plane: "<<endl;
	cin >> filename;
	cout<<"You chose "<<filename<<endl;
	infile.open(filename.c_str());
	for(int i = 0; i<MAXPOINTS; i++)
	{
		infile >> thePlane[i].x;
		if(!infile.good())
		{
			break;
		}
		infile >> thePlane[i].y;
		noPoints++;
	}
	infile.close();
}
plane::~plane()
{

}

void plane::printpoints()
{
	cout<<"{ ";
	for(int i = 0; i<noPoints; i++)
	{
		cout<<"("<<thePlane[i].x<<", "<<thePlane[i].y<<") ";
	}
	cout<<"}"<<endl;
}//end printpoints
void plane::findclosest()
{
  //appropriately initialize data members required to perform the
  //closest pair analysis
  // the minimum shouldn't be above 1 million
  min = 1000000;
  distCalc = 0;
  cp1.x = 0;
  cp1.y = 0;
  cp2.x = 0;
  cp2.y = 0;

  // sort on the x coordinate, display points
  pass = 1;
  mergesort(noPoints, thePlane);
  cout<<"Points sorted on x coordinate:"<<endl;
  printpoints();

  // sort on the y coordinate, display points
  pass = 2;
  mergesort(noPoints, thePlane);
  cout<<"Points sorted on the y coordinate:"<<endl;
  printpoints();
  
  // print closest points
  cout<<"\nClosest points: ("<<cp1.x<<", "<<cp1.y<<") ("<<cp2.x<<", "<<cp2.y
  	<<")"<<endl;
 	
 	// print the number of distance calculations it took to get there
	cout<<"Distance calculations performed: "<<distCalc<<endl;
}
void plane::mergesort (int n,struct  point *S)
{
	//pseudocode for mergesort (provided by Dr. Bracken)
	// also uses references to Foundations of Algorithms

	//n is the number of elements in the array S

	if (n>1) //is the array size trivially small
	{
		int h = n/2;
		int m = n-h;
		int middle;
  	//move first n/2 elements in S to array U
  	struct point U[h];
  	for(int i = 0; i<h; i++)
  	{
  		U[i] = S[i];
  	}
  	//move second n/2 elements in S to array V
  	struct point V[m];
  	for(int i = 0; i<m; i++)
  	{
  		V[i] = S[i+h];
  	}
  	if (pass == 2)
  	{
    	middle = n/2;
    }

  	mergesort(h,U);
  	mergesort(m,V);
  	merge(h, m, U, V, S);  //need to pass array sizes to merge, details left out
 	}
 	/*
 	if (pass==2)
 	{
  	//p1.x=p2.x=p3.x=p4.x=INFINITY


  	//many details missing. purpose is to provide the idea
  	//for each point p in S
   	{

   		//if the x coordinate of p is < min from middle
    	{
      	//calculate the distance from 
 					//p to p1, p2, p3, p4
    	//if  (distance from any of the points < min)
    	{
      	min = distance
        //replace cp1 and cp2 with p and the appropriate point of the four tested
    	}
   	//p1=p2
   	//p2=p3
   	//p3=p4
  	//p4=p
	}//end for
*/
}//end merge sort
 
void plane::merge(int h, int m, struct point *U, struct point *V, 
   struct point *S)
{
  // merge algorithm taken from the book, altered to be stable
  int i = 0;
  int j = 0;
  int k = 0;
  while(i<h && j<m)
  {
  	// comparing x's the first pass
  	if(pass ==1)
  	{
  		if(U[i].x <= V[j].x)
  		{
  			S[k] = U[i];
  			i++;
  		}
  		else
  		{
  			S[k] = V[j];
  			j++;
  		}
  		k++;
  	} else if(pass ==2)
  	{
  		// comparing y's the second pass
  		if(U[i].y <= V[j].y)
  		{
  			S[k] = U[i];
  			i++;
  		}
  		else
  		{
  			S[k] = V[j];
  			j++;
  		}
  		k++;
  	}
  	if(i>h-1)
  	{
  		for(int z = 0; z<m; z++)
  		{
  			S[k+z] = V[z+j];
  		}
  	} else
  	{
  		for(int z = 0; z<m; z++)
  		{
  			S[k+z] = U[i+z];
  		}
  	}
  }
}//end merge

