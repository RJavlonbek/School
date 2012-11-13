/**
   @file stats.h
   @author Adam Gorman
   @date 9/4/2012
   @version 1.0
   
   This is the statistics header implementation file where we will declare our
   functions.
*/

#ifndef STATS_H
#define STATS_H

float Mean   (const int*, size_t); // calculates mean of data in a
float Median (int*, size_t);       // calculates median of data in a
void  Swap   (int&, int&);            // interchanges values of x and y
void  Sort   (int*, size_t);       // sorts the data in a in ascending order

#endif
