// driver.cpp
// Written by: Carl Stevenson
// 10/23/14
// driver.cpp uses the plane class to find the closest pair in a plane
// Skeleton code provided by Dr. Bracken
// **************************************************************************


#include "plane.h"
#include <iostream>
int main()
{

	// intro text
	cout<<"\nCarl Stevenson"<<endl;
	cout<<"Closest Pair Problem"<<endl;
	cout<<"This program finds the closest two points in a plane.\n"<<endl;

	plane myplane;
	myplane.printpoints();
	myplane.findclosest();
	cout<<"Exiting"<<endl;
}//end of main
