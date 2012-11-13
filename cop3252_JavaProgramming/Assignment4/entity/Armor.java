package entity;

import java.util.Random;

public class Armor extends Entity{
	
	//Nothing special occurs in Armor
	//Simply makes it easier to encapsulate all armor into a specific class 
	// Is this called a superclass? 
	public Armor(){	
		//empty
	}
	
	//I decided to make it do a little something
	private Random r = new Random();

	private static String armorPrefix[] = {"Cruel", "Divine", "Dragon's", "Captain's", "Matt's", "Richard's", 
		"Monk's", "Massive"	,"Enchanted", "Enchanced", "Soldier's", "Strong", "Sturdy", "Knight's"};
	                               
	
	public String getRandomPrefix(){
		
		String prefix = armorPrefix[r.nextInt(14)];
	
		return prefix;
		
	}
	
}
