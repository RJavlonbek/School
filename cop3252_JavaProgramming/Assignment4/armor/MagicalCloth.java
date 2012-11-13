package armor;

import weapon.BattleAxe;
import weapon.LongSword;
import weapon.Spear;
import weapon.WarHammer;
import entity.Armor;
import entity.Weapon;

public class MagicalCloth extends Armor {
	
	public MagicalCloth()
	{
		name = getRandomPrefix() + " " + "Magical Cloth Armor";
	}
	
	public int reduceDamage(int damage, Weapon weapon)
	{
		
		// Rounding will occur and possibility that no change will take place
		int modifiedDamage = 0;
		
		if (weapon instanceof Spear)
		{
			modifiedDamage = (int) Math.round(damage * 0.25);
		} else if (weapon instanceof LongSword)
		{
			modifiedDamage = (int) Math.round(damage * 0.5);
		} else if (weapon instanceof WarHammer)
		{
			modifiedDamage = (int) Math.round(damage * 2.5);
		} else if (weapon instanceof BattleAxe)
		{
			modifiedDamage = (int) Math.round(damage * 0.75);
		} else
		{
			modifiedDamage = damage; // no change
		}
		
		return modifiedDamage;
		
	}
	
}
