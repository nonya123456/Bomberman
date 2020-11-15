package gui.level;

import exception.InvalidLevelSelectionException;
import gui.ErrorMessage;
import gui.WoodenButton;
import gui.game.GameScene;
import gui.mainmenu.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import logic.GameController;
import main.Main;
import sharedObject.RenderableHolder;

public class SelectLevelRoot extends StackPane {
	private static GridPane levelGrid;
	private WoodenButton startButton;
	private WoodenButton backButton;
	private static int selectedLevel = 0;
	private ErrorMessage errorMessage;

	public SelectLevelRoot() {
		super();

		errorMessage = new ErrorMessage("");
		VBox LevelSelection = new VBox(10);
		LevelSelection.setBackground(
				new Background(new BackgroundImage(RenderableHolder.gameBackground,
						BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		levelGrid = new GridPane();

		this.setAlignment(Pos.TOP_CENTER);
		for (int i = 1; i <= 5; i++) {
			if (i < GameController.getLastUnlockedLevel()) {
				levelGrid.add(new LevelButton(i, true, true), i - 1, 0);
			} else if (i == GameController.getLastUnlockedLevel()) {
				levelGrid.add(new LevelButton(i, true, false), i - 1, 0);
			} else {
				levelGrid.add(new LevelButton(i, false, false), i - 1, 0);
			}
		}
		for (int i = 6; i <= 10; i++) {
			if (i < GameController.getLastUnlockedLevel()) {
				levelGrid.add(new LevelButton(i, true, true), i - 6, 1);
			} else if (i == GameController.getLastUnlockedLevel()) {
				levelGrid.add(new LevelButton(i, true, false), i - 6, 1);
			} else {
				levelGrid.add(new LevelButton(i, false, false), i - 6, 1);
			}
		}

		levelGrid.setAlignment(Pos.CENTER);

		startButton = new WoodenButton("Start", 300, 50);
		backButton = new WoodenButton("Back To Main Menu", 300, 50);

		LevelSelection.getChildren().addAll(levelGrid, startButton, backButton);
		LevelSelection.setAlignment(Pos.CENTER);

		this.getChildren().addAll(LevelSelection, errorMessage);
		addListener();
	}

	private void addListener() {
		backButton.getButton().setOnAction((e) -> {
			RenderableHolder.buttonClickedSound.play();
			selectedLevel = 0;
			Main.getMainStage().setScene(new MainMenuScene());
		});

		startButton.getButton().setOnAction(e -> {
			RenderableHolder.buttonClickedSound.play();
			try {
				Main.getMainStage().setScene(new GameScene(selectedLevel));
			} catch (InvalidLevelSelectionException exception) {
				// TODO Auto-generated catch block
				errorMessage.setMessage(exception.getMessage());
			}

			selectedLevel = 0;
		});
	}

	public static void updateSelected() {
		for (Node s : levelGrid.getChildren()) {
			LevelButton lb = (LevelButton) s;
			lb.update();
		}
	}

	public static int getSelectedLevel() {
		return selectedLevel;
	}

	public static void setSelectedLevel(int selectedLevel) {
		SelectLevelRoot.selectedLevel = selectedLevel;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
