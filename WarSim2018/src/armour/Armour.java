package armour;
import java.util.Random;

abstract public class Armour 
{
	protected float armourDefence;
	protected int dexterityCost;
	protected String armourName;
	protected float armourStrength;
	protected int count = 0;
	protected Random randNum =  new Random();
	
	public Armour(String armourName)
	{
		this.armourDefence = armourDefence - randNum.nextInt(5);
		this.dexterityCost = dexterityCost - randNum.nextInt(2);
		this.armourName = armourName;
	}
	
	public float GetArmourDef()
	{
		return armourDefence;
	}
	
	public int GetDexterityCost()
	{
		return dexterityCost;
	}
	
	public String GetArmourName()
	{
		return armourName;
	}
	
	public void SetArmourDef(float armourDefence)
	{
		this.armourDefence = armourDefence; //factoring in armour imperfections
	}
	
	public void SetDexterityCost(int dexterityCost)
	{
		this.dexterityCost = dexterityCost - randNum.nextInt(2);
	}
	
	public void SetArmourName(String armourName)
	{
		this.armourName = armourName;
	}

	public void SetArmourStrength(float armourStrength) 
	{
		if(armourStrength <= 0)
		{
			if(count < 1)
			{
				System.out.println("Armour Destroyed!!!!");
				count++;
			}
		}
			
		else
			this.armourStrength = armourStrength;
	}
	
	public float GetArmourStrength() 
	{
		if(this.armourStrength <= 0)
			return 0;
		else
			return this.armourStrength;
	}
}