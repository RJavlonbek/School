/**
   @file main.cpp
   @author Adam Gorman
   @date 9/4/2012
   @version 1.0
   
   This is the main file for our mathematic statistics program. The main purpose
   is to calculate the mean, median, and to sort the data
*/

#include <iostream>
#include <stats.h>

int main()
{  
  int userNumber = 0;
  int numbers[100];
  int count = 0;
  
  
  // Take input from user and place the response into the array
  std::cout << "Enter integers ('x' to quit): ";
  while( std::cin >> userNumber && count < 100)
  {
    numbers[count] = userNumber;
    ++count; 
  }
  
  
  // Print out the data in the array as entered
  std::cout << "\nData as entered: "; 
  for(int i = 0; i < count; ++i)
  {
    std::cout << " " <<  numbers[i];
  }
  
  
  // Calculate and print the Mean
  std::cout << "\nMean:   "; 
  float theMean = Mean(numbers, count);
  std::cout << theMean;
  
  
  // Calculate and print the Median
  std::cout << "\nMedian: "; 
  float theMedian = Median(numbers, count);
  std::cout << theMedian;
  
  
  //Print the data in the array, since it is now sorted.
  std::cout<< "\nData after sort: ";
  for(int i = 0; i < count; ++i)
  {
    std::cout << " " << numbers[i];
  }
  
  
  std::cout << "\n";
  
  //Terminate the program
  return 0;

} // end main

