# @file    makefile
# @name    Adam Gorman
# @date    Sept 26, 2012
# @version 1.0

# makefile for ID project


# need all: or else it would only build test1.x

all: test1.x test2.x

test1.x: id.o test1.o
	g++ -W -Wall -Wextra -I. -otest1.x test1.o id.o

test2.x: id.o test2.o
	g++ -W -Wall -Wextra -I. -otest2.x test2.o id.o

test1.o: test1.cpp
	g++ -c -W -Wall -Wextra -I. test1.cpp

test2.o: test2.cpp
	g++ -c -W -Wall -Wextra -I. test2.cpp

id.o: id.h id.cpp
	g++ -c -W -Wall -Wextra -I. id.cpp
