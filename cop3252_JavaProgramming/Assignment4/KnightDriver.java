// Adam Gorman
// Java Online
// argorman@admin.fsu.edu


import java.util.Random;
import java.util.Scanner;

import entity.Knight;

public class KnightDriver {
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		
		Knight myKnight;
		Knight enemyKnight;
		boolean playing = true;
		
		while (playing)
		{
			
			System.out.println("\nWelcome to Knight Fight!");
			
			// /////////////////////////////////////////////////////
			// Create our Knight
			// 
			System.out.print("What is your Knight's name?: ");
			String myKnightName = in.nextLine();
			
			System.out.println("Now select your weapon! (Choose number)");
			System.out.println("0) Randomly Select");
			System.out.println("1) Long Sword");
			System.out.println("2) Battle Axe");
			System.out.println("3) Spear");
			System.out.println("4) Warhammer");
			System.out.print("Your choice my liege?: ");
			
			int weaponChoice = in.nextInt();
			in.nextLine();
			myKnight = new Knight(myKnightName, weaponChoice);
			
			System.out.println("Your Knight's randomly selected armor this battle is " + myKnight.getArmor().getName());
			//
			// End creating our Knight
			// /////////////////////////////////////////////////////
			
			// /////////////////////////////////////////////////////
			// Create Enemy Knight
			//
			String opponentChoice = "n";
			System.out.print("Would you like to auto generate your opponent? (y|n):  ");
			
			opponentChoice = in.nextLine();
			
			if (opponentChoice.compareTo("y") == 0)
			{
				System.out.println("\nAuto Generating Knight...");
				enemyKnight = new Knight(); // Creating a new knight, randomizes
											// everything
			} else
			{
				
				System.out.print("What is your enemy Knight's name?: ");
				String enemyKnightName = in.nextLine();
				
				System.out.println("Now select your enemy's weapon! (Choose number)");
				
				// Does it matter which way of handling print statements is
				// used? print, printf, println.
				// I know consistency is important, but I am intentionally not
				// being consistent for sake of asking this and other questions.
				System.out.printf("0) Randomly Select" + "\n" 
						+ "1) Long Sword" + "\n" 
						+ "2) Battle Axe" + "\n"
						+ "3) Spear" + "\n" 
						+ "4) Warhammer" + "\n");
				System.out.println("Your choice my liege?: ");
				int enemyWeaponChoice = in.nextInt();
				in.nextLine();
				enemyKnight = new Knight(enemyKnightName, enemyWeaponChoice);
			}
			System.out.println("Your enemy Knight's randomly selected armor this battle is "
					+ enemyKnight.getArmor().getName());
			//
			// End Create EnemyKnight
			// /////////////////////////////////////////////////////
			
			
			// Randomize health from 250 to 500
			enemyKnight.setHealth(r.nextInt(251) + 250);
			myKnight.setHealth(r.nextInt(251) + 250);
			
			// /////////////////////////////////////////////////////
			// Begin the Battle!
			//
			System.out.println("\"" + myKnight.getName() + "\" Versus \"" + enemyKnight.getName() + "\"");
			
			System.out.print("Would you like to display the battle output?(y|n): ");
			String watch = in.nextLine();
			
			System.out.print("Would you like to begin the battle?(y|n): ");
			String ready = in.nextLine();
			
			if (ready.compareTo("y") == 0)
			{
				int round = 0;
				int dmg = 0;
				
				System.out.println("Starting Healths: " 
						+ myKnight.getName() + " " + myKnight.getHealth() + ",  "
						+ enemyKnight.getName() + " " + enemyKnight.getHealth());
				
				System.out.println("\nFIGHT!");
				
				// 1 attack per round
				while (myKnight.getHealth() > 0 && enemyKnight.getHealth() > 0)
				{ // Fight until one reaches 0hp or less
				
					round++;
					if (round % 2 == 0)
					{
						dmg = enemyKnight.fight(); // This returns weapon damage
						myKnight.hurt(dmg, enemyKnight.getWeapon());
					} else
					{
						dmg = myKnight.fight();
						enemyKnight.hurt(dmg, myKnight.getWeapon());
					}
					
					if (watch.compareTo("y") == 0) // If user wants to watch the health drop!
					{
						if (round % 2 == 0)
						{
							System.out.println("R: " +  + round + "\t" + enemyKnight.getName() + " hit " + myKnight.getName() + " for "+dmg+ " points of damage and now has " + myKnight.getHealth() + " health left" );
						}else{
							System.out.println("R: " +  + round + "\t" + myKnight.getName() + " hit " + enemyKnight.getName() + " for "+dmg+ " points of damage and now has " + enemyKnight.getHealth() + " health left" );
						}
						
					}
					
					
				}
				
				System.out.println();
				if (myKnight.getHealth() <= 0)
				{
					System.out.println(enemyKnight.getName() + " WON after " + round + " rounds!");
					System.out.println(myKnight.getName() + " died! :(");
				} else if (enemyKnight.getHealth() <= 0)
				{
					System.out.println(myKnight.getName() + " WON after " + round + " rounds!");
					System.out.println(enemyKnight.getName() + " died!");
				}
				// else? no one died somehow!
				
				// 
				// The Battle has ended
				// /////////////////////////////////////////////////////
				
				
				// Final Stats!
				System.out.println("\nFinal Knight Stats:");
				System.out.println(myKnight.toString());
				System.out.println(enemyKnight.toString());
				
				// Next Game?
				System.out.print("Would you like to play again?(y|n): ");
				ready = in.nextLine();
				
				if (ready.compareTo("y") == 0)
				{
					playing = true;
				} else
				{
					playing = false;
				}
				
			} else
			{
				playing = false; // exit while loop
			}
		} // end main while loop
		
		System.out.println("\nExiting Game");
		
	} // end main
} // end KnightDriver


