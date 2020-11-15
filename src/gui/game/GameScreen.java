package gui.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {

	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);

	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();

		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (!entity.isDestroyed() && entity.isVisible()) {
				entity.draw(gc);
			}

		}
	}

}
