
// Random Class

// Thu Oct  5 15:17:21 EDT 1995 
// Revised constructor to set seed from internal clock.

// Anthony Kapolka
// CS 204
// Tue Feb 28 11:19:35 EST 1995

using namespace std;

#include <iostream> // only for examplemain() procedure

#include <sys/times.h> // to access the system clock for initial seed
#include "myrandom.h"

myrandom::myrandom()

// constructor which sets default value using system clock
// PRE: none; POST: SEED set.
{
  struct tms clock;
  SEED = (unsigned long int)times(& clock);
}

myrandom::myrandom(unsigned long int sd)

// constructor which sets default value to user supplied seed
// PRE: sd valid; POST: SEED set.
{
   SEED = sd;
}


float myrandom::rnd()

// random::rnd returns a uniform random number from the range [0,1)
// simplified version of algorithm from CACM, v31, n10, Oct 1988 p. 1195.
// discussed in Oh Pascal, 3 ed, p A14-17.
// PRE	SEED initialized, machine handles 32 bit math
// POST return value [0,1)

{
	const int mod = 2147483647; // ((2^31)-1)
	const int mul = 16807;      // (7^5)

	// this implements the formula  Xi+1 = (a Xi + c) MOD m
	// where mul=a, mod=m and c=0.

        SEED = (mul * SEED) % mod;

	return ( float(SEED) / float(mod));
}


/* int examplemain()

// provides an example of now to declare and use random class
// you can rename this function main, compile this alone and run it.
{
	unsigned long int s;
	int i;

	random q; 
	cout << "Using default seed: " << endl;
	for (i=0; i<20; i++) cout << (q.rnd()) << "\n";

  	cout << endl << "Please supply an integer seed: ";
	cin >> s;
	cout << "\n";

	random r(s);
	for (i=0; i<20; i++) cout << (r.rnd()) << "\n";
  
	return 0;
}

*/
