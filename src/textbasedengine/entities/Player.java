package textbasedengine.entities;

import textbasedengine.Main;

/**
 * Handles the logic and data for the player
 * @author Austin
 * @version 0.0.1
 */
public class Player {
	public static Location currentLocation;
	public static boolean dead = false;
	public static Stats playerStats;
	
	/**
	 * Resets the output window and initializes player information
	 * 
	 */
	public static void init() {
		dead = false;
		Main.output.setText("");
		moveTo(Location.start());
		playerStats = new Stats("mainCharacter", 100, 100);
	}
	
	/**
	 * Moves the player to a new location, displaying its description
	 * @param newLoc The location to move to
	 */
	public static void moveTo(Location newLoc) {
		currentLocation = newLoc;
		Main.output.append(String.format(newLoc.getDescription() + "%n"));
		newLoc.onEnter();
	}
	
}
