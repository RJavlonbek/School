import javax.swing.JOptionPane;

public class Knight {

	private String name;
	private int health;
	private int numBattles;
	private int age;
	private double gold;
	private String starrySky;
	
	public Knight() {
		// Setting some default values, We shouldn't ever see these, but incase someone tries to 
		// Print the data or use it, prior to it being set we have some place holder variables
		// I am aware, unlike C++, that java compiler should be setting all values to 0 and ""
		// but coming from C++ and having null set to things, I prefer manually setting things
		
		// I prefer setting it in the constructor and not in the original declaration
		// Because what if I extend my Knight into another class Champion, or change this class
		// into an interface or something that doesn't permit variables being set directly at declaration
		
		name = "Bob";
		health = 10;
		numBattles = 0;
		age = 18;
		gold = 0;
		starrySky = ""; //Its a cloudy night
	}
	
	// Although this construction is unused. I added it to satisfy Acceptance Criteria 3
	// 3. We need the ability to enter the Knight’s attributes (name, health, number of battles,
	// age, and amount of gold procured from pillaging) using the constructor from class Knight.
	// We have the ability to use class Knight constructor, but I chose to not use it. In favor of another approach.
	public Knight(String n, int h, int numB, int a, double g) {
		name = n;
		health = h;
		numBattles = numB;
		age = a;
		gold = g;
		starrySky = "";
	}
	
	
	// get input from user to set all the variables
	// I could have broken these up even further, the same way that I did with with PrintConsoleOutput
	// but I chose to condense this all into 1. 
	
	public void getUserInput(){
		// The first variable is the statement
		// the second variable is the default input to be changed by user
		
		setName(JOptionPane.showInputDialog(
				"Enter your Knight's name", "Sir Jav-a-lot"));

		setHealth(Integer.parseInt(JOptionPane.showInputDialog(
				"How much health does " + name + " have?", "12")));

		setNumBattles(Integer.parseInt(JOptionPane.showInputDialog(
				"How many battles has " + name + " won?", "6")));

		setAge(Integer.parseInt(JOptionPane.showInputDialog(
				"How old is " + name + "?", "25")));

		setGold(Double.parseDouble(JOptionPane.showInputDialog(
				"How much gold has " + name + " pillaged?", "1123.58")));
	}
	
	//Set variables
	public void setName(String n){
		name = n;
	}

	public void setHealth(int h){
		health = h;
	}
	
	public void setNumBattles(int num){
		numBattles = num;
	}
	
	public void setAge(int a){
		age = a;
	}
	
	public void setGold(double g){
		gold = g;
	}
	
	public void setSky(String s){
		starrySky = s;
	}
	
	
	//Pop up dialog output
	public void PopupOutput(){
		
		String tempString;
		tempString= "Knight Name: " + getName() + "\n"
		+ "Knight Health: " + getHealth() + "\n"
		+ "Knight Battles: " + getNumBattles() + "\n"
		+ "Knight Age: " + getAge() + "\n"
		+ "Knight Gold: $" + getGold() + "\n" 
		+ getStarrySky();
		
		JOptionPane.showMessageDialog(null, tempString);
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public int getHealth()
	{
		return health;
	}

	public int getNumBattles()
	{
		return numBattles;
	}

	public int getAge()
	{
		return age;
	}

	public double getGold()
	{
		return gold;
	}
	
	public String getStarrySky()
	{
		return starrySky;
	}

	//output all Knights Information
	public void PrintConsoleOutput(){
		printConsoleName();
		printConsoleHealth();
		printConsoleBattles();
		printConsoleAge();
		printConsoleGold();
	}
	
	// I don't know why, but I far prefer print or println and using + variables instead of printf and %d approach
	// I just find it easier to read this way
	
	// output individual knight's information
	public void printConsoleName(){
		System.out.println("Knight Name: " + name);
	}
	public void printConsoleHealth(){
		System.out.println("Knight Health: " + health);
	}
	public void printConsoleBattles(){
		System.out.println("Knight Battles: " + numBattles);
	}
	public void printConsoleAge(){
		System.out.println("Knight Age: " + age);
	}
	public void printConsoleGold(){
		System.out.println("Knight Gold: $" + gold);
	}
}
