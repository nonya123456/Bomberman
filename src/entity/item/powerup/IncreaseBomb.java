package entity.item.powerup;

import entity.Player;
import logic.GameController;
import logic.Sprites;

public class IncreaseBomb extends PowerUp {

	public IncreaseBomb(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enhance(Player p) {
		// TODO Auto-generated method stub
		if (GameController.getCurrentStats()[3] < 10) {
			p.setMaxBomb(p.getMaxBomb() + 1);
			GameController.getCurrentStats()[3] += 1;
		}

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.INCREASE_BOMB;
	}

}
