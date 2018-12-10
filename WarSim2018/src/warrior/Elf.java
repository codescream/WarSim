package warrior;

public class Elf extends Warrior
{
	private static int[] healthMod = {300, 150};
	private static int[] strengthMod = {150, 50};
	private static int[] intQMod = {7, 2};
	private static int[] dexMod = {15, 6};
	//private static int[] scale = {10, 5, 20};
	private static String type = "Elf";

	public Elf() 
	{
		super(healthMod, strengthMod, dexMod, intQMod, type);
	}
}
