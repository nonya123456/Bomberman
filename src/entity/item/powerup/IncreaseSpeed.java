package entity.item.powerup;

import entity.Player;
import logic.GameController;
import logic.Sprites;

public class IncreaseSpeed extends PowerUp {

	public IncreaseSpeed(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enhance(Player p) {
		// TODO Auto-generated method stub
		if (GameController.getCurrentStats()[1] < 10) {
			p.setMaxSpeed(p.getMaxSpeed() + 0.1);
			GameController.getCurrentStats()[1] += 1;
		}

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.INCREASE_SPEED;
	}

}
