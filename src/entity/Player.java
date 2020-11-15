package entity;

import entity.base.Entity;
import entity.base.Interactable;
import entity.base.Updatable;
import entity.blast.Blast;
import entity.blast.Fire;
import entity.bomb.BombFromPlayer;
import entity.enemy.Enemy;
import entity.item.weapon.FlameThrower;
import entity.item.weapon.LandMine;
import entity.item.weapon.RocketLauncher;
import entity.item.weapon.Weapon;
import exception.InvalidBombPlantingException;
import exception.InvalidLandMinePlantingException;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class Player extends Entity implements Updatable, Interactable {

	private Weapon weapon;
	private double maxSpeed;
	private int bombPower;
	private int maxBomb;
	private boolean isGlovesEquipped;
	private static final int IMMORTAL_TIME = 120;
	private int timeCount;

	public Player(int[] baseStats, String startingWeapon) {
		super();
		this.setDirection(Direction.DOWN);
		this.setX(30);
		this.setY(30);
		this.setGlovesEquipped(baseStats[0] == 1);
		this.setMaxSpeed(baseStats[1] * 0.1 + 1.5);
		this.bombPower = baseStats[2] + 1;
		this.maxBomb = baseStats[3] + 1;
		this.timeCount = 0;
		this.isImmortal = true;

		if (!startingWeapon.isBlank()) {
			switch (startingWeapon) {
			case "LandMine":
				setWeapon(new LandMine(x, y));
				break;
			case "RocketLauncher":
				setWeapon(new RocketLauncher(x, y));
				break;
			case "FlameThrower":
				setWeapon(new FlameThrower(x, y));
				break;
			}
		}

	}

	public void action()
			throws InvalidBombPlantingException, InvalidLandMinePlantingException {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		BombFromPlayer bomb;

		if (this.weapon == null) {
			if (GameController.getNumOfBombs() >= maxBomb) {
				throw new InvalidBombPlantingException(
						"You can't place more than " + maxBomb + " bombs.");
			}
			if (GameController.getGameMap().getCellMap()[xCoord][yCoord].getEntities()
					.size() != 0) {
				throw new InvalidBombPlantingException("You can't place the bomb here.");
			}
			bomb = new BombFromPlayer(xCoord * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, this.bombPower);

			GameController.getAddedEntity().add(bomb);

		} else {
			this.weapon.fire(this);
			this.setWeapon(null);
		}

	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void move() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		Entity entity = null;

		if (this.isMovePossible(this.getDirection())) {
			super.move();
			if (this.direction == Direction.LEFT && this.speed > 0)
				this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
			if (this.direction == Direction.RIGHT && this.speed > 0)
				this.setY(yCoord * GameMap.PIXELS_PER_BLOCK);
			if (this.direction == Direction.UP && this.speed > 0)
				this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
			if (this.direction == Direction.DOWN && this.speed > 0)
				this.setX(xCoord * GameMap.PIXELS_PER_BLOCK);
		} else {
			if (this.getDirection() == Direction.UP
					&& GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
							.isBlockable()
					&& y % GameMap.PIXELS_PER_BLOCK == 0) {
				entity = GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
						.getEntity(0);

			}
			if (this.getDirection() == Direction.DOWN
					&& GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
							.isBlockable()
					&& y % GameMap.PIXELS_PER_BLOCK == 0) {

				entity = GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
						.getEntity(0);
			}
			if (this.getDirection() == Direction.LEFT
					&& GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
							.isBlockable()
					&& x % GameMap.PIXELS_PER_BLOCK == 0) {

				entity = GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
						.getEntity(0);
			}
			if (this.getDirection() == Direction.RIGHT
					&& GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
							.isBlockable()
					&& x % GameMap.PIXELS_PER_BLOCK == 0) {

				entity = GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
						.getEntity(0);
			}
			if (entity != null && entity instanceof Interactable) {
				((Interactable) entity).interact(this);
			}

		}

	}

	public int getBombPower() {
		return bombPower;
	}

	public void setBombPower(int bombPower) {
		this.bombPower = bombPower;
	}

	public int getMaxBomb() {
		return maxBomb;
	}

	public void setMaxBomb(int maxBomb) {
		this.maxBomb = maxBomb;
	}

	public boolean isGlovesEquipped() {
		return isGlovesEquipped;
	}

	public void setGlovesEquipped(boolean isGlovesEquipped) {
		this.isGlovesEquipped = isGlovesEquipped;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.PLAYER;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double opacity;

		if (this.isImmortal) {
			opacity = Math.abs(30 - timeCount % 60) / 30.0;
			gc.setGlobalAlpha(opacity);
		}

		if (this.getDirection() == Direction.RIGHT) {
			if (this.speed == 0) {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 2], x, y);
			} else if (GameController.getTimeCount() % 60 < 15) {
				gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ() + 2], x, y);
			} else if (GameController.getTimeCount() % 60 < 30) {
				gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ() + 2], x, y);
			} else if (GameController.getTimeCount() % 60 < 45) {
				gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ() + 2], x, y);
			} else {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 2], x, y);
			}
		} else if (this.getDirection() == Direction.LEFT) {
			if (this.speed == 0) {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 1], x, y);
			} else if (GameController.getTimeCount() % 60 < 15) {
				gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ() + 1], x, y);
			} else if (GameController.getTimeCount() % 60 < 30) {
				gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ() + 1], x, y);
			} else if (GameController.getTimeCount() % 60 < 45) {
				gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ() + 1], x, y);
			} else {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 1], x, y);
			}
		} else if (this.getDirection() == Direction.UP) {
			if (this.speed == 0) {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 3], x, y);
			} else if (GameController.getTimeCount() % 60 < 15) {
				gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ() + 3], x, y);
			} else if (GameController.getTimeCount() % 60 < 30) {
				gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ() + 3], x, y);
			} else if (GameController.getTimeCount() % 60 < 45) {
				gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ() + 3], x, y);
			} else {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ() + 3], x, y);
			}
		} else {
			if (this.speed == 0) {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
			} else if (GameController.getTimeCount() % 60 < 15) {
				gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ()], x, y);
			} else if (GameController.getTimeCount() % 60 < 30) {
				gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ()], x, y);
			} else if (GameController.getTimeCount() % 60 < 45) {
				gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ()], x, y);
			} else {
				gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
			}
		}
		gc.setGlobalAlpha(1);
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public void update()
			throws InvalidBombPlantingException, InvalidLandMinePlantingException {
		// TODO Auto-generated method stub
		boolean isMoving = false;
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		Enemy enemy;
		Door door;

		if (this.isImmortal) {
			this.timeCount += 1;
		}
		if (this.timeCount == IMMORTAL_TIME) {
			this.setImmortal(false);
			this.timeCount = 0;
		}
		this.move();

		for (KeyCode keyCode : InputUtility.getKeyPressed()) {
			if (keyCode == KeyCode.A || keyCode == KeyCode.S || keyCode == KeyCode.W
					|| keyCode == KeyCode.D) {
				isMoving = true;
				switch (keyCode) {
				case A:
					GameController.getPlayer().setDirection(Direction.LEFT);
					GameController.getPlayer()
							.setSpeed(GameController.getPlayer().getMaxSpeed());
					break;
				case D:
					GameController.getPlayer().setDirection(Direction.RIGHT);
					GameController.getPlayer()
							.setSpeed(GameController.getPlayer().getMaxSpeed());
					break;
				case W:
					GameController.getPlayer().setDirection(Direction.UP);
					GameController.getPlayer()
							.setSpeed(GameController.getPlayer().getMaxSpeed());
					break;
				case S:
					GameController.getPlayer().setDirection(Direction.DOWN);
					GameController.getPlayer()
							.setSpeed(GameController.getPlayer().getMaxSpeed());
					break;
				default:
					break;
				}
				break;
			}
		}
		if (!isMoving) {
			GameController.getPlayer().setSpeed(0);
		}
		if (InputUtility.isSpaceTriggered()) {

			GameController.getPlayer().action();

		}
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].hasEnemy()) {
			this.interact(GameController.getGameMap().getCellMap()[xCoord][yCoord]
					.getEntity(0));

		}
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord + 1].hasEnemy()) {
			enemy = (Enemy) GameController.getGameMap().getCellMap()[xCoord][yCoord + 1]
					.getEnemy();
			if (Math.abs(enemy.getY() - this.getY()) < this.speed + enemy.getSpeed()) {
				this.interact(enemy);

			}
		}
		if (GameController.getGameMap().getCellMap()[xCoord][yCoord - 1].hasEnemy()) {
			enemy = (Enemy) GameController.getGameMap().getCellMap()[xCoord][yCoord - 1]
					.getEnemy();
			if (Math.abs(enemy.getY() - this.getY()) < this.speed + enemy.getSpeed()) {
				this.interact(enemy);

			}
		}
		if (GameController.getGameMap().getCellMap()[xCoord - 1][yCoord].hasEnemy()) {
			enemy = (Enemy) GameController.getGameMap().getCellMap()[xCoord - 1][yCoord]
					.getEnemy();
			if (Math.abs(enemy.getX() - this.getX()) < this.speed + enemy.getSpeed()) {
				this.interact(enemy);

			}
		}
		if (GameController.getGameMap().getCellMap()[xCoord + 1][yCoord].hasEnemy()) {
			enemy = (Enemy) GameController.getGameMap().getCellMap()[xCoord + 1][yCoord]
					.getEnemy();
			if (Math.abs(enemy.getX() - this.getX()) < this.speed + enemy.getSpeed()) {
				this.interact(enemy);

			}
		}

		if (GameController.getDoor() != null) {
			door = GameController.getDoor();
			if (xCoord == (int) Math.round(door.getX() / GameMap.PIXELS_PER_BLOCK)
					&& yCoord == (int) Math
							.round(door.getY() / GameMap.PIXELS_PER_BLOCK)) {
				door.interact(this);
			}
		}
	}

	@Override
	public void interact(Entity e) {
		// TODO Auto-generated method stub
		if ((e instanceof Blast || e instanceof Enemy) && !isImmortal
				&& !(e instanceof Fire)) {
			RenderableHolder.hurtSound.play();
			GameController.setOver(true);
		}
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

}
