package entity.enemy;

import java.util.ArrayList;
import entity.base.Entity;
import entity.base.Interactable;
import entity.base.Updatable;
import entity.blast.Blast;
import entity.blast.BlastFromEnemy;
import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;

public abstract class Enemy extends Entity implements Interactable, Updatable {
	private int hp;
	protected int changeDirectionCooldown;
	protected int timeCount;
	private static final int IMMORTAL_TIME = 60;

	public Enemy(double x, double y, int hp, Direction d) {
		super();
		this.hp = hp;
		this.setX(x);
		this.setY(y);
		this.setDirection(d);
		changeDirectionCooldown = 0;
		timeCount = 0;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public void interact(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof Blast && !(e instanceof BlastFromEnemy) && !this.isImmortal) {
			this.hp -= 1;
			this.isImmortal = true;
		}
	}

	public void update() {
		int xCoord;
		int yCoord;

		if (this.isImmortal) {
			this.timeCount += 1;
		}
		if (this.timeCount == IMMORTAL_TIME) {
			this.setImmortal(false);
			this.timeCount = 0;
		}

		if (this.isMovePossible(this.getDirection())) {
			this.move();
		} else if (this.getDirection() == Direction.UP) {
			this.setDirection(Direction.DOWN);
		} else if (this.getDirection() == Direction.DOWN) {
			this.setDirection(Direction.UP);
		} else if (this.getDirection() == Direction.RIGHT) {
			this.setDirection(Direction.LEFT);
		} else if (this.getDirection() == Direction.LEFT) {
			this.setDirection(Direction.RIGHT);
		}

		changeDirection();
		if (this.getHp() < 1) {
			this.isDestroyed = true;

			GameController.setScore(GameController.getScore() + this.score());
		}
		xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		if (this.direction == Direction.LEFT)
			this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
		if (this.direction == Direction.RIGHT)
			this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
		if (this.direction == Direction.UP)
			this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
		if (this.direction == Direction.DOWN)
			this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
	}

	public int score() {
		return 50 * (this.getZ() - Sprites.MUSHROOM + 1);
	}

	public void changeDirection() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		Direction initialDirection = this.getDirection();
		ArrayList<Direction> possibleDirection = new ArrayList<>();
		int numOfDirections = 0;

		if (this.changeDirectionCooldown == 0
				&& this.x == xCoord * GameMap.PIXELS_PER_BLOCK
				&& this.y == yCoord * GameMap.PIXELS_PER_BLOCK) {
			if (this.isMovePossible(Direction.LEFT)
					&& this.getDirection() != Direction.RIGHT) {
				numOfDirections += 1;
				possibleDirection.add(Direction.LEFT);
			}
			if (this.isMovePossible(Direction.RIGHT)
					&& this.getDirection() != Direction.LEFT) {
				numOfDirections += 1;
				possibleDirection.add(Direction.RIGHT);
			}
			if (this.isMovePossible(Direction.UP)
					&& this.getDirection() != Direction.DOWN) {
				numOfDirections += 1;
				possibleDirection.add(Direction.UP);
			}
			if (this.isMovePossible(Direction.DOWN)
					&& this.getDirection() != Direction.UP) {
				numOfDirections += 1;
				possibleDirection.add(Direction.DOWN);
			}
			if (numOfDirections > 0) {
				int index = (int) (Math.random() * numOfDirections);
				this.setDirection(possibleDirection.get(index));
			}

		}
		if (this.changeDirectionCooldown > 0) {
			this.changeDirectionCooldown -= 1;
		}

		if (this.getDirection() != initialDirection) {
			this.changeDirectionCooldown = 60;
		}

	}

	public void draw(GraphicsContext gc) {
		double opacity;

		if (this.isImmortal) {
			opacity = Math.abs(15 - timeCount % 30) / 15.0;
			gc.setGlobalAlpha(opacity);
		}
		super.draw(gc);
		gc.setGlobalAlpha(1);
	}

	public int getChangeDirectionCooldown() {
		return changeDirectionCooldown;
	}

	public void setChangeDirectionCooldown(int changeDirectionCooldown) {
		this.changeDirectionCooldown = changeDirectionCooldown;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

}
