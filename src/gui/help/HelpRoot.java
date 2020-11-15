package gui.help;

import gui.WoodenButton;
import gui.mainmenu.MainMenuScene;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import sharedObject.RenderableHolder;

public class HelpRoot extends VBox {
	private HBox menu;
	private HelpCanvas helpCanvas;
	private WoodenButton control;
	private WoodenButton item;
	private WoodenButton objects;
	private WoodenButton enemy;
	private WoodenButton objective;
	private WoodenButton backButton;
	private String state;

	public HelpRoot() {
		super(10);
		this.setBackground(
				new Background(new BackgroundImage(RenderableHolder.gameBackground,
						BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
						BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		menu = new HBox();
		control = new WoodenButton("Control", 100, 50);
		objects = new WoodenButton("Object", 100, 50);
		item = new WoodenButton("Item", 100, 50);
		enemy = new WoodenButton("Enemy", 100, 50);
		objective = new WoodenButton("Objective", 100, 50);

		menu.getChildren().addAll(control, objects, item, enemy, objective);
		menu.setAlignment(Pos.TOP_CENTER);

		state = "Control";
		helpCanvas = new HelpCanvas(state);

		backButton = new WoodenButton("Back To Main Menu", 300, 50);
		this.getChildren().addAll(menu, helpCanvas, backButton);
		this.setAlignment(Pos.CENTER);

		addListener();
	}

	private void addListener() {
		backButton.getButton().setOnAction((e) -> {
			RenderableHolder.buttonClickedSound.play();
			Main.getMainStage().setScene(new MainMenuScene());
		});
		control.getButton().setOnAction((e) -> {
			if (!state.equals("Control")) {
				RenderableHolder.buttonClickedSound.play();
				helpCanvas.updateHelp("Control");
				state = "Control";
			}
		});
		item.getButton().setOnAction((e) -> {
			if (!state.equals("Item")) {
				RenderableHolder.buttonClickedSound.play();
				helpCanvas.updateHelp("Item");
				state = "Item";
			}
		});
		enemy.getButton().setOnAction((e) -> {
			if (!state.equals("Enemy")) {
				RenderableHolder.buttonClickedSound.play();
				helpCanvas.updateHelp("Enemy");
				state = "Enemy";
			}
		});
		objective.getButton().setOnAction((e) -> {
			if (!state.equals("Objective")) {
				RenderableHolder.buttonClickedSound.play();
				helpCanvas.updateHelp("Objective");
				state = "Objective";
			}
		});
		objects.getButton().setOnAction(e -> {
			if (!state.equals("Object")) {
				RenderableHolder.buttonClickedSound.play();
				helpCanvas.updateHelp("Object");
				state = "Object";
			}
		});
	}
}
