// assign1a.cpp
// Written by: Carl Stevenson
// 2/12/14
// CPP and Recursion
// Assignment 1a.
// Problem being solved:
//   Summing the first n elements of a user created array. 
// Flow of the program:
//   Takes inputs of amount of values, the values and the desired number
//   of elements to sum. It computes the sum recursively using compute.
//   It then outputs the sum of the first n elements of the array.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

// include the required header files
#include <cassert>
#include <iostream>

using namespace std;

// prototype for the function used in the program
int compute(const int array[], int choice);


// int compute(int choice, int array[])
//
// compute takes an integer choice and an integer array array
// it recursively computes the sum of the first choice slots in array
// then returns the sum.
// ************************************************************************

int compute(const int array[], int choice)
{

  // the return variable 
  int sum;
  // base case, the sum of 1 value is the value itself
  if(choice == 1)
  {
    // the sum is the only value passed
    sum = array[0];
  }
  // inductive case
  // define the variables needed to recursively call compute
  // split the array, then compute the sum
  else
  {
    // holder is the integer divison of the amount to sum
    // by 2. used to make the code easier to read
    int holder = choice/2;
    // array for the left half of the split to compute
    int left[holder];
    // array for the right half of the split to compute, with an
    // extra slot in case of an odd number
    int right[(holder) +1];
    // fill the left and right arrays
    for(int i = 0; i<holder; i++)
    {
      left[i] = array[i];
      right[i] = array[i+holder];
    }
    // if the number going in is odd, account for it by putting 
    // the remainder in the last slot of right[], then  compute sum
    // based on the even number of values in left and odd number in
    // right
    if(choice%2)
    {
      right[holder] = array[choice-1];
      sum = compute(left, holder) + compute(right, (holder + 1));
    }
    // else if the number going in is even, put 0 in the last slot of right[]
    // to avoid errors in the array, then compute sum based on the even number
    // of values in left and right
    else
    {
      right[holder] = 0;
      sum = compute(left, holder) + compute(right, holder);
    }
  }

  // return the calculated sum
  return sum;
}

int main()
{
  // initial variable declarations
  int size;
  int array[100];
  int choice;
  int sum;

  // Common Output
  cout<<"\nCarl Stevenson"<<endl;
  cout<<"CPP and Recursion"<<endl;
  cout<<"This program sums the first n elements of a user created array"<<endl;
  cout<<endl;

  // Prompt for the array size
  cout<<"Please enter the size of the integer array,";
  cout<<" between 1 and 100:"<<endl;

  // take the array size input
  cin>>size;
  // assert that the value is within acceptable bounds
  assert(size<=100 && size>=1);

  // echo input
  cout<<"You chose the integer array to be of size "<<size<<endl;

  // ask for the values of the slots indicated
  for(int i = 0; i<size; i++)
  {
    cout<<"Please enter the value for slot "<<i+1<<":"<<endl;
    cin>>array[i];
    // echo input
    cout<<array[i]<<" is the value in slot "<<i+1<<endl;
  }

  // prompt for the number of elements to sum
  cout<<"Please enter the first n elements in the array to be summed:"<<endl;
  cin>>choice;

  // assert that the number of elements to sum is less than or equal to the
  // number of input values, and equal to or greater than 0
  assert(choice<=size && choice>=1);

  // echo input
  cout<<"Summing the first "<<choice<<" elements of the array."<<endl;

  // run recursive algorithm compute to sum the elements
  sum = compute(array, choice);

  //output the array 

  cout<<"The array that you entered: [";

  for(int i=0; i<size; i++)
  {
    cout<<array[i];

    // ensure that there is neat output by only outputting commas if 
    // there is a number following it
    if(i<size-1)
    {
      cout<<",";
    }
  }

  // print the end bracket for neat output
  cout<<"]"<<endl;

  // output the sum
  cout<<"The sum of the first "<<choice<<" elements of the array: ";
  cout<<sum<<"\n"<<endl;

  return 0;
}

