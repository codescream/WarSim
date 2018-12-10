package armour;

public class Plate extends Armour
{
	private static int armourDef = 70;
	private static int dexterityCost = 3;
	private static int armourStrength = 40; // the max hit before armour is destroyed
	private static String name = "Platemail";
	
	public Plate() 
	{
		super(name);
		super.SetArmourDef(armourDef);
		super.SetDexterityCost(dexterityCost);
		super.SetArmourStrength(armourStrength);
	}
}
