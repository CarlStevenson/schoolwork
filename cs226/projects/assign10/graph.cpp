// graph.cpp
// Carl Stevenson
// 4/30/14
// Graphs
// Assignment 10
// Graph.cpp is the definition of a general graph using an adjacency list.
// *************************************************************************

#include "graph.h"

using namespace std;

graph::graph()
{
  // initialize the cycle status of the graph.
  cycle = false;


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
    cout<<"Do you have an edge to enter?";
    cin >> choice;
    if(choice == "y")
    {
      cout<<" y"<<endl;
      n1 = inCheck("What is the first node in the edge?");
      cout<<"You chose "<<n1<<endl;
      n2 = inCheck("What is the second node in the edge?");
      cout<<"You chose "<<n2<<endl;
      if((n1<0 || n1>=size)&&(n2<0 || n2>=size))
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

graph::~graph()
{
  delete adj;
}

graph::graph(const graph &theGraph)
{
  // not implemented
}

bool graph::DFS(int V)
{
  bool success;
  if(!topsort.QueueIsEmpty())
  {
    topsort.QueueDelete(success);
    while(success)
    {
      topsort.QueueDelete(success);
    }
    cycle = false;
  }
  if(V >=size || V < 0)
  {
    success = false;
  } else
  {
    cout<<"Depth first search: ";
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
      }
    }
    for(int i = 0; i<V; i++)
    {
      if(visited[i] == 0)
      {
        DFS_visit(i, visited);
      } 
    }
    success = true;
    cout<<endl;
  }
  //delete visited;
  return success;
}

void graph::DFS_visit(int V, int *visited)
{
  // we visit this node
  visited[V] = 1;
  // output the node
  cout<<V<<" ";
  // call its neighbors
  node *tracker = adj[V];
  // while there are still nodes in V's adjacency list
  while(tracker!=NULL)
  {
    // if it's not visited, visit it
    if(visited[tracker->name] == 0)
    {
      DFS_visit(tracker->name, visited);
    }else if(visited[tracker->name == 1])
    {
      // if it's visited, we have a cycle
      cycle = true;
    } else
    {
      // otherwise it's a finished node
    }
    tracker = tracker->next;
  }
  // mark the node as finished
  visited[V] = 2;
  // if there's no cycle found, insert the node into the topological sort list
  if(!cycle)
  {
    bool temp;
    topsort.QueueInsert(V, temp);
  }
}

bool graph::BFS(int V)
{
  bool success;
  if(V >=size || V < 0)
  {
    success = false;
  } else
  {
    queueClass queue;
    int visited[size];
    int holder;
    bool temp;
    node *adder;
    cout<<"Breadth first search: ";
    for(int i = 0; i<size; i++)
    {
      visited[i] = 0;
    }
    for(int i = V; i<size; i++)
    {
      if(visited[i] == 0)
      {
        queue.QueueInsert(i, temp);
        cout<<i<<" ";
        visited[i] = 1;
        while(!queue.QueueIsEmpty())
        {
          queue.QueueDelete(holder, temp);
          // queue all adjacent nodes in order
          adder = adj[holder];
          while(adder !=NULL)
          {
            if(visited[adder->name] == 1)
            {
              cycle = true;
            } else if(visited[adder->name] == 0)
            {
              visited[adder->name] = 1;
              queue.QueueInsert(adder->name, temp);
              cout<<adder->name<<" ";
            }
            adder= adder->next;
          }
          visited[holder] = 2;
        }
      }
    }
    for(int i = 0; i<V; i++)
    {
      if(visited[i] == 0)
      {
        queue.QueueInsert(i, temp);
        visited[i] = 1;
        while(!queue.QueueIsEmpty())
        {
          queue.QueueDelete(holder, temp);
          // queue all adjacent nodes in order
          adder = adj[holder];
          while(adder !=NULL)
          {
            if(visited[adder->name == 1])
            {
              cycle = true;
            } else
            {
              visited[adder->name] = 1;
              queue.QueueInsert(adder->name, temp);
              cout<<adder->name<<" ";
            }
            adder= adder->next;
          }
        }
      } 
    }
    cout<<endl;
    success = true;
  }
  return success;

}

bool graph::topoSort()
{
  bool success;
  // do a DFS from V
  if(DFS(0))
  {
    // if there is a cycle, return false
    if(cycle)
    {
      success = false;
    } else
    {
      // if there is no cycle, output the topological sorted queue
      cout<<"Topological sort: ";
      topsort.QueuePrint();
      cout<<endl;
      success = true;
    }

  } else
  {
    cout<<"Invalid input."<<endl;
    success = false;
  }
  return success;

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
