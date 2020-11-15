package entity.enemy;

import entity.bomb.BombFromPlayer;
import entity.bomb.Rocket;
import logic.Cell;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;

public class BombEater extends Enemy {

	public static final int HP = 2;

	public BombEater(double x, double y, Direction d) {
		super(x, y, HP, d);
		this.setSpeed(1.5);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.BOMBEATER;
	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].hasBomb()) {
			Cell cell = GameController.getGameMap().getCellMap()[xCoord][yCoord];
			if (cell.getEntities().get(0) instanceof BombFromPlayer
					&& !(cell.getEntities().get(0) instanceof Rocket)) {
				((BombFromPlayer) cell.getEntities().get(0)).interact(this);
			}

		}
		super.update();
	}

	public boolean isMovePossible(Direction d) {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		if (d == Direction.UP
				&& (GameController.getGameMap().getCellMap()[xCoord][yCoord - 1].hasWall()
						|| GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
								.hasBombFromEnemy())) {
			double targetY = GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
					.getEntity(0).getY();

			if (Math.abs(y - targetY) - GameMap.PIXELS_PER_BLOCK < speed) {

				this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
				return false;

			}

		}
		if (d == Direction.DOWN
				&& (GameController.getGameMap().getCellMap()[xCoord][yCoord + 1].hasWall()
						|| GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
								.hasBombFromEnemy())) {

			double targetY = GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
					.getEntity(0).getY();
			if (Math.abs(y - targetY) - GameMap.PIXELS_PER_BLOCK < speed) {

				this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
				return false;

			}
		}
		if (d == Direction.LEFT
				&& (GameController.getGameMap().getCellMap()[xCoord - 1][yCoord].hasWall()
						|| GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
								.hasBombFromEnemy())) {
			double targetX = GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
					.getEntity(0).getX();
			if (Math.abs(x - targetX) - GameMap.PIXELS_PER_BLOCK < speed) {

				this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
				return false;

			}
		}
		if (d == Direction.RIGHT
				&& (GameController.getGameMap().getCellMap()[xCoord + 1][yCoord].hasWall()
						|| GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
								.hasBombFromEnemy())) {
			double targetX = GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
					.getEntity(0).getX();
			if (Math.abs(x - targetX) - GameMap.PIXELS_PER_BLOCK < speed) {

				this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
				return false;

			}
		}
		return true;
	}

	public void changeDirection() {
		int min;
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		int up = 99, down = 99, left = 99, right = 99;

		super.changeDirection();
		for (int i = xCoord - 1; i >= 0; i--) {
			if (GameController.getGameMap().getCellMap()[i][yCoord].isBlockable()) {
				if (GameController.getGameMap().getCellMap()[i][yCoord].hasBomb()
						&& GameController.getGameMap().getCellMap()[i][yCoord]
								.getEntities().get(0) instanceof BombFromPlayer) {

					left = xCoord - i;

				}
				break;
			}
		}
		for (int i = xCoord + 1; i < GameMap.WIDTH; i++) {
			if (GameController.getGameMap().getCellMap()[i][yCoord].isBlockable()) {
				if (GameController.getGameMap().getCellMap()[i][yCoord].hasBomb()
						&& GameController.getGameMap().getCellMap()[i][yCoord]
								.getEntities().get(0) instanceof BombFromPlayer) {

					right = i - xCoord;

				}
				break;
			}
		}
		for (int i = yCoord - 1; i >= 0; i--) {
			if (GameController.getGameMap().getCellMap()[xCoord][i].isBlockable()) {
				if (GameController.getGameMap().getCellMap()[xCoord][i].hasBomb()
						&& GameController.getGameMap().getCellMap()[xCoord][i]
								.getEntities().get(0) instanceof BombFromPlayer) {

					up = yCoord - i;

				}
				break;
			}
		}
		for (int i = yCoord + 1; i < GameMap.HEIGHT; i++) {
			if (GameController.getGameMap().getCellMap()[xCoord][i].isBlockable()) {
				if (GameController.getGameMap().getCellMap()[xCoord][i].hasBomb()
						&& GameController.getGameMap().getCellMap()[xCoord][i]
								.getEntities().get(0) instanceof BombFromPlayer) {

					down = i - yCoord;

				}
				break;
			}
		}
		min = Math.min(Math.min(Math.min(up, down), left), right);
		if (this.x == xCoord * GameMap.PIXELS_PER_BLOCK
				&& this.y == yCoord * GameMap.PIXELS_PER_BLOCK && min != 99) {
			if (min == up) {
				this.setDirection(Direction.UP);
			} else if (min == down) {
				this.setDirection(Direction.DOWN);
			} else if (min == left) {
				this.setDirection(Direction.LEFT);
			} else if (min == right) {
				this.setDirection(Direction.RIGHT);
			}
		}

	}

}
