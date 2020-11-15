package entity.enemy;

import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;

public class Mushroom extends Enemy {
	public static final int HP = 2;

	public Mushroom(double x, double y, Direction d) {
		super(x, y, HP, d);
		this.setSpeed(1);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.MUSHROOM;
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

}
