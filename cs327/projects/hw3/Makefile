all : docs sc

scanner.o : globals.h scanner.c
error.o : globals.h error.c
parser.o : globals.h parser.c
emitter.o : globals.h emitter.c
symtab.o : globals.h symtab.c

sc : sc.c globals.h scanner.o error.o symtab.o parser.o emitter.o
	gcc $(CFLAGS) -o sc sc.c scanner.o error.o symtab.o parser.o emitter.o

docs : 
	doxygen
