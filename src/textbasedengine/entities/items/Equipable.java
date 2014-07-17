package textbasedengine.entities.items;

/**
 * Equipable extends Item and add onEquip and onUnequip methods
 * TODO: add a way to connect an array of equipables to the player to indicate the set of currently equipped items.
 * @author AJ
 * @version 0.0.1
 */

public abstract class Equipable extends Item{
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract void onAcquire();
	public abstract void onUse();
	public abstract void onDrop();
	public abstract void onEquip();
	public abstract void onUnequip();
	
	

}
