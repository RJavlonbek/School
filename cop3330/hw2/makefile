# @file makefile
# @name Adam Gorman
# @date 9/14/2012
# @version 1.0
# makefile for stats project


stats.x: main.o stats.o
	g++ -Wall -Wextra -I. -ostats.x stats.o main.o

stats.o: stats.h stats.cpp
	g++ -c -Wall -Wextra -I. stats.cpp

main.o: stats.h main.cpp
	g++ -c -Wall -Wextra -I. main.cpp

