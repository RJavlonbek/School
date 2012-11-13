import java.util.Scanner;

/*
 Assignment 2
 Name: Adam Gorman
 Date: 1-6-2012
 COP3252

 Our character will be a knight from the round table who you can name what you wish. 
 We'll call ours Sir Jav-a-lot.  

 Is his name the name we define or Sir Jav-a-lot ?  or both?


 Acceptance Criteria:
 1.  Ability to enter Sir Jav-a-lot's attributes.
 2.  Sir Jav-a-lot's information will be shown on the screen.
 a.  In addition to showing each of Sir Jav-a-lot's attributes, we need to know the average gold accumulated per battle.
 3.  This is to be a command line interface only.
 4.  Do not use any mechanisms beyond the scope of Chapter's 1 & 2.
 5.  Output must be displayed using the printf method.
 6.  Submit your Assignment2.java file.
 */

public class Assignment2 {

	public static void main(String[] args)
	{
		// Initializing and Declaring Variables //
		String name = "";
		int health = 0;
		int age = 0;

		float numBattles = 0;
		float gold = 0;
		float avgGpB = 0; // Average Gold per Battle

		Scanner in = new Scanner(System.in);

		System.out.printf("Welcome to the land of Camelot.\n");

		// //////////////////////////////////////
		// ///////////// Input //////////////////
		// //////////////////////////////////////

		System.out.printf("What is your Knight's Name?: \n");
		name = in.nextLine();
		// name = in.next();
		// I chose to use nextLine() instead of next() because next()
		// can not handle a string with spaces such as "Billy Bob"
		// It also cannot handle "Sir Jav-a-lot" because of the space
		
		// the only real benefit to use next() instead of nextLine()
		// would be if you wanted to enter all the data at once
		// i.e.  "bob 100 15 27 1000"  for 100 hp, 15 battles, 27 age, 1000 gold

		System.out.printf("How much health does %s have?: ", name);
		health = in.nextInt();

		System.out.printf("How many battles has %s won?: ", name);
		numBattles = in.nextInt();

		System.out.printf("How old is %s?: ", name);
		age = in.nextInt();

		System.out.printf("How much gold has %s procured?: ", name);
		gold = in.nextInt();

		
		// ////////////////////////////////////////
		// ///////////// Output ///////////////////
		// ////////////////////////////////////////

		avgGpB = gold / numBattles;

		System.out.printf("\nSir Jav-a-lot's Attributes:\n");
		System.out.printf("Name: %s\n", name);
		System.out.printf("Age: %d \tHealth: %d\n", age, health);
	
		
		// 1st Method 
		//System.out.printf("Gold: %d\tBattles: %d\n", (int)gold, (int)numBattles);

		// 2nd Method 
		// I chose this method because I believe casting a float to an int 
		// is more expensive of an operation than limiting decimal output 
		// (Albeit negligible difference in a program like this)
		System.out.printf("Gold: %.0f\tBattles: %.0f\n", gold, numBattles);
		
		
		System.out.printf("Average Gold per Battle: %.3f\n", avgGpB);
		
		//System.out.printf("Average Gold per Battle: %f\n", avgGpB);
		// This works but I personally do not like showing unnecessary decimal places
		
		/* I am aware that the %.2f syntax wasn't used in Chapter 1 or 2 
		 * However, if I were to use int for gold/numBattles, then division does
		 * Integer division and truncates decimal in a way I do not like
		 * and I do not personally like unnecessary casts or unnecessary decimal places
		 * I did include commented out code alternatives that better fits to Chapter 2's style.
		 */
		
		
		
		// System.out.printf("\nYou are now leaving Camelot\n");

	}

}
