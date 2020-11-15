package entity.enemy;

import entity.base.Entity;
import entity.blast.Blast;
import entity.bomb.Bomb;
import entity.bomb.BombFromEnemy;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;

public class Mechabomb extends Enemy {
	public static final int HP = 2;
	public static final int BOMB_POWER = 4;
	private boolean isBomb;
	private Bomb bomb;

	public Mechabomb(double x, double y, Direction d) {
		super(x, y, HP, d);
		this.setSpeed(1);
		this.isBomb = false;
		this.timeCount = 0;

		// TODO Auto-generated constructor stub
	}

	public boolean isMovePossible(Direction d) {
		if (this.isBomb) {
			return false;
		}
		return super.isMovePossible(d);
	}

	private void transform() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		bomb = new BombFromEnemy(xCoord * GameMap.PIXELS_PER_BLOCK,
				yCoord * GameMap.PIXELS_PER_BLOCK, BOMB_POWER);

		GameController.getAddedEntity().add(bomb);
		this.isBomb = true;
		this.isVisible = false;
		this.setSpeed(0);
		this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
		this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);

	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		super.update();
		if (bomb != null && bomb.isDestroyed()) {
			this.timeCount += 1;
			if (this.timeCount >= Blast.TIME) {
				this.isBomb = false;
				this.setSpeed(1);
				this.timeCount = 0;
				this.isVisible = true;
			}
		}

		if (!isBomb && Math.random() < 0.002
				&& GameController.getGameMap().getCellMap()[xCoord][yCoord].getEntities()
						.size() == 1) {
			this.transform();
		}

	}

	public void interact(Entity e) {
		if (!this.isBomb) {
			super.interact(e);
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.MECHABOMB;
	}

	public void changeDirection() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		int playerXCoord = (int) Math
				.round(GameController.getPlayer().getX() / GameMap.PIXELS_PER_BLOCK);
		int playerYCoord = (int) Math
				.round(GameController.getPlayer().getY() / GameMap.PIXELS_PER_BLOCK);

		super.changeDirection();
		if (this.x == xCoord * GameMap.PIXELS_PER_BLOCK
				&& this.y == yCoord * GameMap.PIXELS_PER_BLOCK) {
			if (xCoord == playerXCoord && Math.abs(yCoord - playerYCoord) < 3) {
				if (yCoord < playerYCoord && !GameController.getGameMap().isBlockable(1,
						xCoord, yCoord, playerYCoord)) {
					this.setDirection(Direction.DOWN);
				} else if (yCoord > playerYCoord && !GameController.getGameMap()
						.isBlockable(1, xCoord, playerYCoord, yCoord)) {
					this.setDirection(Direction.UP);
				}
			} else if (yCoord == playerYCoord && Math.abs(xCoord - playerXCoord) < 3) {
				if (xCoord < playerXCoord && !GameController.getGameMap().isBlockable(0,
						yCoord, xCoord, playerXCoord)) {
					this.setDirection(Direction.RIGHT);
				} else if (xCoord > playerXCoord && !GameController.getGameMap()
						.isBlockable(0, yCoord, playerXCoord, xCoord)) {
					this.setDirection(Direction.LEFT);
				}
			}
		}

	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

}
