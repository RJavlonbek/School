package armor;

import weapon.BattleAxe;
import weapon.LongSword;
import weapon.Spear;
import weapon.WarHammer;
import entity.Armor;
import entity.Weapon;

public class Plate extends Armor {
	
	public Plate()
	{
		name = getRandomPrefix() + " " + "Plate Armor";
	}
	
	public int reduceDamage(int damage, Weapon weapon)
	{
		// Rounding will occur and possibility that no change will take place
		int modifiedDamage = 0;
		
		if (weapon instanceof Spear)
		{
			modifiedDamage = (int) Math.round(damage * .75);
		} else if (weapon instanceof LongSword)
		{
			modifiedDamage = (int) Math.round(damage * .50);
		} else if (weapon instanceof WarHammer)
		{
			modifiedDamage = (int) Math.round(damage * 1.25);
		} else if (weapon instanceof BattleAxe)
		{
			modifiedDamage = (int) Math.round(damage * 1);
		} else
		{
			modifiedDamage = damage; // no change
		}
		
		return modifiedDamage;
		
	}
	
}
