package textbasedengine.entities;

import java.util.ArrayList;
import java.util.List;

import textbasedengine.Main;
import textbasedengine.entities.items.Item;

/**
 * Handles the logic and data for the player
 * @version 0.0.1
 * @author Austin
 */
public class Player {
	public static Location currentLocation;
	public static boolean dead = false;
	public static List<Item> items = new ArrayList<Item>();
	public static Stats playerStats;
	
	/**
	 * Resets the output window and initializes player information
	 */
	public static void init() {
		dead = false;
		Main.output.setText("");
		moveTo(Location.start());
		playerStats = new Stats("mainCharacter", 100, 100, 100, 100);
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
	
	/**
	 * Sets the player state to dead
	 */
	public static void death() {
		dead = true;
		Main.output.append(String.format("You are dead. Type \"start over\" to restart.%n"));
	}
	
	public static Item checkItems(String phrase) {
		for (int i = 0; i < items.size(); i++)
			if (items.get(i).getName().equals(phrase))
				return items.get(i);
		
		return null;
	}
	
	public static void addItem(Item item) {
		item.onAcquire();
		items.add(item);
	}
	
	public static void useItem(String phrase) {
		for (Item item : items)
			if (item.getName().equals(phrase))
				item.onUse();
	}
	
	public static void dropItem(String phrase) {
		
	}
}
