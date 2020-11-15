package logic.gamemap;

import java.util.ArrayList;

import entity.base.Entity;
import entity.enemy.BlindMushroom;
import entity.enemy.BombEater;
import entity.enemy.BomberBot;
import entity.enemy.Mechabomb;
import entity.enemy.Mushroom;
import entity.wall.BreakableWall;
import entity.wall.InvisibleWall;
import entity.wall.Wall;
import exception.InvalidLevelSelectionException;
import logic.Cell;
import logic.Direction;

public class GameMap {
	private int index;
	private Cell[][] cellMap;
	public static final int WIDTH = 19;
	public static final int HEIGHT = 13;
	public static final int PIXELS_PER_BLOCK = 30;
	private ArrayList<Entity> entities;

	public GameMap(int index) throws InvalidLevelSelectionException {
		if (index == 0) {
			throw new InvalidLevelSelectionException();
		}

		this.index = index;
		String fileName = Integer.toString(index);
		if (fileName.length() == 1) {
			fileName = "level/" + "0" + fileName + ".csv";
		} else {
			fileName = "level/" + fileName + ".csv";
		}
		String[][] map = CSVParser.readCSV(fileName);
		cellMap = new Cell[WIDTH][HEIGHT];
		entities = new ArrayList<Entity>();
		ArrayList<Entity> breakableWalls = new ArrayList<Entity>();
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				cellMap[i][j] = new Cell();
				double x = i * PIXELS_PER_BLOCK;
				double y = j * PIXELS_PER_BLOCK;
				if (map[j][i].equals("W")) {
					entities.add(new Wall(x, y));

				} else if (map[j][i].equals("IW")) {
					entities.add(new InvisibleWall(x, y));

				} else if (map[j][i].equals("BW")) {
					BreakableWall breakableWall = new BreakableWall(x, y);
					entities.add(breakableWall);
					breakableWalls.add(breakableWall);

				} else if (map[j][i].charAt(0) == 'A') {
					Direction d = null;
					// 1=UP, 2=DOWN, 3=LEFT, 4=RIGHT
					if (map[j][i].charAt(1) == '1')
						d = Direction.UP;
					else if (map[j][i].charAt(1) == '2')
						d = Direction.DOWN;
					else if (map[j][i].charAt(1) == '3')
						d = Direction.LEFT;
					else if (map[j][i].charAt(1) == '4')
						d = Direction.RIGHT;

					entities.add(new BomberBot(x, y, d));

				} else if (map[j][i].charAt(0) == 'B') {
					Direction d = null;
					if (map[j][i].charAt(1) == '1')
						d = Direction.UP;
					else if (map[j][i].charAt(1) == '2')
						d = Direction.DOWN;
					else if (map[j][i].charAt(1) == '3')
						d = Direction.LEFT;
					else if (map[j][i].charAt(1) == '4')
						d = Direction.RIGHT;

					entities.add(new Mechabomb(x, y, d));

				} else if (map[j][i].charAt(0) == 'C') {
					Direction d = null;
					if (map[j][i].charAt(1) == '1')
						d = Direction.UP;
					else if (map[j][i].charAt(1) == '2')
						d = Direction.DOWN;
					else if (map[j][i].charAt(1) == '3')
						d = Direction.LEFT;
					else if (map[j][i].charAt(1) == '4')
						d = Direction.RIGHT;

					entities.add(new Mushroom(x, y, d));

				} else if (map[j][i].charAt(0) == 'D') {
					Direction d = null;
					if (map[j][i].charAt(1) == '1')
						d = Direction.UP;
					else if (map[j][i].charAt(1) == '2')
						d = Direction.DOWN;
					else if (map[j][i].charAt(1) == '3')
						d = Direction.LEFT;
					else if (map[j][i].charAt(1) == '4')
						d = Direction.RIGHT;

					entities.add(new BlindMushroom(x, y, d));

				} else if (map[j][i].charAt(0) == 'E') {
					Direction d = null;
					if (map[j][i].charAt(1) == '1')
						d = Direction.UP;
					else if (map[j][i].charAt(1) == '2')
						d = Direction.DOWN;
					else if (map[j][i].charAt(1) == '3')
						d = Direction.LEFT;
					else if (map[j][i].charAt(1) == '4')
						d = Direction.RIGHT;

					entities.add(new BombEater(x, y, d));

				}
			}
		}
		int doorIndex = (int) (Math.random() * breakableWalls.size());
		((BreakableWall) breakableWalls.get(doorIndex)).setHasDoor(true);
	}

	public Cell[][] getCellMap() {
		return cellMap;
	}

	public void setCellMap(Cell[][] cellMap) {
		this.cellMap = cellMap;
	}

	public void clear() {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				cellMap[i][j].clear();
			}
		}
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public boolean isBlockable(int axis, int index, int start, int stop) {
		// 0 = X, 1 = Y, return true if there is a blockable entity between start and
		// stop.
		if (axis == 0) {
			for (int i = start; i < stop + 1; i++) {
				if (cellMap[i][index].isBlockable()) {
					return true;
				}
			}
		} else {
			for (int i = start; i < stop + 1; i++) {
				if (cellMap[index][i].isBlockable()) {
					return true;
				}
			}
		}
		return false;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
