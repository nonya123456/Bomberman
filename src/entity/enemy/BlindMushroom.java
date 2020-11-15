package entity.enemy;

import logic.Direction;
import logic.Sprites;

public class BlindMushroom extends Enemy {
	public static final int HP = 2;

	public BlindMushroom(double x, double y, Direction d) {
		super(x, y, HP, d);
		this.setSpeed(1.5);

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.BLINDMUSHROOM;
	}
}
