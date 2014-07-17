package textbasedengine.entities.items;

import textbasedengine.Main;
import textbasedengine.entities.Stats;

public class BlackRobe extends Equipable {

	@Override
	public String getName() { return "robe"; }

	@Override
	public String getDescription() { return "A plain set of black robes to be worn by a mage"; }

	@Override
	public void onAcquire() {
		Main.output.append(String.format("The black robes rustle as you pick them up.%n"));
	}

	@Override
	public void onUse() {
		Main.output.append(String.format("Can not use robes. Try to EQUIP ROBE.%n"));
	}

	@Override
	public void onDrop() {
	}

	//TODO: implement equip in interpreter
	@Override
	public void onEquip() {
		Stats.setMaxMana(110);
		Stats.setCurrentMana(110);
		Main.output.append(String.format("You feel your psychic faculties expand ever so slightly.%n"));
	}

	@Override
	public void onUnequip() {
		Stats.setCurrentMana(100);
		Stats.setMaxMana(100);
	}

}
