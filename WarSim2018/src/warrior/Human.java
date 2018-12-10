package warrior;

public class Human extends Warrior
{
	private static int[] healthMod = {200, 100}; // max and min values respectively
	private static int[] strengthMod = {250, 150};
	private static int[] intQMod = {10, 5};
	private static int[] dexMod = {8, 3};
	//private static float fsr; // fighters' strength ratio
	//private static int[] scale = {0, 40, 10};
	private static String type = "Human";

	public Human() 
	{
		super(healthMod, strengthMod, dexMod, intQMod, type);
	}
}
