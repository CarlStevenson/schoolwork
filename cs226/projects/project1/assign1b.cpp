// assign1b.cpp
// Written by: Carl Stevenson
// 2/14/14
// CPP and Recursion
// Assignment 1b.
// Problem being solved:
//   Calculating the number of 1's that occur in the binary
//   representation of a user-defined integer n.
// Flow of the program:
//   Take the integer as input from the user. Send it to one. One recursively
//   computes the number of ones in the binary representation of the number.
//   Outputs the result to the screen.
//   
// Program testing:
//   I have thoroughly tested my code and it works properly.
// *************************************************************************

// include the required header files
#include <cassert>
#include <iostream>

using namespace std;

// prototype for the functions used in the program

int one(int n);


// int one(int n)
//
// one takes an integer n and recursively computes the number of 1's in its 
// binary representation. It returns the computed number of 1's.
// *************************************************************************

int one(int n)
{
  // initialize return variable
  int sum;

  // base case, the number of ones in the binary representation of 0 is 0

  if(n==0)
  {
    sum = 0;
  }

  // inductive case
  // loop to find the largest binary digit in n,
  // subtract it from n,
  // add one to sum, recursively call sum for the remainder of n
  else
  {
    // initialize the largest binary digit to be 1
    int lbd = 1;
    
    // loop to find the largest binary digit in n
    while(true)
    {
      // if lbd * 2 is greater than n, lbd is the largest binary digit in n
      if((lbd * 2) > n)
      {
        // found a one. add it and the result of calling one on the remainder
        // to sum
        sum = 1 + one(n-lbd);
        // break out of the loop, we're done
        break;
      }
      // lbd = the next binary digit 
      lbd = lbd * 2;
    }
  }

  // return the calculated sum
  return sum;

} // end one

int main()
{
  // initial variable declarations
  // number is the number taken as input
  int number;
  // sum is the calculated number of 1's
  int sum;

  // Common Output
  cout<<"\nCarl Stevenson"<<endl;
  cout<<"CPP and Recursion"<<endl;
  cout<<"This program calculates the number of ones in the binary "<<endl;
  cout<<"Representation of a user defined integer.\n"<<endl;

  // take number from the user
  cout<<"Please enter the number for the calculation of ones: "<<endl;
  cin>>number;

  // make sure the number is greater than or equal to 0
  assert(number>=0);

  // echo input
  cout<<"Calculating the number of ones in "<<number<<endl;

  // calculate the number of ones
  sum = one(number);

  // echo input and output result
  cout<<"The number of ones in the binary representation of "<<number;
  cout<<": "<<sum<<"\n"<<endl;

  return 0;

} // end main

