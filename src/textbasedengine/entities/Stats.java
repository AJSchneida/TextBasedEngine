package textbasedengine.entities;

import textbasedengine.Main;

/**
 * Class that defines all the states for the player.
 * @author AJ
 * @version 0.0.1
 */

public class Stats {

	public static String playerName;
	public static int healthPoints;
	public static int maxHealth;
	
	public Stats(String playerName, int healthPoints, int maxHealth){
		Stats.playerName = playerName;
		Stats.healthPoints = healthPoints;
		Stats.maxHealth = maxHealth;
	}
	
	public static String getPlayerName(){
		return playerName;
	}
	
	public static void setPlayerName(String newName){
		playerName = newName;
	}
	
	public static int getHealthPoints(){
		return healthPoints;
	}
	
	/**
	 * setHealth is used any time the player heals or takes damage. It automatically checks if the player is dead.
	 */
	
	public static void setHealth(int newHealth){
		healthPoints = newHealth;
		if(newHealth > healthPoints){
			healthPoints = maxHealth;
		}
		if(healthPoints <= 0){
			Player.dead = true;
			Main.output.append(String.format("You are dead. Type \"start over\" to restart.%n"));
		}
	}
	
}
