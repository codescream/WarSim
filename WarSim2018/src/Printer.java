import warrior.Warrior;
//import weapon.Weapon;

public class Printer 
{
	public String[] warriorTypes = 
	{
		"Human", "Elf", "Orc"
	};
	
	public String[] weaponTypes = 
	{
		"Sword", "Hammer", "Axe"
	};
	
	public String[] armourTypes = 
	{
			"Leather", "Chain", "Plate"
	};
	
	public Printer()
	{}
	
	public void Welcome()
	{
		System.out.println("Welcome to WarSim2018 beta!!!!");
	}

	// prints available warrior types
	public void Warriors() 
	{
		for(int i = 1; i <= warriorTypes.length; i++)
		{
			System.out.println(i + ") " + warriorTypes[i - 1]);
		}
	}
	
	public void Weapons() 
	{
		for(int i = 1; i <= weaponTypes.length; i++)
		{
			System.out.println(i + ") " + weaponTypes[i - 1]);
		}
	}
	
	public void Armours() 
	{
		for(int i = 1; i <= armourTypes.length; i++)
		{
			System.out.println(i + ") " + armourTypes[i - 1]);
		}
	}
	

	public void PrintStats(Warrior player, String warrior, String weapon) 
	{
		System.out.println("  ///////////////////////////////////////////////////////////////"
				+ "/////////////////////////////////////////////////");
		System.out.printf(" ///////    Your %s is a %s and has %f health and %f strength, "
				+ "wielding a(n) %s"
				+ "  //////\n", warrior, 
				player.GetType(), player.GetHealth(), player.GetStrength(), weapon);
		System.out.println("///////////////////////////////////////////////////////////"
				+ "//////////////////////////////////////////////////////\n");
	}

	public void Attacks() 
	{
		System.out.println("1) Standard"); 
		System.out.println("2) Heavy Hit"); 
	}

}