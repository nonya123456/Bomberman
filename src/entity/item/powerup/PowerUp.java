package entity.item.powerup;

import entity.Player;
import entity.base.Entity;
import entity.item.Item;

public abstract class PowerUp extends Item {
	public PowerUp(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void interact(Entity e) {
		super.interact(e);
		if (e instanceof Player) {
			this.setDestroyed(true);
			this.enhance((Player) e);
		}
	}

	public abstract void enhance(Player p);
}
