package entity.enemy;

import entity.bomb.Bomb;
import entity.bomb.BombFromEnemy;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;

public class BomberBot extends Enemy {
	public static final int HP = 5;
	public static final int BOMB_POWER = 3;
	private static final int COOLDOWN = 500;
	private boolean isBombPlanted;

	public BomberBot(double x, double y, Direction d) {
		super(x, y, HP, d);
		this.isBombPlanted = false;
		this.timeCount = 0;
		this.speed = 1;
		// TODO Auto-generated constructor stub
	}

	private void action() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);

		if (GameController.getGameMap().getCellMap()[xCoord][yCoord].getEntities().size() != 0) {
			// generate bomb
			Bomb bomb = new BombFromEnemy(xCoord * GameMap.PIXELS_PER_BLOCK,
					yCoord * GameMap.PIXELS_PER_BLOCK, BOMB_POWER);
			GameController.getAddedEntity().add(bomb);
			this.isBombPlanted = true;
		}
	}

	public void update() {
		super.update();
		if (this.isBombPlanted) {
			this.timeCount += 1;
			if (this.timeCount >= COOLDOWN) {
				this.isBombPlanted = false;
				this.timeCount = 0;
			}
		} else if (Math.random() < 0.1) {
			this.action();
		}

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.BOMBER_BOT;
	}

	public boolean isBombPlanted() {
		return isBombPlanted;
	}

	public void setBombPlanted(boolean isBombPlanted) {
		this.isBombPlanted = isBombPlanted;
	}

}
