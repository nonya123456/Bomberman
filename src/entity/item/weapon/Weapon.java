package entity.item.weapon;

import entity.Player;
import entity.base.Entity;
import entity.item.Item;
import exception.InvalidLandMinePlantingException;

public abstract class Weapon extends Item {

	public Weapon(double x, double y) {
		super(x, y);

	}

	private void addWeapon(Player p) {
		p.setWeapon(this);
	}

	public abstract void fire(Player p) throws InvalidLandMinePlantingException;

	public void interact(Entity e) {
		super.interact(e);
		if (e instanceof Player) {
			this.setDestroyed(true);
			this.addWeapon((Player) e);
		}
	}
}
