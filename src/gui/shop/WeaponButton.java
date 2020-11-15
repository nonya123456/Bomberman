package gui.shop;

import exception.ShopException;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import logic.GameController;
import logic.Sprites;
import sharedObject.RenderableHolder;

public class WeaponButton extends Button {
	private String weaponName;
	private int price;
	private ImageView imageView;

	public WeaponButton(String name) {
		super();
		this.weaponName = name;
		imageView = null;
		switch (name) {
		case "LandMine": {
			price = 300;
			imageView = new ImageView(
					RenderableHolder.allSpriteImage[0][Sprites.LANDMINE]);
			break;
		}
		case "RocketLauncher": {
			price = 600;
			imageView = new ImageView(
					RenderableHolder.allSpriteImage[0][Sprites.ROCKETLAUNCHER]);
			break;
		}
		case "FlameThrower": {
			price = 600;
			imageView = new ImageView(
					RenderableHolder.allSpriteImage[0][Sprites.FLAMETHROWER]);
			break;
		}
		}

		imageView.setFitHeight(48);
		imageView.setFitWidth(48);
		this.setBackground(new Background(
				new BackgroundFill(Color.SADDLEBROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setGraphic(imageView);
		this.setFocusTraversable(false);
		addListener();
		setTooltip();
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	private void addListener() {
		this.setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			try {
				GameController.buyWeapon(weaponName, price);
			} catch (ShopException e1) {
				// TODO Auto-generated catch block
				ShopRoot.getErrorMessage().setMessage(e1.getMessage());
			}
		});

	}

	private void setTooltip() {
		Tooltip tooltip = new Tooltip();

		tooltip.setText(this.weaponName + "\n" + "Price: " + this.price);
		tooltip.setTextAlignment(TextAlignment.CENTER);
		tooltip.setStyle("-fx-background-color:#CC9900; " + "-fx-font-weight:normal; "
				+ "-fx-font-size:15px; " + "-fx-position:absolute; "
				+ "-fx-text-fill: white; " + "-fx-box-sizing:border-box;");

		this.setOnMouseMoved((MouseEvent e) -> {
			tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}
}
