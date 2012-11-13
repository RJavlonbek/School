/**
   @file    uintset.cpp
   @author  Adam Gorman
   @date    Oct 4, 2012
   @version 1.2

   A simple class UIntSet that holds unsigned integers.
   Implements the use of a BitVector and some
   accompanying functions such as test, union, intersection
   and difference. Among a few others
*/


#ifndef _UINTSET_CPP
#define _UINTSET_CPP

#include <uintset.h>
#include <iostream>
#include <bitvect.h>

UIntSet::UIntSet(): bv_(UINTSET_DEFAULT_SIZE)
{   
}

UIntSet::UIntSet(size_t n): bv_(n)
{
 
}

UIntSet::UIntSet(const UIntSet & uis):   bv_(uis.bv_)
{
}

UIntSet::~UIntSet()
{
  //Nothing needed here 
}

// inserts n into set
void UIntSet::Insert ( unsigned long n )
{
  bv_.Set(n);
}

// removes n from set
void UIntSet::Remove ( unsigned long n )
{
  bv_.Unset(n);
}

  
// makes set empty
void UIntSet::Clear ()
{
  bv_.Unset();  
}

// returns true iff n is in set
bool UIntSet::Member ( unsigned long n ) const
{
  return bv_.Test(n);
}

void UIntSet::Dump(std::ostream& os) const
{
  bv_.Dump(os);
}
  
  
// true iff set is empty
bool UIntSet::Empty() const
{
  for(size_t i = 0; i < Range(); ++i)
  {
    if(bv_.Test(i))
    {
      return false;
    }
  }
  return true;
}
  
// returns number of elements in set
size_t UIntSet::Size() const
{
  size_t counter = 0;
  for(size_t i = 0; i < Range(); ++i)
  {
    if(Member(i))
    {
      ++counter;
    }
  }
  return counter;
}

// returns upper bound of range/universe [0,ub)
size_t UIntSet::Range() const
{
  return bv_.Size();
}

  
// set = s (assignment operator)
UIntSet& UIntSet::operator =  (const UIntSet& s)
{
  if(this != &s)
  {
    Clear();
    for(size_t i = 0; i < Range(); ++i)
    {
      if(s.Member(i))
      {
        Insert(i);
      }
    }
    // old implementation
    //bv_ = s.bv_;
  }
  return *this;
}

// set = set union s
UIntSet& UIntSet::operator += (const UIntSet& s)
{
  for(size_t i = 0; i < Range(); ++i)
  {
    if(s.Member(i))
    {
      Insert(i);
    }
  }
  return *this;
}

  
// set = set intersection s
UIntSet& UIntSet::operator *= (const UIntSet& s)
{
  for(size_t i = 0; i < Range(); ++i)
  {
    if(s.Member(i) && Member(i))
    {
      Insert(i);
    }
    else
    {
      Remove(i);
    }
  }
  return *this;  
}
  
  
// set = set difference s
UIntSet& UIntSet::operator -= (const UIntSet& s)
{
  for(size_t i = 0; i < Range(); ++i)
  {
    if(Member(i) && s.Member(i)) 
    {
      Remove(i);
    }
  }
  return *this;  
}


// returns s1 union s2
UIntSet operator + (const UIntSet& s1, const UIntSet& s2)
{
  UIntSet uis(s1);
  uis += s2;
  return uis;
}

// returns s1 intersection s2 
UIntSet operator * (const UIntSet& s1, const UIntSet& s2)
{
  UIntSet uis(s1);
  uis *= s2;
  return uis;
}

// returns s1 difference s2
UIntSet operator - (const UIntSet& s1, const UIntSet& s2)
{
  UIntSet uis(s1);
  uis -= s2;
  return uis;
}

// true iff s1 and s2 are equal as sets   
bool operator == (const UIntSet& s1, const UIntSet& s2)
{
  for(size_t i = 0; i < s1.Range(); ++i)
  {
    if(s1.Member(i) != s2.Member(i))
    {
      return false;
    }
  }
  return true;
}

// true iff s1 and s2 are not equal
bool operator != (const UIntSet& s1, const UIntSet& s2)
{
  return !(s1==s2);
}

// output operator
std::ostream & operator << (std::ostream & os, const UIntSet & s)
{
  os << '{';
  for(size_t i = 0; i < s.Range(); ++i)
  {
    if(s.Member(i))
    {
      os << ' ' << i;
    }
  }
  os << " }";
  return os;  
}


// THE END OF FILE

#endif
