# make file!
# V 0.2
#
# Some vars:
#  CC = compiler 
CC=g++
#  CFLAGS = compiler flags
CFLAGS=-g
#  LDFLAGS = link flags
LDFLAGS=
#
#  BIN = final name of the binary we create (the program itself)
BIN=game
#  SOURCES, all the source files of this project
SOURCES=board.cc ghost.cc main.cc myrandom.cc randplayer.cc player.cc 
#  OBJECTS, the object files BIN relies on -- a mod of SOURCES
OBJECTS=$(SOURCES:.cc=.o)

$(BIN): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

.cc.o:
	$(CC) $(CFLAGS) -c $< -o $@

# clean command to start freash
clean: 
	rm *.o *.out $(BIN) 
