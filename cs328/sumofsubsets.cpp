// sumofsubsets.cpp
// Uses algorithm 5.4 from the book

#include <iostream>
#include <cmath>
using namespace std;

bool promising(int i, int weight, int total, int w[], int W)
{
	return (weight + total >=W) && (weight == W || weight + w[i] <= W);
}

void sos(int i, int weight, int total, int w[], int W, bool include[])
{
	if(promising(i, weight, total, w, W))
	{
		if(weight == W)
		{
			for( int j = 0; j<i; j++)
			{
				cout<<include[j]<<" ";
			}
			cout<<endl;
		}else
		{
			include[i] = true;
			sos(i+1, weight + w[i], total - w[i], w, W, include);
			include[i] = false;
			sos(i+1, weight, total-w[i], w, W, include);
		}
	}
}

int main()
{
	int W = 52;
	int w[] = {2, 10, 13, 17, 22, 42};
	bool include[] = {false,false,false,false,false,false};
	int total = 2 + 10 + 13 + 17 + 22 + 42;
	sos(0,0,total,w, W, include);

	return 0;

}