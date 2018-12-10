
//import java.io.IOException;
import java.util.Random;
import weapon.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;
import java.io.*;
import java.util.concurrent.Callable;
import armour.*;
import warrior.*;

public class WarSim2018Main 
{
	private static Scanner input = new Scanner(System.in);
	private static Random randNum = new Random();
	private static Printer print = new Printer();
	private static Warrior player;
	private static Weapon pWeapon;
	private static Warrior enemy;
	private static Weapon eWeapon;
	private static Armour pArmour;
	private static Armour eArmour;
	private static boolean gameOver;
	private static boolean playerTurn;
	private static float pDamageAmount;
	private static int blockChoice = 0;
	private static int enemyWinCount = 0;
	private static int playerWinCount = 0;
	
	public static void main(String[] args) throws InterruptedException 
	{
		print.Welcome();
		print.Warriors();
		int choice = input.nextInt();
		
		// create player //
		CreateWarrior(choice, "Player");
		
		float pHealth = player.GetHealth();
		float pStrength = player.GetStrength();
		
		print.Weapons();
		choice = input.nextInt();
		CreateWeapon(choice, "Player");
		
		// player armour
		print.Armours();
		choice = input.nextInt();
		CreateArmour(choice, "Player");
		
			
		// create enemy //
		CreateWarrior(randNum.nextInt(3) + 1, "Enemy"); // enemy creation
		CreateWeapon(randNum.nextInt(3) + 1, "Enemy"); // enemy weapon
		CreateArmour(randNum.nextInt(3) + 1, "Enemy"); //enemy armour	
		
		float eHealth = enemy.GetHealth();
		float eStrength = enemy.GetStrength();
		
		print.PrintStats(player, "Player", CreateWeapon(choice, "Player"));
		print.PrintStats(enemy, "Enemy", CreateWeapon(randNum.nextInt(3) + 1, "Enemy"));
		
		float[] enemyDetails = enemy.GetWarriorDetails();
		float[] playerDetails = player.GetWarriorDetails();		
		
		System.out.println("\n\n\n\n");
		
		int round = 0;
		while(round <= 3 && (playerWinCount < 2 && enemyWinCount < 2))
		{
			round++;
			gameOver = false;
			if(round > 1)
			{
				player.SetDetails(playerDetails);
				enemy.SetDetails(enemyDetails);
			}
			System.out.print("Round ");
				Delay();
			System.out.print(round);
				Delay();
			System.out.print(" Fight!!!!\n\n\n\n");
				Delay();
					
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
			
			int pRemBarsFin = 0, eRemBarsFin = 0;
			String players = "Player";
			String enemys = "Enemy";
			String spaces = "        ";
			String phealthBar =  player.GetPlayerHbar();
			String ehealthBar =  enemy.GetEnemyHbar();
			int phealthBarLength = phealthBar.length();
			int ehealthBarLength = ehealthBar.length();
			float pBarAmount = pHealth/phealthBar.length();
			float eBarAmount = eHealth/ehealthBar.length();
			float playerPrevHealth = player.GetHealth();
			float enemyPrevHealth = enemy.GetHealth();
			float pArmourDef = 0;
			float eArmourDef = 0;
			int turnCounter = 0;
			playerTurn = true;
			
			System.out.printf("%s                      %s\n", players, enemys);
			System.out.printf("%s        %s\n", phealthBar, ehealthBar);
			System.out.println("\n\n\n\n\n");
			
			float damageAmount = 0.0000000f;
			while(!gameOver)
			{
				blockChoice = 0;
				damageAmount = 0.0000000f;
				if(playerTurn) //player's turn
				{
					turnCounter++;
					System.out.println("Choose attack");
					print.Attacks();
					System.out.println("\n\n\n\n");
					choice = input.nextInt();
					
					damageAmount = player.Strike(player.GetStrength(), enemy.GetStrength(), pStrength,
							pWeapon.weaponEffectVal(), pHealth, player.GetHealth(), choice);
					
					if(damageAmount > 0) // reducing health
					{	
						eArmourDef = (eArmour.GetArmourDef()*damageAmount/100);
						
						if(enemy.SetDamage((damageAmount - eArmourDef), player, damageAmount, eHealth,"Player") == 1)
						{	
							choice = randNum.nextInt(2) + 1;//computer block option is randomly selected
							
							if(choice == 1)
							{
								enemy.BlockAttack((damageAmount - eArmourDef), eArmour, eArmourDef, "Player");
								turnCounter++;
								playerTurn = false;
							}
							else if(choice == 2)
							{
								enemy.DeductHealth((damageAmount - eArmourDef), eArmour, eArmourDef, "Player");
							}
						}
						
						//block of code to reduce health bar
						if(enemy.GetDamage() > 0)
						{
							float eRemBarsInit = (enemyPrevHealth - (damageAmount - eArmourDef)) / eBarAmount;
							if((eRemBarsInit % 1) > 0)
							{
								eRemBarsFin = (int)eRemBarsInit + 1;
							}
							enemyPrevHealth = enemy.GetHealth(); // stores previous health of fighter
						}
						else
						{
							// do nothing
						}
					}
					
					//check if enemy is dead
					if(enemy.GetHealth() <= 0)
					{
						gameOver = true;
						System.out.println("Game over!!!!!");
						System.out.printf("Player Wins Round %d!\n", round);
						System.out.println("\n\n\n\n\n");
						playerWinCount++;
						
						break;
					}
					
					playerTurn = !playerTurn;
				}
				else
				{
					//enemy's turn
					turnCounter++;
					choice = randNum.nextInt(2) + 1; // attack type
					damageAmount = enemy.Strike(enemy.GetStrength(), player.GetStrength(), eStrength,
							eWeapon.weaponEffectVal(), eHealth, enemy.GetHealth(), choice);
					
					if(damageAmount > 0) // reducing health
					{
						pArmourDef = (pArmour.GetArmourDef()*damageAmount/100);
						
						if(player.SetDamage((damageAmount - pArmourDef), enemy, damageAmount, pHealth, "Enemy") == 1)
						{
							System.out.println("1) Block attack *Turn will be missed*\n2) Allow attack");
							
							//this allows player a 2secs window to choose to block otherwise attack impacts
							ConsoleInput con = new ConsoleInput(1, 1500, TimeUnit.MILLISECONDS);
							
							blockChoice = con.readLine();
							
							if(blockChoice == 1)
							{
								player.BlockAttack((damageAmount - pArmourDef), pArmour, pArmourDef, "Enemy");
								//turnCounter++;
								playerTurn = true;
							}
							else if(blockChoice == 2)
							{
								player.DeductHealth((damageAmount - pArmourDef), pArmour, pArmourDef, "Enemy");
							}
						}
						
						//block of code to reduce health bar
						if(player.GetDamage() > 0)
						{
							float pRemBarsInit = (playerPrevHealth - (damageAmount - pArmourDef)) / pBarAmount;
							pDamageAmount = damageAmount;
							
							if((pRemBarsInit % 1) > 0)
							{
								pRemBarsFin = (int)pRemBarsInit + 1;
							}
							
							playerPrevHealth = player.GetHealth(); // stores previous health of fighter
						}
						else
						{
							// do nothing
						}
					}
					
					//check if enemy is dead
					if(player.GetHealth() <= 0)
					{
						gameOver = true;
						System.out.println("Game over!!!!!");
						System.out.printf("Computer Wins Round %d!\n", round);
						System.out.println("\n\n\n\n\n");
						enemyWinCount++;
						
						break;
					}
					
					playerTurn = !playerTurn;
				}
				
				if(turnCounter == 2)
				{
					if(pRemBarsFin > 0)
					{
						phealthBar = phealthBar.substring(0, phealthBarLength - 
									(phealthBarLength - pRemBarsFin));
						
						pArmourDef = pArmourDef + 0;
						pDamageAmount = pDamageAmount + 0;
						
						int addSpaces = phealthBarLength - pRemBarsFin;
						
						phealthBarLength = phealthBar.length();
						
						String spaceToAdd = "";
						
						for(int i = 0; i < addSpaces; i++)
						{
							spaceToAdd += " ";
						}
						
						spaces += spaceToAdd;
					}
					
					if(phealthBar == "" && player.GetHealth() > 0)
					{
						phealthBar = "|";
					}
					
					if(eRemBarsFin > 0)
						ehealthBar = ehealthBar.substring(0, ehealthBarLength - 
									(ehealthBarLength - eRemBarsFin));
					
					ehealthBarLength = ehealthBar.length();
					
					if(ehealthBar == "" && enemy.GetHealth() > 0)
					{
						ehealthBar = "|";
					}
					
					System.out.printf("%s                      %s\n", players, enemys);
					System.out.printf("%s%s%s\n", phealthBar, spaces, ehealthBar);
					//System.out.printf("%f                      %f\n", player.GetHealth(), enemy.GetHealth());
					System.out.println("\n\n\n\n\n");
					
					if(!playerTurn)
						turnCounter = 1;
					else
						turnCounter = 0;
				}
				
			}// end fight while
		}// end round while
		
		if(playerWinCount == 2)
		{
			System.out.println("Player Wins!!!");
		}
		else
		{
			System.out.println("Computer Wins!!!");
		}
	}// main
	
