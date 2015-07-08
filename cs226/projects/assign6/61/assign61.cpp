// assign61.cpp
// Written by: Carl Stevenson
// 4/9/14
// Queues
// Assignment 61.
// Problem being solved:
//   Calculating the sum and average of a queue defined by the data in
//   a file provided by the user.
// Flow of the program:
//   Prompt for file name, then perform calculations and output.
//
// Program testing:
//   I have thoroughly tested my code and it works properly.
// Edited from code provided by Dr. Bracken
// *************************************************************************

#include <fstream>
#include <cassert>
#include <iostream>
#include "queuei.h"
#include <string>
int main()
{
  string fn;
  ifstream fin;
  int newitem;
  bool success;
  queueClass queue1;
  cout<<"Please enter the name of the file containing your data"<<endl;
  cin>>fn;
  cout<<"The file name entered is "<<fn<<endl;
  fin.open(fn.c_str());
  assert(fin.is_open());
  while(true)
  {
     fin>>newitem;
     if(fin.eof())
     {
        break;
     }
     cout<<"About to insert "<<newitem<<"   into the queue "<<endl;
     //Insert newitem into the queue
     queue1.QueueInsert(newitem, success);
     
  }//end of input loop
  //print the contents of the queue
  queue1.QueuePrint();

  //calculate sum and average
  queueClass queue2;
  int noelements = queue1.NumberOfQueueElements();
  int element;
  int sum;
  float average;
  for(int i=0; i<noelements; i++)
  {
    queue1.QueueDelete(element, success);
    sum = sum + element;
    queue2.QueueInsert(element, success);
  }
  cout<<"Sum of the queue: "<<sum<<endl;

  // calculate the average
  if(noelements == 0)
  {
    cout<<"Queue average: none"<<endl;
  }else{
    average = (float)sum/noelements;
    cout<<"Queue average: "<<average<<endl;
  }
  
  // print the contents of the queue
  queue2.QueuePrint();
}//end of main
