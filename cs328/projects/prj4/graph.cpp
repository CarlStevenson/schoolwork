// graph.cpp
// Carl Stevenson
// 4/30/14
// updated 12/1/14
// Graphs & cycles
// Assignment 4
// Graph.cpp is the definition of a general graph using an adjacency list.
// Edited from cs4 assignment 10 by Carl Stevenson
// *************************************************************************

#include "graph.h"


using namespace std;

graph::graph()
{

  size = inCheck("Please enter the number of vertices in the graph:");
  cout<<"You chose "<<size<<" vertices."<<endl;
  adj = new node*[size];
  for(int i = 0; i<size; i++)
  {
    adj[i] = NULL;
  }
  int n1;
  int n2;
  string choice;
  while(true)
  {
    cout<<"Do you have an edge to enter? (y/n)";
    cin >> choice;
    if(choice == "y")
    {
      cout<<" y"<<endl;
      n1 = inCheck("What is the first node in the edge?");
      cout<<"You chose "<<n1<<endl;
      n2 = inCheck("What is the second node in the edge?");
      cout<<"You chose "<<n2<<endl;
      if((n1<0 || n1>=size)||(n2<0 || n2>=size))
      {
        // invalid vertex choice, print message and continue
        cout<<"One of your chosen vertices does not exist."<<endl;
        continue;
      }
      node *newNode = new node;
      newNode->name = n2;
      if(adj[n1] == NULL)
      {
        adj[n1] = newNode;
      } else
      {
        newNode->next = adj[n1];
        adj[n1] = newNode;
      }

      
    } else if(choice == "n")
    {
      cout<<" n"<<endl;
      break;
    }
    cout<<endl;
  }
}
// destructor
graph::~graph()
{
  delete[](adj);
}
// copy constructor
graph::graph(graph &theGraph)
{
  // pull the adjacency list and size from the input graph
  theGraph.adjList(adj, size);

}
// returns the adjacency list in node pointer form
void graph::adjList(node ** &list, int &big)
{
  big = size;
  stack tempStack;
  node* finder;
  node* newNode;
  list = new node*[size]; 
  for(int i = 0; i<size; i++)
  {
    list[i] = NULL;
  }
  for(int i = 0; i<size; i++)
  {
    // collect all the things this one is adjacent to
    finder = adj[i];
    while(finder !=NULL)
    {
      tempStack.push(finder->name);
      finder = finder->next; 
    }
    //put those things into the adjacency list
    while(!tempStack.isEmpty())
    {
      newNode = new node;
      tempStack.pop(newNode->name);
      newNode->next = list[i];
      list[i] = newNode;
    }
  }
}

bool graph::DFS(int V)
{
  // resetting the DFS, clearing any remnants in the stack and emptying the
  // topsort queue
  bool success;
  while(theStack.pop())
  {
    // pop all the items left
  }
  if(V >=size || V < 0)
  {
    success = false;
  } else
  {
    cout<<"Depth first search: "<<endl;
    int visited[size];
    for(int i = 0; i<size; i++)
    {
      visited[i] = 0;
    }

    for(int i = V; i<size; i++)
    {
      if(visited[i] == 0)
      {
        DFS_visit(i, visited);
        // need to increment the already completed nodes to avoid multiple
        // detections (the new color)
        for(int j = 0; j<size; j++)
        {
          if(visited[j] == 2)
          {
            visited[j]++;
          } 
        }
      }
    }
    for(int i = 0; i<V; i++)
    {
      if(visited[i] == 0)
      {
        DFS_visit(i, visited);
        // need to increment the already completed nodes to avoid multiple
        // detections (the new color)
        for(int j = 0; j<size; j++)
        {
          if(visited[j] == 2)
          {
            visited[j]++;
          } 
        }
      } 
    }
    success = true;
    cout<<endl;
  }
  return success;
}

void graph::DFS_visit(int V, int *visited)
{
  // temp boolean for inserting and deleting
  bool temp;

  // we visit this node
  if(visited[V] < 2)
  {
    visited[V] = 1;
  }
  // working through this node, need to put it on the stack
  theStack.push(V);
  // output the node
  cout<<"Visiting: "<<V<<endl;
  // call its neighbors
  node *tracker = adj[V];
  // while there are still nodes in V's adjacency list
  while(tracker!=NULL)
  {
    // if it's not visited, visit it
    if(visited[tracker->name] == 0)
    {
      DFS_visit(tracker->name, visited);
    }else if(visited[tracker->name] == 1)
    {
      // if it's visited, we have a cycle
      // print the cycle
      printCycle(tracker->name);
    } else if(visited[tracker->name] == 2)
    {
      // if tracker-> == 2, we need to check because there are probably more
      // cycles
      DFS_visit(tracker->name, visited);
    } else
    {
      // otherwise it's a finished node
    }
    tracker = tracker->next;
  }
  // mark the node as finished, and pop it off the stack
  visited[V] = 2;
  theStack.pop();
}
void graph::printCycle(int nodenum)
{
  // transferint so we don't lose the queue when we print it out
  int transferint = 0;
  // found so we know that we're good to print numbers in the cycle
  bool found = false;
  // need a temp stack to read the items in the right order
  stack tempStack;
  while(theStack.pop(transferint))
  {
    tempStack.push(transferint);
  }
  // print the cycle
  cout<<"Cycle detected: "<<endl;
  while(tempStack.pop(transferint))
  {
    if(transferint == nodenum)
    {
      found = true;
    }
    if(found)
    {
      cout<<transferint<<" ";
    }
    // push the item back on theStack, so we don't lose it
    theStack.push(transferint);
  }
  // put on the last number
  cout<<nodenum<<endl;
}


// int inCheck(string prompt)
// inCheck loops until the user inputs valid input.
// it takes the prompt that is to be displayed as an argument.
// edited from code by Dr. Bracken
// re-used from previous assignments
// *************************************************************************
int graph::inCheck(string prompt)
{
  int choice;
  // loop until valid input given.
  while(true)
  {
    cout<<prompt<<endl;

    cin >> choice;

    if(cin.good())
    {
      break;
    }

    cout<<"You provided a non-integer or an integer not corresponding to "
        <<"a menu item. Please try again."<<endl;

    cin.clear();

    cin.ignore(120,'\n');
  } 
  // return the gathered input.
  return choice;
}
