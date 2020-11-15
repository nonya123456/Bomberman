package gui.game;

import exception.InvalidLevelSelectionException;
import gui.ErrorMessage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.GameController;
import logic.gamemap.GameMap;

public class GameRoot extends StackPane {
	private ErrorMessage errorMessage;
	private GameScreen gameScreen;
	private InfoPane infoPane;

	public GameRoot(int index) throws InvalidLevelSelectionException {
		super();
		HBox gameAndInfo = new HBox(0);
		GameMap gameMap = new GameMap(index);

		errorMessage = new ErrorMessage("");
		gameScreen = new GameScreen(GameMap.WIDTH * GameMap.PIXELS_PER_BLOCK,
				GameMap.HEIGHT * GameMap.PIXELS_PER_BLOCK);
		infoPane = new InfoPane();
		gameAndInfo.getChildren().add(infoPane);
		gameAndInfo.getChildren().add(gameScreen);

		this.getChildren().add(gameAndInfo);
		this.getChildren().add(errorMessage);
		this.setAlignment(Pos.TOP_CENTER);

		GameController.initialize(gameMap);
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public InfoPane getInfoPane() {
		return infoPane;
	}

	public void setInfoPane(InfoPane infoPane) {
		this.infoPane = infoPane;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
