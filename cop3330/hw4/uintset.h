/**
   @file    uintset.h
   @author  Adam Gorman
   @date    Oct 4, 2012
   @version 1.2

   The header file for a UIntSet
   Unsigned Integer Set
   Allows simple bit operations on a set
   Utilizing a BitVector to perform some
   basic funnctions
*/

#ifndef _UINTSET_H
#define _UINTSET_H

#include <bitvect.h>

//default maximum is 64
const int UINTSET_DEFAULT_SIZE = 64;

class UIntSet
{
  
private:
  fsu::BitVector bv_;    // bit vector representing set
  
public:
  UIntSet();
  UIntSet(unsigned long);
  UIntSet(const UIntSet& );
  ~UIntSet();
   
  void     Insert ( unsigned long n );        // inserts n into set
  void     Remove ( unsigned long n );        // removes n from set
  void     Clear  ();                         // makes set empty
  bool     Member ( unsigned long n ) const;  // returns true iff n is in set
  void     Dump   (std::ostream& os ) const;  // prints the dump
  
  bool     Empty  () const; // true iff set is empty
  size_t   Size   () const; // returns number of elements in set
  size_t   Range  () const; // returns upper bound of range/universe [0,ub)

  UIntSet& operator =  (const UIntSet& s);  // set = s (assignment operator)
  UIntSet& operator += (const UIntSet& s);  // set = set union s
  UIntSet& operator *= (const UIntSet& s);  // set = set intersection s
  UIntSet& operator -= (const UIntSet& s);  // set = set difference s  
  
}; // end UIntSet

  // returns s1 union s2
UIntSet operator +  (const UIntSet& s1, const UIntSet& s2);
// returns s1 intersection s2 
UIntSet operator *  (const UIntSet& s1, const UIntSet& s2);
// returns s1 difference s2
UIntSet operator -  (const UIntSet& s1, const UIntSet& s2);
// true iff s1 and s2 are equal as sets   
bool    operator == (const UIntSet& s1, const UIntSet& s2);
// true iff s1 and s2 are not equal
bool    operator != (const UIntSet& s1, const UIntSet& s2);
    
// output operator
std::ostream& operator << (std::ostream& os, const UIntSet& s);


#endif
