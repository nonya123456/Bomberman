package entity.item.weapon;

import entity.Player;
import entity.bomb.Rocket;
import logic.GameController;
import logic.Sprites;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class RocketLauncher extends Weapon {

	public RocketLauncher(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void fire(Player p) {
		int xCoord = (int) Math.round(p.getX() / GameMap.PIXELS_PER_BLOCK);
		int yCoord = (int) Math.round(p.getY() / GameMap.PIXELS_PER_BLOCK);

		// TODO Auto-generated method stub
		GameController.getAddedEntity().add(new Rocket(xCoord * GameMap.PIXELS_PER_BLOCK,
				yCoord * GameMap.PIXELS_PER_BLOCK, p.getDirection()));
		RenderableHolder.rocketLauncherSound.stop();
		RenderableHolder.rocketLauncherSound.play();
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Sprites.ROCKETLAUNCHER;
	}

}
