package entity.blast;

import entity.Player;
import entity.base.Entity;
import entity.base.Interactable;
import entity.base.Updatable;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public abstract class Blast extends Entity implements Updatable {
	public static final int TIME = 30;
	private int timeCount;
	private int form;

	public Blast(double x, double y, int form) {
		super();
		this.timeCount = TIME;
		this.setX(x);
		this.setY(y);
		this.form = form;
	}

	public void update() {
		int xCoord = (int) Math.floor(this.getX() / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.floor(this.getY() / GameMap.PIXELS_PER_BLOCK);

		Player p = GameController.getPlayer();
		if (xCoord == (int) Math.round(p.getX() / GameMap.PIXELS_PER_BLOCK)
				&& yCoord == (int) Math.round(p.getY() / GameMap.PIXELS_PER_BLOCK)
				&& timeCount < TIME) {
			p.interact(this);
		}
		for (Entity entity : GameController.getGameMap().getCellMap()[xCoord][yCoord]
				.getEntities()) {
			if (entity instanceof Interactable) {
				((Interactable) entity).interact(this);

			}
		}
		if (this.timeCount < 1) {
			this.isDestroyed = true;

		}
		this.timeCount -= 1;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.BLAST;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

		if (form == 0) {
			gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
		} else if (form == 1) {
			gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ()], x, y);
		} else if (form == 2) {
			gc.drawImage(RenderableHolder.allSpriteImage[2][this.getZ()], x, y);
		}
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

}
