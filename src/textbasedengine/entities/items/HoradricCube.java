package textbasedengine.entities.items;

import textbasedengine.Main;
import textbasedengine.entities.Location;
import textbasedengine.entities.Player;

public class HoradricCube extends Item {
	@Override
	public String getDescription() { return "A strange cube covered in ornate inscriptions. It seems to hum and the runes on its faces almost appear to move before you."; }

	@Override
	public String getName() { return "cube"; }
	
	@Override
	public void onAcquire() {
		Main.output.append(String.format("As you pick up the strange cube, it begins to vibrate in your hand. Suddenly, a portal opens up before you very eyes!%n"));
		Player.currentLocation.updateConnection(3, true);
	}

	@Override
	public void onUse() {
		if (Player.currentLocation.getID() == 3) {
			Main.output.append(String.format("The cube glows a bright blue as you pull it out. A bright flash surounds you!%n"));
			Player.moveTo(Location.getLocation(2));
		}			
		else
			Main.output.append(String.format("The cube seems to hum as it always has in your hand. Nothing else happens.%n"));
	}

	@Override
	public void onDrop() {}
}
