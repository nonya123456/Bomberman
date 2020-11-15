package gui.shop;

import exception.ShopException;
import gui.ItemImage;
import gui.WoodenButton;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import logic.Sprites;
import sharedObject.RenderableHolder;

public class PowerUpBar extends HBox {

	private ItemImage itemImage;
	private ProgressBar progressBar;
	private WoodenButton powerUpButton;
	private int price;

	public PowerUpBar(String name, int index, int maxAmount) {
		super(5);
		price = (GameController.getBaseStats()[index] + 1) * 100;
		itemImage = new ItemImage(30, Color.WHITE);

		switch (index) {
		case 0:
			itemImage.setImage(RenderableHolder.allSpriteImage[0][Sprites.GLOVES]);
			break;
		case 1:
			itemImage.setImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_SPEED]);
			break;
		case 2:
			itemImage.setImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_POWER]);
			break;
		case 3:
			itemImage.setImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_BOMB]);
			break;
		}

		progressBar = new ProgressBar();
		progressBar.setProgress((1.0 * GameController.getBaseStats()[index]) / maxAmount);
		progressBar.setPrefSize(270, 25);
		progressBar.setStyle("-fx-accent: green; -fx-box-border: goldenrod;");

		powerUpButton = new WoodenButton("" + price, 50, 25);
		powerUpButton.getLabel().setFont(new Font(10));
		if (GameController.getBaseStats()[index] == maxAmount) {
			powerUpButton.getLabel().setText("MAX");
			powerUpButton.setDisable(true);
		}
		powerUpButton.setFocusTraversable(false);
		powerUpButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			try {
				if (GameController.buyPowerUp(index, price, maxAmount)) {
					progressBar.setProgress(
							(1.0 * GameController.getBaseStats()[index]) / maxAmount);
					powerUpButton.getLabel().setText("MAX");
					powerUpButton.setDisable(true);
				} else {
					progressBar.setProgress(
							(1.0 * GameController.getBaseStats()[index]) / maxAmount);
					price = (GameController.getBaseStats()[index] + 1) * 100;
					powerUpButton.getLabel().setText("" + price);
				}
				ShopRoot.getCoinLabel().setText("Coin: " + GameController.getCoin());
			} catch (ShopException e1) {
				// TODO Auto-generated catch block
				ShopRoot.getErrorMessage().setMessage(e1.getMessage());
			}

		});
		powerUpButton.setPrefWidth(50);

		this.getChildren().addAll(itemImage, progressBar, powerUpButton);
		this.setPrefSize(385, 25);
		this.setAlignment(Pos.BOTTOM_RIGHT);
	}
}