	private static void CreateArmour(int choice, String who) 
	{
		switch(choice)
		{
			case 1: // Leather
			{
				if(who == "Player")
					pArmour = new Leather();
				else
					eArmour = new Leather();
				
				break;
			}
			
			case 2: // Chain
			{
				if(who == "Player")
					pArmour = new Chain();
				else
					eArmour = new Chain();	
				
				break;
			}
			
			case 3: // Plate
			{
				if(who == "Player")
					pArmour = new Plate();
				else
					eArmour = new Plate();
				
				break;
			}
			
			default:
			{
				//should not happen
				break;
			}
		}
	}
	private static String CreateWeapon(int choice, String who) 
	{
		String weapon = "";
		switch(choice)
		{
			case 1: // Sword
			{
				if(who == "Player")
					pWeapon = new Sword();
				else
					eWeapon = new Sword();
				
				weapon = "Sword";
				break;
			}
			
			case 2: // Hammer
			{
				if(who == "Player")
					pWeapon = new Hammer();
				else
					eWeapon = new Hammer();
				
				weapon = "Hammer";
				break;
			}
			
			case 3: // Axe
			{
				if(who == "Player")
					pWeapon = new Axe();
				else
					eWeapon = new Axe();
				
				weapon = "Axe";
				break;
			}
			
			default:
			{
				//should not happen
				break;
			}
		}
		return weapon;
	}
	
