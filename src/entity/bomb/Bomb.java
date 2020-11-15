package entity.bomb;

import entity.base.Blockable;
import entity.base.Entity;
import entity.base.Updatable;
import entity.enemy.BombEater;
import entity.enemy.Enemy;
import logic.Direction;
import logic.GameController;
import logic.gamemap.GameMap;

public abstract class Bomb extends Entity implements Blockable, Updatable {
	public static final int TIME = 180;
	protected int timeCount;
	protected int power;

	public Bomb(double x, double y, int power) {
		super();
		this.timeCount = TIME;
		this.setX(x);
		this.setY(y);
		this.power = power;
		this.isBlocked = false;
	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		this.timeCount -= 1;
		if (this.isMovePossible(this.getDirection())) {
			this.move();
		} else {
			this.isBlocked = false;
			this.setSpeed(0);
		}
		if (this.timeCount < 1) {
			GameController.getGameMap().getCellMap()[xCoord][yCoord].clear();
			this.isDestroyed = true;
			this.explode();

		}
	}

	public boolean isMovePossible(Direction d) {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		if (this.speed == 0) {
			return false;
		}
		if (d == Direction.UP
				&& (GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
						.isBlockable()
						|| GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
								.hasEnemy())
				&& y % GameMap.PIXELS_PER_BLOCK == 0) {

			return false;
		}
		if (d == Direction.DOWN
				&& (GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
						.isBlockable()
						|| GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
								.hasEnemy())
				&& y % GameMap.PIXELS_PER_BLOCK == 0) {

			return false;
		}
		if (d == Direction.LEFT
				&& (GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
						.isBlockable()
						|| GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
								.hasEnemy())
				&& x % GameMap.PIXELS_PER_BLOCK == 0) {

			return false;
		}
		if (d == Direction.RIGHT
				&& (GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
						.isBlockable()
						|| GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
								.hasEnemy())
				&& x % GameMap.PIXELS_PER_BLOCK == 0) {

			return false;
		}

		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].hasEnemy()) {
			this.isBlocked = true;
			for (Entity e : GameController.getGameMap().getCellMap()[xCoord][yCoord]
					.getEntities()) {
				if (e instanceof Enemy && !(e instanceof BombEater)) {
					e.setBlocked(true);
				}
			}
		}
		if (this.isBlocked && x % GameMap.PIXELS_PER_BLOCK == 0
				&& y % GameMap.PIXELS_PER_BLOCK == 0) {

			return false;
		}

		return true;
	}

	public double getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public abstract void explode();

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

}
