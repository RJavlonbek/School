/**
   @file stats.cpp
   @author Adam Gorman
   @date 9/4/2012
   @version 1.0
   
   This is the implementation of the stats file.    
*/

#include <iostream>
#include <stats.h>

//Calculate the Mean
float Mean (const int* a, size_t size)
{
  float sum = 0;
  for(size_t i = 0; i < size; ++i)
  {
    sum += a[i];
  }
  return (sum / size);
}

//Calculate the Median and Sort
float Median (int* a, size_t size)
{
  Sort(a, size);
  if(size % 2 == 0)
  {
    return ( a[size / 2] + a[(size / 2) - 1] ) / 2.0f ;
  }
  else
  {
    return a[size / 2];
  }
}


//Swap the location of 2 variables
void Swap (int& x, int& y)
{
  int temp = x;
  x = y;
  y = temp;
}


//Sort the array 
void Sort (int* a, size_t size)
{
  for(size_t i = 0; i < (size - 1); ++i)
  {
    for(size_t j = i + 1; j < size; ++j)
    {
      if(a[i] > a[j])
      {
        Swap(a[i], a[j]);
      } 
    }   
  } 
}

