
// Random Class

// Thu Oct  5 15:17:21 EDT 1995 
// Revised constructor to set seed from internal clock.

// Anthony Kapolka
// CS 204
// Tue Feb 28 11:19:35 EST 1995

#ifndef random_h
#define random_h

class myrandom
{

public:

	myrandom();
	// constructor which sets default value using system clock
	// PRE: none; POST: SEED set.

	myrandom(unsigned long int sd);
	// constructor which sets default value to user supplied seed
	// PRE: sd valid; POST: SEED set.

	float rnd();
	// random returns a uniform random number from the range [0,1)
	// PRE	SEED initialized
	// POST return value [0,1)

private:

	unsigned long int SEED;

}; // end random

#endif
