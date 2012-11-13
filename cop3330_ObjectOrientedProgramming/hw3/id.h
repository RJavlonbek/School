/**
   @file    id.h
   @author  Adam Gorman
   @date    Sept 26, 2012
   @version 1.2

   The header for class ID.

   A simple class that handles names and ages
*/

#ifndef _ID_H
#define _ID_H

#include <iostream>  //needed for std::ostream

const int DEFAULT_AGE = -1;
const char* const DEFAULT_NAME = "#";

class ID
{

public:
  ID();                                 // default constructor
  ID(const char*, int);                 // constructor with 2 parameter
  ID(const ID&);                        // copy 
  ~ID();                                // destructor  
  const ID& operator=(const ID&);       // assignment operator
  
  void        SetName( const char* );   // sets the name field
  void        SetAge ( int );           // sets the age field
  const char* GetName() const;          // returns the name field
  int         GetAge () const;          // returns the age field
 
private:
  char * name_;                         // the name field
  int age_;                             // the age field

}; //end class ID

std::ostream& operator << (std::ostream&, const ID&); // stand-alone operator

#endif
