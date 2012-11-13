/*
Gorman, Adam
COP-3252
Assignment 5
3/22/2012
*/

// NumberNotFoundException.java
public class NumberNotFoundException extends Exception 
{
   // no-argument constructor specifies default error message
   public NumberNotFoundException() 
   {
      super( "Number not found in array" );
   }
   
   // constructor to allow customized error message
   public NumberNotFoundException( String message )
   {
      super( message );
   }
} // end class NumberNotFoundException
