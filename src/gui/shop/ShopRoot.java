package gui.shop;

import gui.ErrorMessage;
import gui.ItemImage;
import gui.WoodenButton;
import gui.mainmenu.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import logic.Sprites;
import main.Main;
import sharedObject.RenderableHolder;

public class ShopRoot extends VBox {

	private WoodenButton backToMenu;
	private static Label coinLabel;
	private static ItemImage yourWeapon;
	private static ErrorMessage errorMessage;

	public ShopRoot() {
		super(10);
		this.setBackground(
				new Background(new BackgroundImage(RenderableHolder.shopBackground,
						BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		errorMessage = new ErrorMessage("");

		StackPane powerUpPane = new StackPane();
		ImageView powerUpSign = new ImageView(RenderableHolder.woodSign);
		powerUpSign.setFitHeight(50);
		powerUpSign.setFitWidth(300);
		Label powerUpLabel = new Label("Power-Ups");
		powerUpLabel.setAlignment(Pos.CENTER);
		powerUpLabel.setStyle("-fx-font-size: 25px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		powerUpPane.getChildren().addAll(powerUpSign, powerUpLabel);

		VBox baseStats = new VBox(20);
		baseStats.setPrefSize(385, 200);
		baseStats.setAlignment(Pos.CENTER);
		baseStats.getChildren().add(powerUpPane);
		baseStats.getChildren().add(new PowerUpBar("Gloves", 0, 1));
		baseStats.getChildren().add(new PowerUpBar("Speed", 1, 10));
		baseStats.getChildren().add(new PowerUpBar("Power", 2, 10));
		baseStats.getChildren().add(new PowerUpBar("Bomb", 3, 10));

		StackPane weaponPane = new StackPane();
		ImageView weaponSign = new ImageView(RenderableHolder.woodSign);
		weaponSign.setFitHeight(50);
		weaponSign.setFitWidth(300);
		Label weaponLabel = new Label("Weapons");
		weaponLabel.setFont(new Font(25));
		weaponLabel.setPrefWidth(385);
		weaponLabel.setAlignment(Pos.CENTER);
		weaponLabel.setStyle("-fx-font-size: 25px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		weaponPane.getChildren().addAll(weaponSign, weaponLabel);

		StackPane yourWeaponPane = new StackPane();
		ImageView yourWeaponSign = new ImageView(RenderableHolder.woodSign);
		yourWeaponSign.setFitHeight(50);
		yourWeaponSign.setFitWidth(300);
		HBox yourWeaponHBox = new HBox(5);
		yourWeaponHBox.setAlignment(Pos.CENTER);
		Label yourWeaponLabel = new Label("Your Weapon: ");
		yourWeaponLabel.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		yourWeapon = new ItemImage(40, Color.SADDLEBROWN);

		if (GameController.getStartingWeapon().equals("LandMine")) {
			ShopRoot.getYourWeapon()
					.setImage(RenderableHolder.allSpriteImage[0][Sprites.LANDMINE]);
		} else if (GameController.getStartingWeapon().equals("RocketLauncher")) {
			ShopRoot.getYourWeapon()
					.setImage(RenderableHolder.allSpriteImage[0][Sprites.ROCKETLAUNCHER]);
		} else if (GameController.getStartingWeapon().equals("FlameThrower")) {
			ShopRoot.getYourWeapon()
					.setImage(RenderableHolder.allSpriteImage[0][Sprites.FLAMETHROWER]);
		}

		yourWeaponHBox.getChildren().addAll(yourWeaponLabel, yourWeapon);
		yourWeaponPane.getChildren().addAll(yourWeaponSign, yourWeaponHBox);

		StackPane coinPane = new StackPane();
		ImageView coinSign = new ImageView(RenderableHolder.woodSign);
		coinSign.setFitHeight(50);
		coinSign.setFitWidth(300);
		coinLabel = new Label("Coin: " + GameController.getCoin());
		coinLabel.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		coinPane.getChildren().addAll(coinSign, coinLabel);

		HBox weaponList = new HBox(15);
		weaponList.setPrefSize(385, 48);
		weaponList.setAlignment(Pos.CENTER);
		weaponList.getChildren().addAll(new WeaponButton("LandMine"),
				new WeaponButton("RocketLauncher"), new WeaponButton("FlameThrower"));
		VBox weaponShop = new VBox(15);
		weaponShop.getChildren().add(weaponPane);
		weaponShop.getChildren().add(yourWeaponPane);
		weaponShop.getChildren().add(weaponList);
		weaponShop.getChildren().add(coinPane);
		weaponShop.setPrefSize(385, 200);

		HBox shopHBox = new HBox(15);
		shopHBox.getChildren().addAll(baseStats, weaponShop);

		backToMenu = new WoodenButton("Back To Main Menu", 300, 50);
		backToMenu.getButton().setOnAction((e) -> {
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().setScene(new MainMenuScene());
		});

		this.getChildren().addAll(errorMessage, shopHBox, backToMenu);
		this.setAlignment(Pos.CENTER);
		RenderableHolder.enterShopSound.stop();
		RenderableHolder.enterShopSound.play();
	}

	public static Label getCoinLabel() {
		return coinLabel;
	}

	public static void setCoinLabel(Label coinLabel) {
		ShopRoot.coinLabel = coinLabel;
	}

	public static ItemImage getYourWeapon() {
		return yourWeapon;
	}

	public static void setYourWeapon(ItemImage yourWeapon) {
		ShopRoot.yourWeapon = yourWeapon;
	}

	public static ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(ErrorMessage errorMessage) {
		ShopRoot.errorMessage = errorMessage;
	}

}
