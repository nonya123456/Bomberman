package entity.blast;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Fire extends Blast {

	public Fire(double x, double y) {
		super(x, y, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.allSpriteImage[3][this.getZ()], x, y);
	}
}
