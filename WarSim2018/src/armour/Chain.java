package armour;

public class Chain extends Armour
{
	private static int armourDef = 80;
	private static int dexterityCost = 5;
	private static int armourStrength = 50; // the max hit before armour is destroyed
	private static String name = "Chainmail";

	public Chain() 
	{
		super(name);
		super.SetArmourDef(armourDef);
		super.SetDexterityCost(dexterityCost);
		super.SetArmourStrength(armourStrength);
	}

}
