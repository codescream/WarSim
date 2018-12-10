package weapon;

import java.util.Random;

abstract public class Weapon 
{
	protected Random randNum = new Random();
	int weaponEffectVal;
	//protected int minDamage;
	//protected int maxDamage;
	public Weapon(int weaponEffectVal)
	{
		this.weaponEffectVal = weaponEffectVal;
	}
	
	public int weaponEffectVal()
	{
		return weaponEffectVal;
	}
	//abstract public int Strike(int attackType, int minDamage, int maxDamage, int dexterity, int srength);
}
