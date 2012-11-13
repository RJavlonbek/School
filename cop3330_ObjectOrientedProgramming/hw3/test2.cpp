/**
   test2.cpp

   test2 harness for class ID
   specifically tests for ownership of data pointed to by name_
*/

#include <cstdlib>
#include <iostream>
#include <iomanip>
#include <cstring>
#include <id.h>

int main()
{
  char myName [9] = "John Doe";
  int  myAge = 100;

  char tonyName [12] = "Tony Staple";
  int  tonyAge = 30;

  char mattName [13] = "Matt Johnson";
  int  mattAge = 20;

  ID p1(myName, myAge), p2(tonyName,tonyAge), p3(mattName, mattAge);
  std::cout << " IDs after declaration:\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p2 = " << p2 << '\n';
  // std::cout << "  p3 = " << p3 << '\n';

  strcpy (myName,tonyName);    // copy tonyName to myName
  strcpy (tonyName,mattName);  // copy mattName to tonyName
  myAge = tonyAge;             // copy tonyAge to myAge
  tonyAge = mattAge;           // copy mattAge to tonyAge

  std::cout << " IDs after client program changes myName and myAge:\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p2 = " << p2 << '\n';
  // std::cout << "  p3 = " << p3 << '\n';
}
