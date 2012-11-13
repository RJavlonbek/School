/* 
	Cop3252 - Assignment 3
	Name: Adam Gorman
	
	
Acceptance Criteria:
	1. You’ll need public classes: // Check
 		public class Knight
 		public class Stars
 		
	2. You’ll need to create a driver class which will contain your main method: // Check
 		public class KnightDriver
 		
	3. We need the ability to enter the Knight’s attributes 
	(name, health, number of battles, age, and amount of gold procured 
	from pillaging) using the constructor from class Knight.
	// 

	4. Instance variables must be accessed via set and get methods.
	// get and set methods created for all private instance variables // Check
	
	5. Command line interaction is boring, so we’ll need to spice things up with 
	Dialog boxes utilizing the javax.swing package for our input.
	//Dialog output using javax.swing Check
	
	6. You’ll need 7 input dialog boxes. Five for the knight’s attributes. 
	Two for the number of starts (rows & columns) that the knight will be viewing.
	//7 input dialog boxes created, check!
	
	
	7. Notice in the sample output that the stars are not blocked, this is how your output should be as well.
	// Output stars are staggerd the same way, Check
	
	8. The output can be via the command line or if you’d like to get fancy, via a dialog box.
	// Offered both options, Check
	
	9.  Do not use any mechanisms beyond the scope of Chapter's 1-4.
	// I don't think I did. If I did, I did so unknowingly. 
	
	10.  Zip up and submit your KnightDriver.java, Knight.java & Stars.java files.
	// Check
	
 */

public class KnightDriver {
	
	public static void main(String[] args)
	{
		System.out.println("Launching Assignment 3\n");
		
		//creating a new instances for Knight and Stars
		Knight myKnight = new Knight();
		Stars myStars = new Stars();
		
		//getting the user input to populate the variables for Knight and Stars
		myKnight.getUserInput();
		myStars.getUserInput();
		
		//Passing my sky to the Knight
		myKnight.setSky(myStars.getSky());
		// Alternatively I could have created myStars inside of myKnight but I chose to not.
		
		//Sinlge popup for ALL information
		myKnight.PopupOutput();
		
		//seperate outputs
		myKnight.PrintConsoleOutput();
		myStars.printConsoleStars();
	}
}