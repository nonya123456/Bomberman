package entity.bomb;

import java.util.ArrayList;
import entity.base.Entity;
import entity.blast.BlastFromEnemy;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class BombFromEnemy extends Bomb {

	public BombFromEnemy(double x, double y, int power) {
		super(x, y, power);
		// TODO Auto-generated constructor stub

	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		this.timeCount -= 1;
		if (this.timeCount < 1) {
			GameController.getGameMap().getCellMap()[xCoord][yCoord].clear();
			this.isDestroyed = true;
			this.explode();

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
				.hasWall())) {
			numOfLeftCells += 1;
		}
		while (!(GameController.getGameMap().getCellMap()[xCoord
				+ numOfRightCells][yCoord].hasWall())) {
			numOfRightCells += 1;
		}
		while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord - numOfUpCells]
				.hasWall())) {
			numOfUpCells += 1;
		}
		while (!(GameController.getGameMap().getCellMap()[xCoord][yCoord + numOfDownCells]
				.hasWall())) {
			numOfDownCells += 1;
		}

		for (int i = 0; i < Math.min(power, numOfRightCells - 1); i++) {
			newBlast.add(new BlastFromEnemy((xCoord + i + 1) * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, 1));
		}
		for (int i = 0; i < Math.min(power, numOfLeftCells - 1); i++) {
			newBlast.add(new BlastFromEnemy((xCoord - i - 1) * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, 1));
		}
		for (int i = 0; i < Math.min(power, numOfUpCells - 1); i++) {
			newBlast.add(new BlastFromEnemy(xCoord * GameMap.PIXELS_PER_BLOCK,
					(yCoord - i - 1) * GameMap.PIXELS_PER_BLOCK, 2));
		}
		for (int i = 0; i < Math.min(power, numOfDownCells - 1); i++) {
			newBlast.add(new BlastFromEnemy(xCoord * GameMap.PIXELS_PER_BLOCK,
					(yCoord + i + 1) * GameMap.PIXELS_PER_BLOCK, 2));
		}

		newBlast.add(new BlastFromEnemy(xCoord * GameMap.PIXELS_PER_BLOCK,
				yCoord * GameMap.PIXELS_PER_BLOCK, 0));
		GameController.getAddedEntity().addAll(newBlast);

		RenderableHolder.explosionSound.play();
	}

	public int getZ() {
		return Sprites.BOMB_FROM_ENEMY;
	}

}
