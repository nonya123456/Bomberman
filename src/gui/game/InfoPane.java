package gui.game;

import entity.item.weapon.FlameThrower;
import entity.item.weapon.LandMine;
import entity.item.weapon.RocketLauncher;
import gui.ItemImage;
import gui.mainmenu.MainMenuScene;
import input.InputUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Sprites;
import main.Main;
import sharedObject.RenderableHolder;

public class InfoPane extends VBox {
	private HBox currentWeapon;
	private Label weaponLabel;
	private ItemImage weaponImage;
	private Label scoreLabel;
	private Label enemyLabel;
	private Label numOfBombs;
	private Button exitButton;
	private Button pauseButton;
	private Button playButton;
	private PowerUpBarInGame hasGlovesBar;
	private PowerUpBarInGame powerBar;
	private PowerUpBarInGame speedBar;
	private PowerUpBarInGame maxBombsBar;
	private HBox controlButtons;

	public InfoPane() {
		super(5);

		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(200);

		this.setBackground(
				new Background(new BackgroundImage(RenderableHolder.gameBackground,
						BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		hasGlovesBar = new PowerUpBarInGame(0, 1);
		speedBar = new PowerUpBarInGame(1, 10);
		powerBar = new PowerUpBarInGame(2, 10);
		maxBombsBar = new PowerUpBarInGame(3, 10);

		currentWeapon = new HBox(5);
		weaponLabel = new Label("Weapon:");
		weaponLabel.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		weaponImage = new ItemImage(30, Color.WHITESMOKE);

		currentWeapon.getChildren().addAll(weaponLabel, weaponImage);
		currentWeapon.setAlignment(Pos.CENTER);

		numOfBombs = new Label();
		numOfBombs.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");

		ImageView scoreSign = new ImageView(RenderableHolder.woodSign);
		scoreSign.setFitHeight(40);
		scoreSign.setFitWidth(175);
		scoreLabel = new Label();
		scoreLabel.setStyle("-fx-font-size: 30px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		StackPane scorePane = new StackPane();
		scorePane.getChildren().addAll(scoreSign, scoreLabel);

		enemyLabel = new Label();
		enemyLabel.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");

		exitButton = new Button("Exit");
		exitButton.setPrefSize(50, 50);
		exitButton.setFocusTraversable(false);
		exitButton.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkred;");

		pauseButton = new Button("Pause");
		pauseButton.setPrefSize(50, 50);
		pauseButton.setFocusTraversable(false);
		pauseButton.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkgreen;");

		playButton = new Button("Play");
		playButton.setPrefSize(50, 50);
		playButton.setFocusTraversable(false);
		playButton.setStyle(
				"-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: \"Arial Narrow\"; -fx-background-color: darkgreen;");

		controlButtons = new HBox(5);
		controlButtons.getChildren().addAll(exitButton, pauseButton);
		controlButtons.setAlignment(Pos.CENTER);
		this.getChildren().addAll(scorePane, numOfBombs, currentWeapon, enemyLabel,
				hasGlovesBar, speedBar, powerBar, maxBombsBar, controlButtons);
		addListener();
	}

	public void updateInfoPane() {
		scoreLabel.setText("Score: " + GameController.getScore());

		numOfBombs.setText("Bomb: " + GameController.getNumOfBombs() + " / "
				+ GameController.getPlayer().getMaxBomb());

		if (GameController.getPlayer().getWeapon() == null) {
			weaponImage.setImage(null);
		} else if (GameController.getPlayer().getWeapon() instanceof LandMine) {
			weaponImage.setImage(RenderableHolder.allSpriteImage[0][Sprites.LANDMINE]);
		} else if (GameController.getPlayer().getWeapon() instanceof RocketLauncher) {
			weaponImage
					.setImage(RenderableHolder.allSpriteImage[0][Sprites.ROCKETLAUNCHER]);
		} else if (GameController.getPlayer().getWeapon() instanceof FlameThrower) {
			weaponImage
					.setImage(RenderableHolder.allSpriteImage[0][Sprites.FLAMETHROWER]);
		}

		enemyLabel.setText("Enemy: " + GameController.getAllEnemy().size());

		hasGlovesBar.update();
		speedBar.update();
		powerBar.update();
		maxBombsBar.update();

	}

	private void addListener() {
		exitButton.setOnMouseClicked(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			if (GameController.isPaused()) {
				GameController.setPaused(false);
			}
			((GameScene) Main.getMainStage().getScene()).getTimer().stop();
			Main.getMainStage().setScene(new MainMenuScene());
			GameController.setStartingWeapon("");
		});

		pauseButton.setOnMouseClicked(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			if (!GameController.isOver()) {
				((GameScene) Main.getMainStage().getScene()).getTimer().stop();
				GameController.setPaused(true);
				controlButtons.getChildren().remove(pauseButton);
				controlButtons.getChildren().add(playButton);
				InputUtility.getKeyPressed().clear();
			}

		});
		playButton.setOnMouseClicked(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			if (!GameController.isOver()) {
				((GameScene) Main.getMainStage().getScene()).getTimer().start();
				GameController.setPaused(false);
				controlButtons.getChildren().remove(playButton);
				controlButtons.getChildren().add(pauseButton);
			}

		});
	}

	public void disableButton() {
		if (GameController.isOver()) {
			exitButton.setDisable(true);
			pauseButton.setDisable(true);
			playButton.setDisable(true);
		}
	}
}
