package entity;
import java.util.Random;

import weapon.BattleAxe;
import weapon.LongSword;
import weapon.Spear;
import weapon.WarHammer;

import armor.Leather;
import armor.MagicalCloth;
import armor.Plate;


public class Knight extends Entity{
	
	private Random r = new Random();
	
	// Although its randomized in KnightDriver, I thought it was wise to still set it 
	// redundantly random here, in case in future programs, someone forgot to select or randomize health.
	private int health = r.nextInt(250) + 250; 
	
	
	private Weapon myWeapon;
	private Armor myArmor;
	
	//45 random names
	private static String nameList[] = { "Adam Jones", "Adam Gorman", "Alexander Muller", "Anthony Carson", "Bailey Frazier",
			"Brent Gann", "Bugra Akdogan", "Carl Averion", "Chad Engler", "Christian Smith", "Christopher Bannon",
			"Daniel Schleuger", "Daniel Weston", "Daniel Waseem", "David Poarch", "Edward Januska",
			"Francesco Martone", "Gustavo Maturana", "Hiram Raulerson", "Jennifer Starling", "Jonathan Tucker",
			"Kacey Davenport", "Karen Hall", "Kassandra Coan", "Katrina Campbell", "Keith Achorn", "Kevin Blease",
			"Kristina Hancock", "Lorenzo King", "Manuel Montes De oca", "Matthew Monat", "Michael Enriquez",
			"Patrick Wright", "Randy Simes", "Richard Hall", "Richard Gerard", "Rodolfo De Los santos",
			"Sarah Scheibe", "Shireen Taha", "Stephanie Ramseier", "Stephen O'Donnell", "Stephen Dagley",
			"Taylor Hanson", "Triesha Fagan", "Yulia Jenner" };
	// Do the names look familiar?  :) 
	
	
	public Knight() // Random Knight
	{
		name = nameList[r.nextInt(45)];
		setWeapon(r.nextInt(4)+1); // 1 to 4
		setArmor(r.nextInt(3)); // 0 to 3
	}
	
	public Knight(String n, int w)
	{
		name = n;
		setWeapon(w);
		setArmor(r.nextInt(3)); // I decided to not give user choice in armor, but always assign randomly.
	}
	
	// Set variables
	
	public void setHealth(int h)
	{
		health = h;
	}
		
	public int getHealth()
	{
		return health;
	}
	
	// private String WeaponList[] = { "Long Sword", "Battle Axe", "Spear", "Warhammer" };
	public void setWeapon(int id)
	{
		switch (id)
		{
			case 1:
				myWeapon = new LongSword();
				break;
			case 2:
				myWeapon = new BattleAxe();
				break;
			case 3:
				myWeapon = new Spear();
				break;
			case 4:
				myWeapon = new WarHammer();
				break;
			default:
				setWeapon(r.nextInt(4)+1); // {1, 2, 3, 4} 
				break;
		}
	}
	
	public Weapon getWeapon()
	{
		return myWeapon;
	}
	
	public void setArmor(int id){
		switch (id)
		{
			case 0: 
				myArmor = new Leather();
				break;
			case 1: 
				myArmor = new MagicalCloth();
				break;
			case 2: 
				myArmor = new Plate();
				break;
			default:
				setWeapon(r.nextInt(3)); // {0, 1, 2}
				break;
		}
	}
	
	public Armor getArmor(){
		return myArmor;
	}
	
	public void hurt(int ouch, Weapon weapon)
	{
		health = health - ouch;
		
		if(health < 0)
			health = 0;
	}
	
	public int fight()
	{
		return myWeapon.attack();
	}
	
	public String toString()
	{
		String tempString = "Knight Name: " + getName() + ", " 
		+ "Health: " + getHealth() + ", " 
		+ "Weapon: " + getWeapon().getName() + ", "
		+ "Armor: " + getArmor().getName();
		
		return tempString;
	}
	
}
