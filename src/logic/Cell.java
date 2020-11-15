package logic;

import java.util.ArrayList;

import entity.PlantedLandMine;
import entity.base.Blockable;
import entity.base.Entity;
import entity.bomb.Bomb;
import entity.bomb.BombFromEnemy;
import entity.enemy.Enemy;
import entity.wall.BreakableWall;
import entity.wall.Wall;

public class Cell {
	private ArrayList<Entity> entities;

	public Cell() {
		this.entities = new ArrayList<Entity>();
	}

	public void clear() {
		entities.clear();
	}

	public Entity getEntity(int index) {
		return entities.get(index);
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public boolean isBlockable() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(0) instanceof Blockable;

	}

	public boolean hasBomb() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(0) instanceof Bomb;
	}

	public boolean hasWall() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(0) instanceof Wall;
	}

	public boolean hasUnbreakableWall() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(0) instanceof Wall
				&& !(entities.get(0) instanceof BreakableWall);
	}

	public boolean hasEnemy() {
		for (Entity e : entities) {
			if (e instanceof Enemy && e.isVisible() && !e.isDestroyed()) {
				return true;
			}
		}
		return false;
	}

	public boolean hasLandMine() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(entities.size() - 1) instanceof PlantedLandMine;
	}

	public boolean hasBombFromEnemy() {
		if (entities.size() == 0) {
			return false;
		}
		return entities.get(0) instanceof BombFromEnemy;
	}

	public Enemy getEnemy() {
		if (hasEnemy()) {
			for (Entity e : entities) {
				if (e instanceof Enemy) {
					return (Enemy) e;
				}
			}
		}

		return null;
	}
}
