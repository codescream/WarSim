package warrior;

public class Orc extends Warrior
{
	private static int[] healthMod = {350, 200};
	private static int[] strengthMod = {450, 200};
	private static int[] intQMod = {2, 0};
	private static int[] dexMod = {1, 0};
	//private static int[] scale = {75, 85, 0};
	private static String type = "Orc";

	public Orc() 
	{
		super(healthMod, strengthMod, dexMod, intQMod, type);
	}
}
