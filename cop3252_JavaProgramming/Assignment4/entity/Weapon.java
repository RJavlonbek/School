package entity;

import java.util.Random;

public class Weapon extends Entity {
	
	protected int maxDamage;
	protected int minDamage;
	
	private Random r = new Random();
	

	public int attack()
	{
		return r.nextInt(maxDamage - minDamage) + minDamage;
	}
	
	public void setWeaponDamage(int mind, int maxd)
	{
		minDamage = mind;
		maxDamage = maxd;
	}
	
	public void setMinDamage(int mind)
	{
		minDamage = mind;
	}
	
	public void setMaxDamage(int maxd)
	{
		maxDamage = maxd;
	}
	
	public int getMinDamage()
	{
		return minDamage;
	}
	
	public int getMaxDamage()
	{
		return maxDamage;
	}
	
}
