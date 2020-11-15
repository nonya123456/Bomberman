package gui.level;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class LevelButton extends StackPane {
	private int index;
	private Button button;
	private boolean isUnlocked;
	private boolean isWin;

	public LevelButton(int index, boolean isUnlocked, boolean isWin) {
		super();

		this.index = index;
		this.isWin = isWin;
		this.isUnlocked = isUnlocked;

		ImageView background = new ImageView(RenderableHolder.levelButtonImage);
		background.setFitHeight(100);
		background.setFitWidth(100);

		button = new Button();
		button.setStyle("-fx-background-color: transparent");
		button.setPrefSize(100, 100);
		button.setFocusTraversable(false);

		Label label = new Label(Integer.toString(index));
		label.setStyle("-fx-font-size: 50px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");

		ImageView tickImage = new ImageView(RenderableHolder.tickImage);
		tickImage.setFitHeight(70);
		tickImage.setFitWidth(70);

		ImageView lockImage = new ImageView(RenderableHolder.lockImage);
		lockImage.setFitHeight(50);
		lockImage.setFitWidth(50);

		if (isWin) {
			this.getChildren().addAll(background, label, tickImage, button);
		} else if (isUnlocked) {
			this.getChildren().addAll(background, label, button);
		} else {
			this.getChildren().addAll(background, label, lockImage);
		}
		addListener();
		this.setStyle("-fx-border-color: transparent;\n" + "-fx-border-width: 3;\n"
				+ "-fx-border-style: dashed;\n");

	}

	private void addListener() {
		button.setOnAction(e -> {
			RenderableHolder.buttonClickedSound.play();
			SelectLevelRoot.setSelectedLevel(index);
			SelectLevelRoot.updateSelected();
		});
	}

	public void update() {
		if (this.index == SelectLevelRoot.getSelectedLevel()) {
			this.setStyle("-fx-border-color: white;\n" + "-fx-border-width: 3;\n"
					+ "-fx-border-style: dashed;\n");

		} else {
			this.setStyle("-fx-border-color: transparent;\n" + "-fx-border-width: 3;\n"
					+ "-fx-border-style: dashed;\n");
		}
	}

	public boolean isUnlocked() {
		return isUnlocked;
	}

	public void setUnlocked(boolean isUnlocked) {
		this.isUnlocked = isUnlocked;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

}
