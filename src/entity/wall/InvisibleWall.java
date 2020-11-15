package entity.wall;

import entity.Player;
import entity.base.Entity;
import entity.base.Interactable;
import javafx.scene.canvas.GraphicsContext;

public class InvisibleWall extends Wall implements Interactable {
	public static final int TIME = 60;
	private int timeCount;

	public InvisibleWall(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		timeCount = 60;
		this.setVisible(false);
	}

	@Override
	public void interact(Entity e) {
		// TODO Auto-generated method stub
		if (e instanceof Player) {
			this.setVisible(true);
			timeCount = 0;
		}

	}

	public void draw(GraphicsContext gc) {
		if (timeCount < 60) {
			double opacity = Math.abs(60 - timeCount) / 60.0;
			gc.setGlobalAlpha(opacity);
			super.draw(gc);
			gc.setGlobalAlpha(1);
			timeCount += 1;
		} else {
			this.setVisible(false);
		}
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

}
