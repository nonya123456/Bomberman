package entity.item.powerup;

import entity.Player;
import logic.GameController;
import logic.Sprites;

public class Gloves extends PowerUp {

	public Gloves(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enhance(Player p) {
		// TODO Auto-generated method stub
		if (!p.isGlovesEquipped()) {
			p.setGlovesEquipped(true);
			GameController.getCurrentStats()[0] += 1;
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.GLOVES;
	}

}
