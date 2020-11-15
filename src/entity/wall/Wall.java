package entity.wall;

import entity.base.Blockable;
import entity.base.Entity;
import logic.Sprites;

public class Wall extends Entity implements Blockable {
	public Wall(double x, double y) {
		super();
		this.setX(x);
		this.setY(y);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.WALL;
	}

}