// assign1.cpp
// Carl Stevenson
// 8/7/14
// Divide and Conquer
// Assignment 1
// Problem being solved:
// 	This program divides a list of numbers into two sublists of size n/2 where
// 	the difference of the sums of the two lists are maximized.

#include <iostream>
#include <cassert>
using namespace std;

void kpartition(int k, int array[], int first, int last);
int main()
{
	// size of the array being input
	int array_size;
	// the array
	int array[100];
	// sublist sum variables
	int sub1sum = 0;
	int sub2sum = 0;

	// intro
	cout<<"\nCarl Stevenson"<<endl;
	cout<<"Divide and Conquer"<<endl;
	cout<<"This program accepts an array defined by the user, then"<<endl;
	cout<<"partitions it into 2 sublists, where the difference"<<endl;
	cout<<"between the two lists is maximized."<<endl<<endl;
	cout<<"Enter the size of the array you will be inputting (must be even): "<<endl;
	cin>>array_size;
	assert(array_size>=1 && array_size<=100 && array_size%2 == 0);
	
	cout<<"You chose the array to be of size "<<array_size<<endl;

	for(int i =0; i<array_size; i++)
	{
		cout<<"Please enter item "<<i+1<<" of the array: "<<endl;
		cin>>array[i];
		assert(cin.good());
		cout<<"You input the value "<<array[i]<<endl;
	}

	cout<<"\nThe input array: [";
	for(int i=0; i<array_size; i++)
	{
		cout<<array[i];
		if(i != array_size-1)
			{
				cout<<", ";
			}
	}
	cout<<"]"<<endl;

	// kpartition(k, array, first, last)
	kpartition(array_size/2, array, 0, array_size-1);

	cout<<"Sublist 1: [";
	for(int i=0; i<array_size/2; i++)
	{
		cout<<array[i];
		sub1sum +=array[i];
		if(i != (array_size/2)-1)
			{
				cout<<", ";
			}
	}
	cout<<"]"<<endl;

cout<<"Sublist 2: [";
	for(int i=array_size/2; i<array_size; i++)
	{
		cout<<array[i];
		sub2sum +=array[i];
		if(i != array_size-1)
			{
				cout<<", ";
			}
	}
	cout<<"]"<<endl;

	cout<<"Difference of the sums: "<<sub2sum -sub1sum<<endl;
	return 0;
}

// kpartiton uses the partition algorithm from the textbook
void kpartition(int k, int array[], int first, int last)
{
	if(last > first)
	{
		cout<<"kpartition entered."<<endl;
		int i,j, temp, pivot;
		pivot = array[first];
		j = first;
		for(i = first+1; i<=last; i++)
		{
			if(array[i] < pivot)
			{	
				j++;
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}

		temp = array[first];
		array[first] = array[j];
		array[j] = temp; 
	
		// continue the algorithm recursively
		if(k < j-first+1)
		{
			kpartition(k, array, first, j-1);
		}
		else if(k == j-first+1)
		{
			//done
		}else{
			kpartition(k-(j-first+1), array, j+1, last);
		}
	}
}