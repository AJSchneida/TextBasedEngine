package textbasedengine.entities;

import textbasedengine.Main;

/**
 * Class that encapsulates all stats for the player.
 * @author AJ
 * @version 0.0.1
 */

public class Stats {

	public static String playerName;
	public static int currentHealth;
	public static int maxHealth;
	private static int currentMana;
	private static int maxMana;
	
	public Stats(String playerName, int currentHealth, int maxHealth, int currentMana, int maxMana){
		Stats.playerName = playerName;
		Stats.currentHealth = currentHealth;
		Stats.maxHealth = maxHealth;
		Stats.currentMana = currentMana;
		Stats.maxMana = maxMana;
	}
	
	public static String getPlayerName(){
		return playerName;
	}
	
	public static void setPlayerName(String newName){
		playerName = newName;
	}
	
	public static int getCurrentHealth(){
		return currentHealth;
	}
	
	/**
	 * setHealth is used any time the player heals or takes damage. It automatically checks if the player is dead.
	 */
	
	public static void setHealth(int newHealth){
		if(newHealth > currentHealth){
			currentHealth = maxHealth;
		}
		currentHealth = newHealth;
		if(currentHealth <= 0){
			Player.dead = true;
			Main.output.append(String.format("You are dead. Type \"start over\" to restart.%n"));
		}
	}
	
	public static void setMaxHealth(int newMaxHealth){
		maxHealth = newMaxHealth;
	}
	
	public static int getCurrentMana(){
		return currentMana;
	}
	
	public static void setCurrentMana(int newMana){
		if(newMana > currentMana){
			currentMana = maxMana;
		}
			currentMana = newMana;
	}
	
	public static void setMaxMana(int newMaxMana){
		maxMana = newMaxMana;
	}
	
	public static void printStats(){
		Main.output.append(String.format("Your current stats are " + getCurrentHealth() + " HP and " + getCurrentMana() + " Mana.%n"));
	}
}
