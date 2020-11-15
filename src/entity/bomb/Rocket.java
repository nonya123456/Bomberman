package entity.bomb;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class Rocket extends BombFromPlayer {
	public static final int POWER = 1;

	public Rocket(double x, double y, Direction d) {
		super(x, y, POWER);
		// TODO Auto-generated constructor stub
		this.speed = 5;
		this.setDirection(d);
	}

	public void update() {
		int xCoord = (int) Math.round(x / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(y / GameMap.PIXELS_PER_BLOCK);
		this.timeCount -= 1;
		if (this.isMovePossible(this.getDirection())) {
			this.move();
		} else {
			this.timeCount = 0;
		}
		if (this.timeCount < 1) {
			GameController.getGameMap().getCellMap()[xCoord][yCoord].clear();
			this.isDestroyed = true;
			this.explode();
			RenderableHolder.rocketLauncherSound.stop();
		}
	}

	public int getZ() {
		return Sprites.ROCKET;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (this.getDirection() == Direction.UP) {
			gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ()], x, y);
		} else if (this.getDirection() == Direction.DOWN) {
			gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ()], x, y);
		} else if (this.getDirection() == Direction.RIGHT) {
			gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
		} else if (this.getDirection() == Direction.LEFT) {
			gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ()], x, y);
		}

	}
}
