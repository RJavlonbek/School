# Adam Gorman
# October 3rd, 2012
# COP3353 Unix
# Assignment #5

# note: the pdf says its assignment 4, which is wrong...

CC = g++
CFLAGS = -g -c -I.
LFLAGS = -g

#-c compile only
#-g debugger option
#-I. current directory
# g++ is the compiler I have chosen, alternatively gcc
# CC is the macro of the compiler
# CFLAGS is for compiling flags
# LFLAGS is for linking flags

#build and link the primary executable main
#I would have preferred to have called it main.x
#but #3 indicated it should be called 'main'
#though there is a typo of etxtra.o in that same line :)

main: main.o history.o size.o extra.o
	$(CC) $(LFLAGS) -omain main.o history.o size.o extra.o


#compile main

main.o: main.cpp size.h history.h
	$(CC) $(CFLAGS) main.cpp

# size.h and history.h are included in main.cpp so I included them here


# compille history.cpp which will include its header

history.o: history.cpp history.h
	$(CC) $(CFLAGS) history.cpp


# compile size.cpp which will include its header

size.o: size.cpp size.h history.h
	$(CC) $(CFLAGS) size.cpp

# I include history.h only because it was referenced in size.h
# It is sneakily snuck in use in the size.cpp indirectly!


# compile extra.cpp which will include its header

extra.o: extra.cpp extra.h
	$(CC) $(CFLAGS) extra.cpp


# The clean command
# rm will remove the follow listed files
# The -f flag will force removal, and not prompt the user

clean:
	rm -f extra.o size.o history.o main.o

