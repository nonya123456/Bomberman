package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import sharedObject.RenderableHolder;

public class WoodenButton extends StackPane {
	private Button button;
	private Label label;

	public WoodenButton(String text, int width, int height) {

		ImageView woodenBoard = new ImageView(RenderableHolder.woodenBoardImage);
		woodenBoard.setFitWidth(width);
		woodenBoard.setFitHeight(height);

		button = new Button();
		button.setStyle("-fx-background-color: transparent");
		button.setPrefSize(width, height);
		button.setFocusTraversable(false);

		label = new Label(text);
		label.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: #FFFFFF;");

		this.getChildren().addAll(woodenBoard, label, button);
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
