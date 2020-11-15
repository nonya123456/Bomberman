package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Door;
import entity.PlantedLandMine;
import entity.Player;
import entity.base.Blockable;
import entity.base.Entity;
import entity.base.Updatable;
import entity.bomb.Bomb;
import entity.bomb.BombFromEnemy;
import entity.bomb.Rocket;
import entity.enemy.Enemy;
import entity.item.Item;
import entity.item.weapon.FlameThrower;
import entity.item.weapon.LandMine;
import entity.item.weapon.RocketLauncher;
import exception.InvalidBombPlantingException;
import exception.InvalidLandMinePlantingException;
import exception.ShopException;
import gui.shop.ShopRoot;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class GameController {
	private static boolean isPaused;
	private static Player player;
	private static ArrayList<Entity> addedEntity = new ArrayList<Entity>();
	private static ArrayList<Entity> allEntity = new ArrayList<Entity>();
	private static ArrayList<Bomb> allBomb = new ArrayList<Bomb>();
	private static ArrayList<Enemy> allEnemy = new ArrayList<Enemy>();
	private static GameMap gameMap;
	private static int numOfBombs;
	private static int score;
	private static int timeCount;
	private static boolean isOver;
	private static boolean isWin;
	private static Door door;
	private static int[] baseStats = { 0, 0, 0, 0 }; // Gloves, speed, power, bomb
	private static int[] currentStats = new int[4];
	private static String startingWeapon = "";
	private static int coin = 0;
	private static int lastUnlockedLevel = 1;
	private static Comparator<Entity> comparator = (Entity e1, Entity e2) -> {
		if (e1.getZ() > e2.getZ())
			return 1;
		return -1;
	};

	public static void initialize(GameMap newGameMap) {
		GameController.clear();
		player = new Player(baseStats, startingWeapon);
		currentStats = baseStats.clone();
		gameMap = newGameMap;
		GameController.addEntity(player);
		GameController.addAllEntity(gameMap.getEntities());
		GameController.updateGameMap();
		timeCount = 0;
		isOver = false;
		isWin = false;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		GameController.player = player;
	}

	public static ArrayList<Bomb> getAllBomb() {
		return allBomb;
	}

	public static void setAllBomb(ArrayList<Bomb> allBomb) {
		GameController.allBomb = allBomb;
	}

	public static ArrayList<Entity> getAllEntity() {
		return allEntity;
	}

	public static void setAllEntity(ArrayList<Entity> allEntity) {
		GameController.allEntity = allEntity;
	}

	public static Door getDoor() {
		return door;
	}

	public static void setDoor(Door door) {
		GameController.door = door;
	}

	public static GameMap getGameMap() {
		return gameMap;
	}

	public static boolean isWin() {
		return isWin;
	}

	public static void setWin(boolean isWin) {
		GameController.isWin = isWin;
	}

	public static void setGameMap(GameMap gameMap) {
		GameController.gameMap = gameMap;
	}

	public static void updateGameMap() {
		GameController.gameMap.clear();
		for (int i = allEntity.size() - 1; i >= 0; i--) {
			Entity e = allEntity.get(i);
			int xCoord = (int) Math.round(e.getX() / GameMap.PIXELS_PER_BLOCK);
			int yCoord = (int) Math.round(e.getY() / GameMap.PIXELS_PER_BLOCK);
			if (e instanceof Blockable || e instanceof Enemy || e instanceof Item
					|| e instanceof PlantedLandMine || e instanceof Door) {
				GameController.getGameMap().getCellMap()[xCoord][yCoord].addEntity(e);
			}
		}
	}

	public static void updateMap()
			throws InvalidBombPlantingException, InvalidLandMinePlantingException {

		if (!isPaused) {
			if (timeCount == 600) {
				timeCount = 0;
			} else {
				timeCount += 1;
			}

			for (int i = allEntity.size() - 1; i >= 0; i--) {
				if (allEntity.get(i).isDestroyed()) {
					allEntity.remove(i);
				}
			}

			for (int i = allEntity.size() - 1; i >= 0; i--) {
				Entity e = allEntity.get(i);
				if (e instanceof Updatable) {
					((Updatable) e).update();
				}
			}

			GameController.updateGameMap();

			GameController.addAllEntity(addedEntity);
			addedEntity.clear();

			for (int i = allBomb.size() - 1; i >= 0; i--) {
				if (allBomb.get(i).isDestroyed()) {
					allBomb.remove(i);
				}
			}
			for (int i = allEnemy.size() - 1; i >= 0; i--) {
				if (allEnemy.get(i).isDestroyed()) {
					allEnemy.remove(i);
				}
			}

			int totalBomb = 0;
			for (Bomb bomb : allBomb) {
				if (!(bomb instanceof BombFromEnemy) && !(bomb instanceof Rocket)) {
					totalBomb += 1;
				}
			}
			GameController.setNumOfBombs(totalBomb);
		}

	}

	public static void addEntity(Entity e) {
		RenderableHolder.getInstance().add(e);
		GameController.allEntity.add(e);
		if (e instanceof Bomb) {
			GameController.allBomb.add((Bomb) e);
		} else if (e instanceof Enemy) {
			GameController.allEnemy.add((Enemy) e);
		}

		Collections.sort(allEntity, comparator);

	}

	public static void addAllEntity(ArrayList<Entity> entities) {
		for (Entity e : entities) {
			GameController.addEntity(e);
		}
	}

	public static int getNumOfBombs() {
		return numOfBombs;
	}

	public static void setNumOfBombs(int numOfBombs) {
		GameController.numOfBombs = numOfBombs;
	}

	public static void clear() {

		score = 0;
		numOfBombs = 0;
		door = null;
		allEntity.clear();
		allBomb.clear();
		allEnemy.clear();
		addedEntity.clear();
		RenderableHolder.getInstance().getEntities().clear();
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameController.score = score;
	}

	public static int getTimeCount() {
		return timeCount;
	}

	public static void setTimeCount(int timeCount) {
		GameController.timeCount = timeCount;
	}

	public static boolean isOver() {
		return isOver;
	}

	public static void setOver(boolean isOver) {
		GameController.isOver = isOver;
	}

	public static boolean isPaused() {
		return isPaused;
	}

	public static void setPaused(boolean isPaused) {
		GameController.isPaused = isPaused;
	}

	public static int[] getBaseStats() {
		return baseStats;
	}

	public static void setBaseStats(int[] baseStats) {
		GameController.baseStats = baseStats;
	}

	public static int getCoin() {
		return coin;
	}

	public static void setCoin(int coin) {
		GameController.coin = coin;
	}

	public static String getStartingWeapon() {
		return startingWeapon;
	}

	public static void setStartingWeapon(String startingWeapon) {
		GameController.startingWeapon = startingWeapon;
	}

	public static ArrayList<Entity> getAddedEntity() {
		return addedEntity;
	}

	public static void setAddedEntity(ArrayList<Entity> addedEntity) {
		GameController.addedEntity = addedEntity;
	}

	public static ArrayList<Enemy> getAllEnemy() {
		return allEnemy;
	}

	public static void setAllEnemy(ArrayList<Enemy> allEnemy) {
		GameController.allEnemy = allEnemy;
	}

	public static int[] getCurrentStats() {
		return currentStats;
	}

	public static void setCurrentStats(int[] currentStats) {
		GameController.currentStats = currentStats;
	}

	public static int getLastUnlockedLevel() {
		return lastUnlockedLevel;
	}

	public static void setLastUnlockedLevel(int lastUnlockedLevel) {
		GameController.lastUnlockedLevel = lastUnlockedLevel;
	}

	public static void buyWeapon(String weaponName, int price) throws ShopException {
		if (GameController.getStartingWeapon().equals(weaponName)) {
			throw new ShopException("You already own this weapon.");

		} else if (GameController.getCoin() >= price) {
			GameController.setCoin(GameController.getCoin() - price);
			ShopRoot.getCoinLabel().setText("Coin: " + GameController.getCoin());
			RenderableHolder.buyingSound.stop();
			RenderableHolder.buyingSound.play();
			if (weaponName.equals("LandMine")) {
				ShopRoot.getYourWeapon()
						.setImage(RenderableHolder.allSpriteImage[0][Sprites.LANDMINE]);
			} else if (weaponName.equals("RocketLauncher")) {
				ShopRoot.getYourWeapon().setImage(
						RenderableHolder.allSpriteImage[0][Sprites.ROCKETLAUNCHER]);
			} else if (weaponName.equals("FlameThrower")) {
				ShopRoot.getYourWeapon().setImage(
						RenderableHolder.allSpriteImage[0][Sprites.FLAMETHROWER]);
			}
			GameController.setStartingWeapon(weaponName);

		} else {
			throw new ShopException("Not Enough Coin.");
		}
	}

	public static boolean buyPowerUp(int index, int price, int maxAmount)
			throws ShopException {
		// return true if max
		if (GameController.getBaseStats()[index] < maxAmount) {
			if (GameController.getCoin() < price) {
				throw new ShopException("Not Enough Coin.");
			} else {
				RenderableHolder.buyingSound.stop();
				RenderableHolder.buyingSound.play();
				GameController.getBaseStats()[index] += 1;
				GameController.setCoin(GameController.getCoin() - price);
			}
		}
		if (GameController.getBaseStats()[index] == maxAmount) {
			return true;
		}
		return false;
	}

	public static void handleGameOver() {
		if (GameController.isWin()) {
			GameController.setCoin(GameController.getCoin() + GameController.getScore());
			if (GameController.getPlayer().getWeapon() instanceof RocketLauncher) {
				GameController.setStartingWeapon("RocketLauncher");
			} else if (GameController.getPlayer().getWeapon() instanceof LandMine) {
				GameController.setStartingWeapon("LandMine");
			} else if (GameController.getPlayer().getWeapon() instanceof FlameThrower) {
				GameController.setStartingWeapon("FlameThrower");
			} else {
				GameController.setStartingWeapon("");
			}

			if (GameController.getGameMap().getIndex() == GameController
					.getLastUnlockedLevel()) {
				GameController
						.setLastUnlockedLevel(GameController.getLastUnlockedLevel() + 1);
			}
		} else {
			GameController.setStartingWeapon("");
		}
	}

}
