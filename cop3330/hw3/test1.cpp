/**
   test.cpp

   test harness for class ID
*/

#include <cstdlib>
#include <iostream>
#include <iomanip>
#include <id.h>
// #include <id.cpp> // in lieu of makefile

const size_t arraySize = 10;
const size_t numDigits = 2; 

ID CopyCheck (ID id)  // pass in by value calls CC
{
  ID x(id);  // initialization calls CC (NOT assignment!)
  return x;  // return by value calls CC
}

void AssignCheck (const ID& idIn, ID& idOut)  // pass in by reference - no copies made
{
  idOut = idIn;  // calls assignment (not CC)
}

int main()
{
  ID p1("Chris Smith", 100), p2;
  std::cout << " IDs after declaration:\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p2 = " << p2 << '\n';

  p1.SetName("Copy Checker");
  p1.SetAge(10);
  p2.SetName("Assign Checker");
  p2.SetAge(20);
  std::cout << " IDs after Set:\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p2 = " << p2 << '\n';

  ID p3 = CopyCheck(p1);
  std::cout << " IDs after p3 = CopyCheck(p1):\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p3 = " << p3 << '\n';

  // AssignCheck(p2, p3);
  std::cout << " IDs after AssignCheck(p2,p3):\n"; 
  std::cout << "  p2 = " << p2 << '\n';
  std::cout << "  p3 = " << p3 << '\n';

  ID p4 ("Transitive Assignment Check", 50);
  p1 = p2 = p3 = p4;
  std::cout << " IDs after p1 = p2 = p3 = p4:\n"; 
  std::cout << "  p1 = " << p1 << '\n';
  std::cout << "  p2 = " << p2 << '\n';
  std::cout << "  p3 = " << p3 << '\n';
  std::cout << "  p4 = " << p4 << '\n';

  ID * idarray = new ID [arraySize];
  std::cout << " ID Array after declaration:\n";
  for (size_t i = 0; i < arraySize; ++i)
  {
    std::cout << "  id[" << std::setw(numDigits) << i << "] = " << idarray[i] << '\n';
  } 

  for (size_t i = 0; i < arraySize; ++i)
  {
    idarray[i].SetName("A. B. Student");
    idarray[i].SetAge(17 + i);
  } 
  std::cout << " ID Array after Set:\n";
  for (size_t i = 0; i < arraySize; ++i)
  {
    std::cout << "  id[" << std::setw(numDigits) << i << "] = " << idarray[i] << '\n';
  } 

  // */
}
