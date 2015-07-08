
CFLAGS = -c -Wall -g
LFLAGS = -g -o maintest

maintest: maintest.o testlist.o
	g++ maintest.o testlist.o $(LFLAGS)

testlist.o: testlist.cpp testlist.h
	g++ $(CFLAGS) testlist.cpp

clean: rm -f maintest *.o *~ *#