	private static void Delay()
	{
		try 
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void CreateWarrior(int choice, String who) 
	{
		switch(choice)
		{
			case 1: // Human
			{
				if(who == "Player")
					player = new Human();
				else
					enemy = new Human();
				
				break;
			}
			
			case 2: // Elf
			{
				if(who == "Player")
					player = new Elf();
				else
					enemy = new Elf();	
				
				break;
			}
			
			case 3: // Orc
			{
				if(who == "Player")
					player = new Orc();
				else
					enemy = new Orc();
				break;
			}
			
			default:
			{
				//should not happen
				break;
			}
		}
	}
}// class

class ConsoleInputReadTask implements Callable<Integer> 
{
	  public Integer call() throws IOException 
	  {
	    BufferedReader br = new BufferedReader(
	        new InputStreamReader(System.in));
	   // System.out.println("ConsoleInputReadTask run() called.");
	    int input = 0;
	    String loopTest;
	    do 
	    {
	     // System.out.println("Please type something: ");
	      try 
	      {
	        // wait until we have data to complete a readLine()
	        while (!br.ready()) 
	        {
	          Thread.sleep(200);
	        }
	        loopTest = br.readLine();
	        input = Integer.parseInt(loopTest);
	      } catch (InterruptedException e) 
	      {
	       // System.out.println("ConsoleInputReadTask() cancelled");
	        return null;
	      }
	    } while ("".equals(loopTest));
	    //System.out.println("Thank You for providing input!");
	    return input;
	  }
	}

class ConsoleInput 
{
	  private final int tries;
	  private final int timeout;
	  private final TimeUnit unit;

	  public ConsoleInput(int tries, int timeout, TimeUnit unit) 
	  {
	    this.tries = tries;
	    this.timeout = timeout;
	    this.unit = unit;
	  }

	  public int readLine() throws InterruptedException 
	  {
	    ExecutorService ex = Executors.newSingleThreadExecutor();
	    int input = 2;
	    try 
	    {
	      // start working
	      for (int i = 0; i < tries; i++) 
	      {
	        //System.out.println(String.valueOf(i + 1) + ". loop");
	        Future<Integer> result = ex.submit(
	            new ConsoleInputReadTask());
	        try 
	        {
	          input = result.get(timeout, unit);
	          break;
	        } catch (ExecutionException e) 
	        {
	          e.getCause().printStackTrace();
	        } catch (TimeoutException e) 
	        {
	         // System.out.println("Cancelling reading task");
	          result.cancel(true);
	         // System.out.println("\nThread cancelled. input is null");
	        }
	      }
	    } finally 
	    {
	      ex.shutdownNow();
	    }
	    return input;
	  }
}