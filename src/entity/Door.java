package entity;

import entity.base.Entity;
import entity.base.Interactable;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;
import logic.Sprites;
import sharedObject.RenderableHolder;

public class Door extends Entity implements Interactable {

	public Door(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.DOOR;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (GameController.getAllEnemy().size() == 0) {
			gc.drawImage(RenderableHolder.allSpriteImage[1][this.getZ()], x, y);
		} else {
			gc.drawImage(RenderableHolder.allSpriteImage[0][this.getZ()], x, y);
		}
	}

	@Override
	public void interact(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof Player && GameController.getAllEnemy().size() == 0) {
			GameController.setOver(true);
			GameController.setWin(true);
		}
	}

}
