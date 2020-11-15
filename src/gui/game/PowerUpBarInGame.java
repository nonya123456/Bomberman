package gui.game;

import gui.ItemImage;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Sprites;
import sharedObject.RenderableHolder;

public class PowerUpBarInGame extends HBox {
	private ItemImage image;
	private ProgressBar progressBar;
	private int index;
	private int maxAmount;

	public PowerUpBarInGame(int index, int maxAmount) {
		super(5);
		this.setAlignment(Pos.CENTER);
		this.index = index;
		this.maxAmount = maxAmount;
		switch (index) {
		case 0:
			image = new ItemImage(25, RenderableHolder.allSpriteImage[0][Sprites.GLOVES],
					Color.WHITE);
			break;
		case 1:
			image = new ItemImage(25,
					RenderableHolder.allSpriteImage[0][Sprites.INCREASE_SPEED],
					Color.WHITE);
			break;
		case 2:
			image = new ItemImage(25,
					RenderableHolder.allSpriteImage[0][Sprites.INCREASE_POWER],
					Color.WHITE);
			break;
		case 3:
			image = new ItemImage(25,
					RenderableHolder.allSpriteImage[0][Sprites.INCREASE_BOMB],
					Color.WHITE);
		}

		progressBar = new ProgressBar();
		progressBar.setProgress((1.0 * GameController.getBaseStats()[index]) / maxAmount);
		progressBar.setPrefSize(150, 25);
		progressBar.setStyle("-fx-accent: green; -fx-box-border: goldenrod;");

		this.getChildren().addAll(image, progressBar);
	}

	public void update() {
		progressBar
				.setProgress((1.0 * GameController.getCurrentStats()[index]) / maxAmount);
	}
}
