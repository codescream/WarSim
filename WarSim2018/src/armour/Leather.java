package armour;

public class Leather extends Armour
{
	private static int armourDef = 60;
	private static int dexterityCost = 2;
	private static int armourStrength = 30; // the max hit before armour is destroyed
	private static String name = "Leather";
	public Leather() 
	{
		super(name);
		super.SetArmourDef(armourDef);
		super.SetDexterityCost(dexterityCost);
		super.SetArmourStrength(armourStrength);
	}

}
