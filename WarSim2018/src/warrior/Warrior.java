package warrior;
import java.util.Random;

import armour.Armour;

abstract public class Warrior 
{
	protected int damageFactor = 0;
	protected String phealthBar = "||||||||||||||||||||";
	protected String ehealthBar = "||||||||||||||||||||";
	protected float health;
	protected float strength;
	protected float dodgeProb; //random number to determine if a player can dodge an attack
	protected int[] dexMod;
	protected int[] intQMod;
	protected float dexterity;
	protected float intQuotient;
	protected String warriorType;
	protected Random randNum = new Random();
	private float strikeAmount;
	
	public Warrior(int[] healthMod, int[] strengthMod, int[] dexMod, int[] intQ, String type)
	{
		this.health = randNum.nextInt((healthMod[0] - healthMod[1]) + 1) + healthMod[1];
		this.strength = randNum.nextInt((strengthMod[0] - strengthMod[1]) + 1) + strengthMod[1];
		this.dexterity = randNum.nextInt((dexMod[0] - dexMod[1]) + 1) + dexMod[1];
		this.intQuotient = randNum.nextInt((intQ[0] - intQ[1]) + 1) + intQ[1];
		this.dexMod = dexMod;
		this.intQMod = intQ;
		this.warriorType = type;
	}
	
	public float[] GetWarriorDetails()
	{
		float[] playerDetails = new float[] {this.health, this.strength, this.dexterity, this.intQuotient};
		return playerDetails;
	}
	
	public float GetHealth()
	{
		return this.health;
	}

	
	public String GetPlayerHbar()
	{
		return phealthBar;
	}
	
	public String GetEnemyHbar()
	{
		return ehealthBar;
	}
	
	public float GetStrength()
	{
		return this.strength;
	}
	public String GetType()
	{
		return this.warriorType;
	}

	public float GetDexterity() 
	{
		return this.dexterity;
	}
	
	public float Strike(float playerOneStrength, float playerTwoStrength, float pStrength, 
			int weaponEffect, float pHealth, float playerOneHealth, int attackType)
	{
		float attackTypeEffect = 1.0f;
		float strikeLevel = 0.0f;
		
		if(attackType == 1)
		{
			attackTypeEffect = 0.5f;
		}
			
		else
		{
			attackTypeEffect = 1.0f;
		}
		
		float fsr = playerOneStrength / playerTwoStrength;// fighters' strength ratio
		float sr = playerOneStrength / pStrength; // strength ratio
		float hr = playerOneHealth / pHealth; // health ratio
		//healthRatio = hr;
		
		if(fsr >= 1)
		{
			strikeLevel = attackTypeEffect * (sr + hr) * fsr * weaponEffect;
			this.strength -= strikeLevel*playerOneStrength/playerOneHealth;
			// this ensure we are proportionally decrementing strength
		}
		else
		{
			strikeLevel = attackTypeEffect * (sr + hr) * weaponEffect;
			this.strength -= strikeLevel*playerOneStrength/playerOneHealth;
			// this ensure we are proportionally decrementing strength
		}
		
		return strikeLevel;
	}
	
	public int SetDamage(float strikeAmount, Warrior attacker, float damageAmount, 
				float defHealth, String fighter)
	{
		float healthRatio = this.GetHealth()/defHealth;
		int dodgeProbSeed = attacker.dexMod[0] + attacker.intQMod[0] + this.intQMod[0] + 
							this.dexMod[0];
		dodgeProb = randNum.nextInt(dodgeProbSeed) + 1;//probability that a player can dodge an attack
		float dodgeFactor = ((float)this.dexterity + this.intQuotient) * healthRatio;
		// ability to dodge an attack
		
		if(dodgeFactor >= dodgeProb)
		{
			System.out.printf("%s attack missed!!! \n", fighter);
			this.strikeAmount = 0;
			
			return 0;
		}
		else
		{
			if(fighter == "Enemy")
			{
				System.out.println("Block attack?");
				
				return 1;
			}
			else
			{
				return 1;
			}
		}
	}

	public float GetDamage() 
	{
		return this.strikeAmount;
	}

	public void BlockAttack(float strikeAmount, Armour armour, float armourDef, String fighter) 
	{
		System.out.printf("%s attack blocked!!! \n", fighter);
		
		this.health -= strikeAmount * 0.5f;//block reduces attack by 50%
		this.strikeAmount = strikeAmount * 0.5f;
		//a blocked attack does not affect the armour
	}

	public void DeductHealth(float strikeAmount, Armour armour, float armourDef, String fighter) 
	{
		this.health -= strikeAmount;
		this.strikeAmount = strikeAmount;
		
		float armourReduction = armourDef * armour.GetArmourDef( ) / 
				armour.GetArmourStrength(); // armour reduction value
		armour.SetArmourStrength(armour.GetArmourStrength() - armourDef);//sets new armour strength after a hit
		armour.SetArmourDef(armour.GetArmourDef() - armourReduction);//sets a new armourdef after a hit
	}

	public void SetDetails(float[] warriorDetails) 
	{
		this.health = warriorDetails[0];
		this.strength = warriorDetails[1];
		this.dexterity = warriorDetails[2];
		this.intQuotient = warriorDetails[3];
	}
}
