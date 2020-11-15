package gui.game;

import exception.InvalidBombPlantingException;
import exception.InvalidLandMinePlantingException;
import exception.InvalidLevelSelectionException;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.GameController;
import logic.gamemap.GameMap;
import sharedObject.RenderableHolder;

public class GameScene extends Scene {

	private AnimationTimer timer;

	public GameScene(int index) throws InvalidLevelSelectionException {
		super(new GameRoot(index), 200 + GameMap.WIDTH * GameMap.PIXELS_PER_BLOCK,
				GameMap.HEIGHT * GameMap.PIXELS_PER_BLOCK);
		GameRoot root = (GameRoot) this.getRoot();
		GameScreen gameScreen = root.getGameScreen();
		InfoPane infoPane = root.getInfoPane();

		addListener();
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				gameScreen.paintComponent();
				infoPane.updateInfoPane();
				try {
					GameController.updateMap();
				} catch (InvalidBombPlantingException e) {
					// TODO Auto-generated catch block
					root.getErrorMessage().setMessage(e.getMessage());
				} catch (InvalidLandMinePlantingException e) {
					// TODO Auto-generated catch block
					root.getErrorMessage().setMessage(e.getMessage());
				}
				RenderableHolder.getInstance().update();
				InputUtility.postUpdate();

				if (GameController.isOver()) {
					GameController.handleGameOver();
					root.getChildren().add(new GameOverAlert(GameController.isWin()));
					infoPane.disableButton();
					this.stop();
				}

			}

		};
		timer.start();
	}

	public AnimationTimer getTimer() {
		return timer;
	}

	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}

	private void addListener() {
		this.setOnKeyPressed((KeyEvent ke) -> {
			if (!GameController.isPaused() && !GameController.isOver()) {
				if (ke.getCode() == KeyCode.A || ke.getCode() == KeyCode.S
						|| ke.getCode() == KeyCode.W || ke.getCode() == KeyCode.D) {
					InputUtility.setKeyPressed(ke.getCode(), true);
				} else if (ke.getCode() == KeyCode.SPACE) {
					if (!InputUtility.getKeyPressed(ke.getCode())) {
						InputUtility.setSpaceTriggered(true);
					}
					InputUtility.setKeyPressed(ke.getCode(), true);

				}
			}

		});
		this.setOnKeyReleased((KeyEvent ke) -> {
			if (!GameController.isPaused() && !GameController.isOver()) {
				InputUtility.setKeyPressed(ke.getCode(), false);
			}

		});

	}

}
