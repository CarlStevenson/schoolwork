#include <cstring>
#include <iostream>
#include "listc.cpp"

using namespace std; 
int main()
{
  int a = 4;
  int *b;
  int *c;
  b = &a;
  c = &a;


  cout<<a<<endl;
  if(b == c)
  {
    cout<<"good"<<endl;
  }

  node *temp;
  node *pointer1;
  
  node *pointer2;
  pointer2 = new node;
  node *head = new node;
  head->item = 3;
  head->next = pointer2;
  pointer2->next = head;


  pointer1 = new node;
  pointer1->next = head;
  head = pointer1;


  if(head->next == pointer1->next)
  {
    cout<<"good"<<endl;
  }


  return 0;
}