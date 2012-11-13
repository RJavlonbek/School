/**
   @file    id.cpp
   @author  Adam Gorman
   @date    Sept 27, 2012
   @version 1.2
   
   The implementation of ID.
   IT is a simple class that holds a name and age

   I added the forced null termination in SetName
   I didn't originally have it forced there, is this required?
*/

#ifndef _ID_CPP
#define _ID_CPP

#include <id.h>
#include <cstring>

//default constructor
ID::ID(): name_(0), age_(0)
{
  SetName(DEFAULT_NAME);
  SetAge(DEFAULT_AGE);  
}

//constructor with 2 parameters
ID::ID(const char* name, int age): name_(0), age_(0)
{
  SetName(name);
  SetAge(age);
}

//copy constructor
ID::ID(const ID& rhs): name_(0), age_(0)
{
  SetName(rhs.GetName());
  SetAge(rhs.GetAge());
}

//assignment operator
const ID& ID::operator=(const ID& rhs)
{
  if(this != &rhs)  //make sure its not the same thing!
  {
    SetName(rhs.GetName());
    SetAge(rhs.GetAge());
  }
  return *this;
}

//destroy the ID
//delete the char array
//don't need to delete things I didn't use 'new' on, such as int
//because of dynamic memory allocation

ID::~ID()
{
  delete[] name_;
}

//set the name
void ID::SetName(const char* name)
{
  if(name_ != NULL) // free up memory
    delete [] name_;
  name_ = new char[strlen(name) + 1]; // resize + 1 for null terminate 
  strcpy(name_, name);                // copy the character string
  name_[ strlen(name) ] = '\0';
}

//set the age
void ID::SetAge(int age)
{ 
  age_ = age;
}

// get the name
const char* ID::GetName() const
{
  return name_; 
}

// get the age
int ID::GetAge() const
{
  return age_;
}


//used for printing out
//I used \t because it was used in the IDTest#_i.x cases, needed to match!
std::ostream& operator << (std::ostream& stream, const ID& rhs)
{
  stream << rhs.GetName() << "\t" << rhs.GetAge();
  return stream;
}



#endif
