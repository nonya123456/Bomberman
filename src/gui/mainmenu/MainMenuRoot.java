package gui.mainmenu;

import gui.WoodenButton;
import gui.help.HelpScene;
import gui.level.SelectLevelScene;
import gui.shop.ShopScene;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import sharedObject.RenderableHolder;

public class MainMenuRoot extends HBox {
	private WoodenButton startGameButton;
	private WoodenButton shopButton;
	private WoodenButton helpButton;
	private WoodenButton quitButton;

	public MainMenuRoot() {
		super(80);

		this.setBackground(
				new Background(new BackgroundImage(RenderableHolder.gameBackground,
						BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

		startGameButton = new WoodenButton("Start Game", 180, 50);
		shopButton = new WoodenButton("Shop", 180, 50);
		helpButton = new WoodenButton("Help", 180, 50);
		quitButton = new WoodenButton("Quit", 180, 50);

		ImageView logo = new ImageView(RenderableHolder.logo);
		logo.setFitHeight(324);
		logo.setFitWidth(270);

		VBox allButton = new VBox(10);
		allButton.setAlignment(Pos.CENTER);
		allButton.getChildren().addAll(startGameButton, shopButton, helpButton,
				quitButton);

		this.getChildren().addAll(logo, allButton);
		this.setAlignment(Pos.CENTER);

		addListener();

	}

	private void addListener() {

		startGameButton.getButton().setOnAction((e) -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().setScene(new SelectLevelScene());

		});

		shopButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().setScene(new ShopScene());
		});

		helpButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().setScene(new HelpScene());
		});

		quitButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.stop();
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().close();
		});
	}
}
