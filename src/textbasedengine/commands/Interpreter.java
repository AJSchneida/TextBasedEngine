package textbasedengine.commands;

import textbasedengine.Main;
import textbasedengine.entities.Player;
import textbasedengine.entities.Stats;
import textbasedengine.entities.items.Equipable;
import textbasedengine.entities.items.Item;

/**
 * Checks input from the user and processes any commands that might be needed
 * @author Austin
 * @version 0.0.1
 */
public class Interpreter {
	/**
	 * Converts the simple input from the input field into multiple parameters
	 * @param input The input from the input field
	 */
	public static void run(String input) { run(input.toLowerCase().split("\\s")); }
	
	/**
	 * Handles the actual commands sent from the input fields
	 * @param input The individual commands and parameters typed by the player
	 */
	public static void run(String[] input) {
		if (!Player.dead)
			switch (input[0]) {
				case "go":
					if (input.length < 2)
						Main.output.append(String.format("Nowhere to go!%n"));
					else {
						String place = input[1];
						if (!Player.currentLocation.checkPhrase(place))
							Main.output.append(String.format(input[1] + ": I can't go there!%n"));
					}
					break;
				case "grab":
					if (input.length < 2)
						Main.output.append(String.format("Nothing to grab!%n"));
					else {
						String itemName = input[1];
						Item item = Player.currentLocation.checkItem(itemName);
						if (item != null)
							Player.addItem(item);
						else
							Main.output.append(String.format("Couldn't find that around me.%n"));
					}
					break;
				case "look":
					if (input.length == 1)
						Main.output.append(String.format(Player.currentLocation.getDescription() + "%n"));
					else {
						Item item;
						
						// check items in inventory
						item = Player.checkItems(input[1]);
						if (item != null)
							Main.output.append(String.format(item.getDescription() + " (in inventory)%n"));
						else {
							// check items in location
							item = Player.currentLocation.checkItem(input[1]);
							if (item != null)
								Main.output.append(String.format(item.getDescription() + "%n"));
							else
								Main.output.append(String.format("Couldn't see that around me.%n"));
						}
					}
					break;
				case "use":
					if (input.length < 2)
						Main.output.append(String.format("Nothing to use!%n"));
					else {
						String itemName = input[1];
						Item item = Player.checkItems(itemName);
						if (item != null)
							item.onUse();
						else
							Main.output.append(String.format("I couldn't find that to use.%n"));
					}
					break;
					/**
					 * Added equip and stats text input.
					 */
				case "equip":
					if (input.length < 2)
						Main.output.append(String.format("Nothing to equip!%n"));
					else {
						String itemName = input[1];
						Equipable equipment = (Equipable) Player.checkItems(itemName);
						if (equipment != null)
							equipment.onEquip();
						else
							Main.output.append(String.format("I couldn't find that to equip.%n"));
					}
					break;
				case "stats":
					Stats.printStats();
					break;
				default:
					Main.output.append(String.format("I don't recognize the command '%s'.", input[0]));
			}
		else
			if (input[0].equals("start") && input[1].equals("over")) {
				Player.init();
			}
	}
}
