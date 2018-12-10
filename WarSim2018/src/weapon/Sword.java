package weapon;

public class Sword extends Weapon
{
	//private static int minDamage = 20;
	//private static int maxDamage = 55;
	private static int weaponEffectVal = 3;

	public Sword()
	{
		super(weaponEffectVal);
	}
	
	/*public int GetWeaponEffectVal()
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
