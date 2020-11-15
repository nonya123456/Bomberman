package entity.item.powerup;

import entity.Player;
import logic.GameController;
import logic.Sprites;

public class IncreasePower extends PowerUp {

	public IncreasePower(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enhance(Player p) {
		// TODO Auto-generated method stub
		if (GameController.getCurrentStats()[2] < 10) {
			p.setBombPower(p.getBombPower() + 1);
			GameController.getCurrentStats()[2] += 1;
		}

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.INCREASE_POWER;
	}

}
