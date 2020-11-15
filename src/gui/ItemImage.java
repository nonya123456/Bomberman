package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ItemImage extends StackPane {
	private ImageView imageView;

	public ItemImage(double width, Color backgroundColor) {
		super();
		Rectangle background = new Rectangle(width, width);
		background.setFill(backgroundColor);
		background.setArcWidth(10.0);
		background.setArcHeight(10.0);

		imageView = new ImageView();
		imageView.setFitHeight(width);
		imageView.setFitWidth(width);
		this.getChildren().addAll(background, imageView);
	}

	public ItemImage(double width, Image image, Color backgroundColor) {
		super();

		Rectangle background = new Rectangle(width, width);
		background.setFill(backgroundColor);
		background.setArcWidth(10.0);
		background.setArcHeight(10.0);

		imageView = new ImageView(image);
		imageView.setFitHeight(width);
		imageView.setFitWidth(width);

		this.getChildren().addAll(background, imageView);
	}

	public void setImage(Image image) {
		this.imageView.setImage(image);
	}

}
