
CFLAGS = -c -Wall -g
LFLAGS = -g -o assign62

assign62: assign62.o queuea.o
	g++ assign62.o queuea.o $(LFLAGS)

assign62.o: assign62.cpp queuea.h
	g++ $(CFLAGS) assign62.cpp

queuea.o: queuea.cpp queuea.h
	g++ $(CFLAGS) queuea.cpp

clean:
	rm -f assign6i *.o *~ *#
