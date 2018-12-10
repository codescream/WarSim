package weapon;

public class Hammer extends Weapon
{
	//private static int minDamage = 35;
	//private static int maxDamage = 75;
	private static int weaponEffectVal = 5;
	
	public Hammer()
	{
		super(weaponEffectVal);
	}
	
/*	public int GetWeaponEffectVal()
	{
		return weaponEffectVal;
	}*/
	
	/*@Override
	public int Strike(int attackType, int minDamage, int maxDamage, int dexterity, int strength) 
	{
		int damageAmount = 0;
		int hitChance = 50; // in percent
		int roll = this.randNum.nextInt(100) + 1;
		roll += dexterity;
		
		if(attackType == 1) // standard
		{
			if(roll >= hitChance) // attack hits
			{
				damageAmount = randNum.nextInt(maxDamage) + minDamage + strength;
			}
		}
		else // heavy
		{
			if(roll >= hitChance + 25) // attack hits
			{
				damageAmount = randNum.nextInt(maxDamage) + minDamage + strength + 50;
			}
		}
		return damageAmount;
	}*/
}
