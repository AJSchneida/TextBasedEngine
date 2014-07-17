package textbasedengine.entities.items;

public abstract class Item {
	public static Item getItem(int id) {
		switch (id) {
			case 0:
				return new HoradricCube();
			case 1:
				return new BlackRobe();
		}
		return null;
	}
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract void onAcquire();
	public abstract void onUse();
	public abstract void onDrop();
}
