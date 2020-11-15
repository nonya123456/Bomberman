package entity.wall;

import entity.Door;
import entity.base.Entity;
import entity.base.Interactable;
import entity.blast.BlastFromPlayer;
import entity.blast.Fire;
import entity.item.Item;
import entity.item.powerup.Gloves;
import entity.item.powerup.IncreaseBomb;
import entity.item.powerup.IncreasePower;
import entity.item.powerup.IncreaseSpeed;
import entity.item.weapon.FlameThrower;
import entity.item.weapon.LandMine;
import entity.item.weapon.RocketLauncher;
import logic.GameController;
import logic.Sprites;

public class BreakableWall extends Wall implements Interactable {
	private boolean hasDoor;

	public BreakableWall(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		this.hasDoor = false;
	}

	public void interact(Entity e) {
		if (e instanceof BlastFromPlayer) {
			e.setDestroyed(true);
			this.destroy(true);
		} else if (e instanceof Fire) {
			this.destroy(false);
		}
	}

	public int getZ() {
		return Sprites.BREAKABLE_WALL;
	}

	private void destroy(boolean isDropped) {
		int randomNumber = (int) Math.floor(Math.random() * Item.NUM_OF_ITEMS)
				+ Sprites.GLOVES;

		this.isDestroyed = true;
		if (hasDoor) {
			GameController.setDoor(new Door(x, y));
			GameController.getAddedEntity().add(GameController.getDoor());

		} else if (Math.random() < 0.2 && isDropped) {
			switch (randomNumber) {
			case Sprites.GLOVES:
				GameController.getAddedEntity().add(new Gloves(x, y));

				break;

			case Sprites.INCREASE_BOMB:
				GameController.getAddedEntity().add(new IncreaseBomb(x, y));

				break;

			case Sprites.INCREASE_POWER:
				GameController.getAddedEntity().add(new IncreasePower(x, y));

				break;

			case Sprites.INCREASE_SPEED:
				GameController.getAddedEntity().add(new IncreaseSpeed(x, y));

				break;

			case Sprites.LANDMINE:
				GameController.getAddedEntity().add(new LandMine(x, y));

				break;

			case Sprites.ROCKETLAUNCHER:
				GameController.getAddedEntity().add(new RocketLauncher(x, y));

				break;
			case Sprites.FLAMETHROWER:
				GameController.getAddedEntity().add(new FlameThrower(x, y));

				break;
			default:
				break;
			}
		}
	}

	public boolean getHasDoor() {
		return hasDoor;
	}

	public void setHasDoor(boolean hasDoor) {
		this.hasDoor = hasDoor;
	}

}
