package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	public static Image gameBackground;
	public static Image shopBackground;
	public static Image woodSign;
	public static Image woodenBoardImage;
	public static Image levelButtonImage;
	public static Image tickImage;
	public static Image lockImage;
	public static Image spriteImage;
	public static Image logo;
	public static WritableImage[][] allSpriteImage;
	public static AudioClip explosionSound;
	public static AudioClip flameThrowerSound;
	public static AudioClip bombEatingSound;
	public static AudioClip hurtSound;
	public static AudioClip enterShopSound;
	public static AudioClip buttonClickedSound;
	public static AudioClip buyingSound;
	public static AudioClip landmineSound;
	public static AudioClip rocketLauncherSound;
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	static {
		loadResource();
	}

	public static void loadResource() {
		allSpriteImage = new WritableImage[4][24];
		spriteImage = new Image(
				ClassLoader.getSystemResource("images/sprite.png").toString());
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 24; j++) {
				allSpriteImage[i][j] = new WritableImage(
						RenderableHolder.spriteImage.getPixelReader(), i * 30, j * 30, 30,
						30);
			}
		}

		logo = new Image(ClassLoader.getSystemResource("images/gui/logo.png").toString());
		lockImage = new Image(
				ClassLoader.getSystemResource("images/gui/lock.png").toString());
		tickImage = new Image(
				ClassLoader.getSystemResource("images/gui/tick.png").toString());
		levelButtonImage = new Image(
				ClassLoader.getSystemResource("images/gui/level-button.png").toString());
		woodenBoardImage = new Image(
				ClassLoader.getSystemResource("images/gui/wooden-button.png").toString());
		woodSign = new Image(
				ClassLoader.getSystemResource("images/gui/wooden-sign.png").toString());
		gameBackground = new Image(ClassLoader
				.getSystemResource("images/gui/brick-background.png").toString());
		shopBackground = new Image(ClassLoader
				.getSystemResource("images/gui/wooden-background.png").toString());

		explosionSound = new AudioClip(
				ClassLoader.getSystemResource("audio/Explosion.wav").toString());
		flameThrowerSound = new AudioClip(ClassLoader
				.getSystemResource("audio/flame_thrower_sound.wav").toString());
		bombEatingSound = new AudioClip(
				ClassLoader.getSystemResource("audio/bomb-eating.wav").toString());
		hurtSound = new AudioClip(
				ClassLoader.getSystemResource("audio/hurt.wav").toString());
		enterShopSound = new AudioClip(
				ClassLoader.getSystemResource("audio/shop-enter.wav").toString());
		buttonClickedSound = new AudioClip(
				ClassLoader.getSystemResource("audio/button.wav").toString());
		buyingSound = new AudioClip(
				ClassLoader.getSystemResource("audio/buying.wav").toString());
		buyingSound.setVolume(0.2);
		landmineSound = new AudioClip(
				ClassLoader.getSystemResource("audio/landmine.wav").toString());
		landmineSound.setVolume(0.3);
		rocketLauncherSound = new AudioClip(
				ClassLoader.getSystemResource("audio/rocketlauncher.wav").toString());
		rocketLauncherSound.setVolume(0.5);

	}

	public void add(IRenderable entity) {

		entities.add(entity);
		Collections.sort(entities, comparator);

	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public void setEntities(List<IRenderable> entities) {
		this.entities = entities;
	}

	public Comparator<IRenderable> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<IRenderable> comparator) {
		this.comparator = comparator;
	}

	public void update() {

		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}

		}
	}
}
