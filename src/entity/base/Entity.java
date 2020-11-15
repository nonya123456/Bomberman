package entity.base;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;
import logic.gamemap.GameMap;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public abstract class Entity implements IRenderable {
	protected double x;
	protected double y;
	protected double speed;
	protected boolean isVisible;
	protected boolean isDestroyed;
	protected boolean isBlocked;
	protected Direction direction;
	protected boolean isImmortal;

	public Entity() {
		direction = Direction.NONE;
		this.speed = 0;
		this.isDestroyed = false;
		this.isVisible = true;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void move() {

		if (this.direction == Direction.LEFT) {
			this.setX(this.getX() - this.speed);

		} else if (this.direction == Direction.RIGHT) {
			this.setX(this.getX() + this.speed);

		} else if (this.direction == Direction.UP) {
			this.setY(this.getY() - this.speed);

		} else if (this.direction == Direction.DOWN) {
			this.setY(this.getY() + this.speed);

		}

	}

	public boolean isMovePossible(Direction d) {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		double targetX;
		double targetY;

		if (this.isBlocked) {
			if (d == Direction.UP) {
				this.setY((yCoord + 1) * GameMap.PIXELS_PER_BLOCK);
			} else if (d == Direction.DOWN) {
				this.setY((yCoord - 1) * GameMap.PIXELS_PER_BLOCK);
			} else if (d == Direction.LEFT) {
				this.setX((xCoord + 1) * GameMap.PIXELS_PER_BLOCK);
			} else if (d == Direction.RIGHT) {
				this.setX((xCoord - 1) * GameMap.PIXELS_PER_BLOCK);
			}
			this.isBlocked = false;
			return false;
		}

		if (d == Direction.UP
				&& GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
						.isBlockable()) {

			targetY = GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
					.getEntity(0).getY();
			if (Math.abs(y - targetY) - GameMap.PIXELS_PER_BLOCK <= 0) {
				if (this.getDirection() != d) {
					return false;
				}
				if (targetY % GameMap.PIXELS_PER_BLOCK == 0) {
					this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
					return false;
				}
			}

		}
		if (d == Direction.DOWN
				&& GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
						.isBlockable()) {

			targetY = GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
					.getEntity(0).getY();
			if (Math.abs(y - targetY) - GameMap.PIXELS_PER_BLOCK <= 0) {
				if (this.getDirection() != d) {
					return false;
				}
				if (targetY % GameMap.PIXELS_PER_BLOCK == 0) {
					this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
					return false;
				}
			}

		}
		if (d == Direction.LEFT
				&& GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
						.isBlockable()) {

			targetX = GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
					.getEntity(0).getX();
			if (Math.abs(x - targetX) - GameMap.PIXELS_PER_BLOCK <= 0) {
				if (this.getDirection() != d) {
					return false;
				}
				if (targetX % GameMap.PIXELS_PER_BLOCK == 0) {
					this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
					return false;
				}
			}
		}
		if (d == Direction.RIGHT
				&& GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
						.isBlockable()) {

			targetX = GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
					.getEntity(0).getX();
			if (Math.abs(x - targetX) - GameMap.PIXELS_PER_BLOCK <= 0) {
				if (this.getDirection() != d) {
					return false;
				}
				if (targetX % GameMap.PIXELS_PER_BLOCK == 0) {
					this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isDestroyed() {
		return this.isDestroyed;
	}

	public boolean isVisible() {
		return this.isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isImmortal() {
		return isImmortal;
	}

	public void setImmortal(boolean isImmortal) {
		this.isImmortal = isImmortal;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
	}

}
