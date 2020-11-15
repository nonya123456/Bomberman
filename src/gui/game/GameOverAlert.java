package gui.game;

import exception.InvalidLevelSelectionException;
import gui.WoodenButton;
import gui.mainmenu.MainMenuScene;
import gui.shop.ShopScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import main.Main;
import sharedObject.RenderableHolder;

public class GameOverAlert extends VBox {
	private WoodenButton shopButton;
	private WoodenButton nextLevelButton;
	private WoodenButton mainMenuButton;
	private WoodenButton restartButton;

	public GameOverAlert(boolean isWin) {
		super(10);
		Label label;
		StackPane labelPane = new StackPane();
		ImageView labelSign = new ImageView(RenderableHolder.woodenBoardImage);
		HBox buttonHBox;

		if (isWin) {
			if (GameController.getGameMap().getIndex() == 10) {
				label = new Label("Congratulation");
			} else {
				label = new Label("You Win");
			}

		} else {
			label = new Label("You Lose");
		}

		labelSign.setFitHeight(50);
		labelSign.setFitWidth(230);

		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-font-size: 25px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");
		labelPane.getChildren().addAll(labelSign, label);

		buttonHBox = new HBox(10);
		shopButton = new WoodenButton("Shop", 70, 50);
		mainMenuButton = new WoodenButton("Menu", 70, 50);
		nextLevelButton = new WoodenButton("Next", 70, 50);
		restartButton = new WoodenButton("Restart", 70, 50);

		buttonHBox.setAlignment(Pos.BOTTOM_CENTER);

		if (isWin) {
			if (GameController.getGameMap().getIndex() == 10) {
				buttonHBox.getChildren().addAll(shopButton, mainMenuButton);
			} else {
				buttonHBox.getChildren().addAll(shopButton, mainMenuButton,
						nextLevelButton);
			}

		} else {
			buttonHBox.getChildren().addAll(shopButton, mainMenuButton, restartButton);
		}

		this.getChildren().addAll(labelPane, buttonHBox);
		this.setAlignment(Pos.CENTER);

		addListener();
	}

	public void addListener() {
		shopButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			((GameScene) Main.getMainStage().getScene()).getTimer().stop();
			Main.getMainStage().setScene(new ShopScene());
		});

		mainMenuButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			((GameScene) Main.getMainStage().getScene()).getTimer().stop();
			Main.getMainStage().setScene(new MainMenuScene());
		});

		nextLevelButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();

			try {
				((GameScene) Main.getMainStage().getScene()).getTimer().stop();
				Main.getMainStage().setScene(
						new GameScene(GameController.getGameMap().getIndex() + 1));
			} catch (InvalidLevelSelectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		restartButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			try {
				((GameScene) Main.getMainStage().getScene()).getTimer().stop();
				Main.getMainStage()
						.setScene(new GameScene(GameController.getGameMap().getIndex()));
			} catch (InvalidLevelSelectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
