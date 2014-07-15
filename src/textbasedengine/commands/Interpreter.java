package textbasedengine.commands;

import textbasedengine.Main;
import textbasedengine.entities.Player;

public class Interpreter {
	public static void run(String input) { run(input.toLowerCase().split("\\s")); }
	public static void run(String[] input) {
		if (!Player.dead)
			switch (input[0]) {
				case "go":
					if (input.length < 2)
						Main.output.append("Nowhere to go!");
					else {
						String place = input[1];
						if (!Player.currentLocation.checkPhrase(place))
							Main.output.append(String.format(input[1] + ": I can't go there!%n"));
					}
					break;
				default:
					Main.output.append(String.format("I don't recognize the command '%s'.", input[0]));
					break;
				
			}
		else
			if (input[0].equals("start") && input[1].equals("over")) {
				Player.init();
			}
	}
}
