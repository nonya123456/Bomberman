package entity.item;

import entity.Player;

import entity.base.Entity;
import entity.base.Interactable;
import entity.base.Updatable;
import entity.blast.Blast;
import logic.GameController;
import logic.gamemap.GameMap;

public abstract class Item extends Entity implements Interactable, Updatable {
	public static final int NUM_OF_ITEMS = 7;

	public Item(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	public void interact(Entity e) {
		if (e instanceof Blast) {
			this.isDestroyed = true;
		}
	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		// TODO Auto-generated method stub
		Player p = GameController.getPlayer();
		if (xCoord == (int) Math.round(p.getX() / GameMap.PIXELS_PER_BLOCK)
				&& yCoord == (int) Math.round(p.getY() / GameMap.PIXELS_PER_BLOCK)) {
			this.interact(p);
		}
	}
}
