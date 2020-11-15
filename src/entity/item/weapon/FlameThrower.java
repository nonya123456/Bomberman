package entity.item.weapon;

import java.util.ArrayList;
import entity.Player;
import entity.base.Entity;
import entity.blast.Fire;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class FlameThrower extends Weapon {

	public FlameThrower(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.FLAMETHROWER;
	}

	@Override
	public void fire(Player p) {
		// TODO Auto-generated method stub
		int xCoord = (int) Math.round(p.getX() / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(p.getY() / GameMap.PIXELS_PER_BLOCK);
		ArrayList<Entity> newBlast = new ArrayList<Entity>();
		int numOfCells = 0;

		if (p.getDirection() == Direction.RIGHT) {
			while (!(GameController.getGameMap().getCellMap()[xCoord + numOfCells][yCoord]
					.hasUnbreakableWall())) {
				numOfCells += 1;
			}
			for (int i = 0; i < Math.min(3, numOfCells - 1); i++) {
				newBlast.add(new Fire((xCoord + i + 1) * GameMap.PIXELS_PER_BLOCK,
						yCoord * GameMap.PIXELS_PER_BLOCK));
			}
		} else if (p.getDirection() == Direction.LEFT) {
			while (!(GameController.getGameMap().getCellMap()[xCoord - numOfCells][yCoord]
					.hasUnbreakableWall())) {
				numOfCells += 1;
			}
			for (int i = 0; i < Math.min(3, numOfCells - 1); i++) {
				newBlast.add(new Fire((xCoord - i - 1) * GameMap.PIXELS_PER_BLOCK,
						yCoord * GameMap.PIXELS_PER_BLOCK));
			}
		} else if (p.getDirection() == Direction.UP) {
			while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord - numOfCells]
					.hasUnbreakableWall())) {
				numOfCells += 1;
			}
			for (int i = 0; i < Math.min(3, numOfCells - 1); i++) {
				newBlast.add(new Fire(xCoord * GameMap.PIXELS_PER_BLOCK,
						(yCoord - i - 1) * GameMap.PIXELS_PER_BLOCK));
			}
		} else if (p.getDirection() == Direction.DOWN) {
			while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord + numOfCells]
					.hasUnbreakableWall())) {
				numOfCells += 1;
			}
			for (int i = 0; i < Math.min(3, numOfCells - 1); i++) {
				newBlast.add(new Fire(xCoord * GameMap.PIXELS_PER_BLOCK,
						(yCoord + i + 1) * GameMap.PIXELS_PER_BLOCK));
			}
		}

		GameController.getAddedEntity().addAll(newBlast);
		RenderableHolder.flameThrowerSound.play();
	}

}
