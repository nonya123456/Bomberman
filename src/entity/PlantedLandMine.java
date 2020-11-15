package entity;

import entity.base.Entity;
import entity.base.Updatable;
import entity.blast.BlastFromPlayer;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class PlantedLandMine extends Entity implements Updatable {
	public PlantedLandMine(double x, double y) {
		super();
		this.setX(x);
		this.setY(y);

	}

	public void explode() {
		GameController.getAddedEntity()
				.add(new BlastFromPlayer(this.getX(), this.getY(), 0));
		RenderableHolder.explosionSound.play();
	}

	@Override
	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		// TODO Auto-generated method stub
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].hasEnemy()) {
			this.isDestroyed = true;
			explode();

		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.PLANTED_LAND_MINE;
	}

}
