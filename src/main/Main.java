package main;

import gui.mainmenu.MainMenuScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage mainStage;

	public static Stage getMainStage() {
		return mainStage;
	}

	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		mainStage.setResizable(false);
		Scene scene = new MainMenuScene();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bomberman");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
