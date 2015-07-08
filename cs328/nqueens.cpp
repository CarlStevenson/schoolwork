// nqueens.cpp
// uses algorithm 5.1 from the book to calculate nqueens
#include <iostream>
#include <cmath>
using namespace std;
bool promising(int i, int col[])
{
	int k = 1;
	bool good = true;
	while(k<i && good)
	{
		if(col[i] == col[k] || abs(col[i] - col[k]) == i-k)
		{
			good = false;
		}
		k++;
	}
	return good;
}

void queens(int i, int n, int col[])
{
	if(promising(i, col))
	{
		if(i ==n)
		{
			for(int j = 1; j<=n; j++)
			{
				cout<<col[j]<<" ";
			}
			cout<<endl;
		}else
		{
			for(int j = 1; j<=n; j++)
			{
				col[i+1] = j;
				queens(i+1, n, col);
			}
		}
	
	}
}

int main()
{
	int n;
	cin >> n;
	int col[n+1];
	queens(0, n, col);
}

