package textbasedengine.entities;

import textbasedengine.Main;

public class Player {
	public static Location currentLocation;
	public static boolean dead = false;
	
	public static void init() {
		dead = false;
		Main.output.setText("");
		moveTo(Location.start());
	}
	
	public static void moveTo(Location newLoc) {
		currentLocation = newLoc;
		Main.output.append(String.format(newLoc.getDescription() + "%n"));
		newLoc.onEnter();
	}
	
	public static void death() {
		dead = true;
		Main.output.append(String.format("You are dead. Type \"start over\" to restart.%n"));
	}
}