package entity.item.weapon;

import entity.PlantedLandMine;
import entity.Player;
import exception.InvalidLandMinePlantingException;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class LandMine extends Weapon {

	public LandMine(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Player p) throws InvalidLandMinePlantingException {
		// TODO Auto-generated method stub
		int xCoord = (int) Math.round(p.getX() / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(p.getY() / GameMap.PIXELS_PER_BLOCK);

		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].hasLandMine()) {
			throw new InvalidLandMinePlantingException();
		}
		if (GameController.getDoor() != null) {
			if (xCoord == (int) Math
					.round(GameController.getDoor().getX() / GameMap.PIXELS_PER_BLOCK)
					&& yCoord == (int) Math.round(
							GameController.getDoor().getY() / GameMap.PIXELS_PER_BLOCK)) {
				throw new InvalidLandMinePlantingException();
			}
		}

		GameController.getAddedEntity().add(new PlantedLandMine(
				xCoord * GameMap.PIXELS_PER_BLOCK, yCoord * GameMap.PIXELS_PER_BLOCK));
		RenderableHolder.landmineSound.stop();
		RenderableHolder.landmineSound.play();
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.LANDMINE;
	}

}
