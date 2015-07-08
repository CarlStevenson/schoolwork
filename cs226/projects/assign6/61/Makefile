
CFLAGS = -c -Wall -g
LFLAGS = -g -o assign61

assign61: assign61.o queuei.o
	g++ assign61.o queuei.o $(LFLAGS)

assign61.o: assign61.cpp queuei.h
	g++ $(CFLAGS) assign61.cpp

queuei.o: queuei.cpp queuei.h
	g++ $(CFLAGS) queuei.cpp

clean:
	rm -f assign6i *.o *~ *#
