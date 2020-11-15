package entity.bomb;

import java.util.ArrayList;

import entity.Player;
import entity.base.Entity;
import entity.base.Interactable;
import entity.blast.Blast;
import entity.blast.BlastFromPlayer;
import entity.enemy.BombEater;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class BombFromPlayer extends Bomb implements Interactable {

	public BombFromPlayer(double x, double y, int power) {
		super(x, y, power);
		// TODO Auto-generated constructor stub
	}

	public void interact(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof Blast) {
			this.timeCount = 0;
		} else if (e instanceof Player) {
			if (((Player) e).isGlovesEquipped()) {
				this.setDirection(e.getDirection());
				this.setSpeed(5);
			}
		} else if (e instanceof BombEater) {
			RenderableHolder.bombEatingSound.play();
			this.isDestroyed = true;
		}
	}

	public void explode() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		ArrayList<Entity> newBlast = new ArrayList<Entity>();
		// number of cell between wall and player in 4 directions
		int numOfLeftCells = 0;
		int numOfRightCells = 0;
		int numOfUpCells = 0;
		int numOfDownCells = 0;

		while (!(GameController.getGameMap().getCellMap()[xCoord - numOfLeftCells][yCoord]
				.isBlockable())) {
			numOfLeftCells += 1;
		}
		if (GameController.getGameMap().getCellMap()[xCoord - numOfLeftCells][yCoord]
				.hasUnbreakableWall()) {
			numOfLeftCells -= 1;
		}

		while (!(GameController.getGameMap().getCellMap()[xCoord
				+ numOfRightCells][yCoord].isBlockable())) {
			numOfRightCells += 1;
		}
		if (GameController.getGameMap().getCellMap()[xCoord + numOfRightCells][yCoord]
				.hasUnbreakableWall()) {
			numOfRightCells -= 1;
		}

		while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord - numOfUpCells]
				.isBlockable())) {
			numOfUpCells += 1;
		}
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord - numOfUpCells]
				.hasUnbreakableWall()) {
			numOfUpCells -= 1;
		}

		while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord + numOfDownCells]
				.isBlockable())) {
			numOfDownCells += 1;
		}
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord + numOfDownCells]
				.hasUnbreakableWall()) {
			numOfDownCells -= 1;
		}

		for (int i = 0; i < Math.min(power, numOfRightCells); i++) {
			newBlast.add(new BlastFromPlayer((xCoord + i + 1) * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, 1));
		}
		for (int i = 0; i < Math.min(power, numOfLeftCells); i++) {
			newBlast.add(new BlastFromPlayer((xCoord - i - 1) * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, 1));
		}
		for (int i = 0; i < Math.min(power, numOfUpCells); i++) {
			newBlast.add(new BlastFromPlayer(xCoord * GameMap.PIXELS_PER_BLOCK,
					(yCoord - i - 1) * GameMap.PIXELS_PER_BLOCK, 2));
		}
		for (int i = 0; i < Math.min(power, numOfDownCells); i++) {
			newBlast.add(new BlastFromPlayer(xCoord * GameMap.PIXELS_PER_BLOCK,
					(yCoord + i + 1) * GameMap.PIXELS_PER_BLOCK, 2));
		}

		newBlast.add(new BlastFromPlayer(xCoord * GameMap.PIXELS_PER_BLOCK,
				yCoord * GameMap.PIXELS_PER_BLOCK, 0));

		GameController.getAddedEntity().addAll(newBlast);
		RenderableHolder.explosionSound.play();
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.BOMB;
	}
}
