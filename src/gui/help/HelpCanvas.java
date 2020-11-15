package gui.help;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Sprites;
import sharedObject.RenderableHolder;

public class HelpCanvas extends Canvas {
	public HelpCanvas(String s) {
		super(600, 200);
		GraphicsContext gc = this.getGraphicsContext2D();

		this.setVisible(true);
		gc.setFill(Color.SADDLEBROWN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(10);

		gc.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		gc.strokeRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		updateHelp(s);

	}

	public void updateHelp(String s) {
		GraphicsContext gc = this.getGraphicsContext2D();

		gc.setFill(Color.SADDLEBROWN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(10);
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		gc.strokeRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		switch (s) {
		case "Control":

			gc.setLineWidth(3);
			gc.setFont(new Font(20));
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.BLACK);

			gc.strokeRoundRect(130, 15, 100, 30, 10, 10);
			gc.fillText("SPACE", 135, 40);
			gc.strokeRoundRect(165, 50, 30, 30, 10, 10);
			gc.fillText("W", 170, 75);
			gc.strokeRoundRect(165, 85, 30, 30, 10, 10);
			gc.fillText("A", 170, 110);
			gc.strokeRoundRect(165, 120, 30, 30, 10, 10);
			gc.fillText("S", 170, 145);
			gc.strokeRoundRect(165, 155, 30, 30, 10, 10);
			gc.fillText("D", 170, 180);
			gc.setFill(Color.WHITE);
			gc.fillText("Bomb / Use Weapon", 275, 40);
			gc.fillText("Move Up", 275, 75);
			gc.fillText("Move Left", 275, 110);
			gc.fillText("Move Down", 275, 145);
			gc.fillText("Move Right", 275, 180);
			break;
		case "Enemy":
			gc.setLineWidth(3);
			gc.setFont(new Font(15));
			gc.setFill(Color.WHITE);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.MUSHROOM], 30, 15, 30,
					30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BLINDMUSHROOM], 30,
					50, 30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.MECHABOMB], 30, 85,
					30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BOMBER_BOT], 30, 120,
					30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BOMBEATER], 30, 155,
					30, 30);

			gc.fillText("Mushroom is slow and able to follow you.", 80, 35);
			gc.fillText("Blind mushroom is fast, but he can't follow you.", 80, 70);
			gc.fillText("Mechabomb can transform to a bomb.", 80, 105);
			gc.fillText("Bomberbot can drop a bomb.", 80, 140);
			gc.fillText("Bombeater can eat the bomb.", 80, 175);
			break;
		case "Item":
			gc.setLineWidth(3);
			gc.setFont(new Font(20));
			gc.setFill(Color.WHITE);
			gc.fillText("Power-ups", 30, 30);
			gc.fillText("Weapons", 330, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.GLOVES], 30, 40, 30,
					30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_BOMB], 30,
					75, 30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_POWER], 30,
					110, 30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.INCREASE_SPEED], 30,
					145, 30, 30);

			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.LANDMINE], 330, 40,
					30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.ROCKETLAUNCHER], 330,
					75, 30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.FLAMETHROWER], 330,
					110, 30, 30);

			gc.setFont(new Font(15));
			gc.fillText("Use Gloves to push bombs.", 80, 60);
			gc.fillText("Increase maximum bombs.", 80, 95);
			gc.fillText("Increase bomb power.", 80, 130);
			gc.fillText("Increase speed.", 80, 165);

			gc.fillText("Land Mine.", 380, 60);
			gc.fillText("Rocket Launcher.", 380, 95);
			gc.fillText("Flamethrower.", 380, 130);
			break;
		case "Objective":
			gc.setFont(new Font(20));
			gc.setFill(Color.WHITE);
			gc.fillText("Game Objective is to kill all enemies and get through the door",
					30, 50);
			gc.fillText("hidden behind the wall. If you beat the game, the score you", 30,
					85);
			gc.fillText("get will be the number of coins you get. You can use the coins",
					30, 120);
			gc.fillText("to upgrade your base stats and buy a weapon.", 30, 155);
			break;
		case "Object":
			gc.setLineWidth(3);
			gc.setFont(new Font(15));
			gc.setFill(Color.WHITE);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.WALL], 30, 15, 30,
					30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.WALL], 30, 50, 30,
					30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BREAKABLE_WALL], 30,
					85, 30, 30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BOMB], 30, 120, 30,
					30);
			gc.drawImage(RenderableHolder.allSpriteImage[0][Sprites.BOMB_FROM_ENEMY], 30,
					155, 30, 30);

			gc.fillText("This wall can't be destroyed.", 80, 35);
			gc.fillText(
					"This wall can't be destroyed. It will appear only if you run into the wall",
					80, 70);
			gc.fillText("This wall can be destroyed by bombs, flamethrower and rocket.",
					80, 105);
			gc.fillText("This Bomb will be exploded if interact with blast.", 80, 140);
			gc.fillText(
					"This Bomb is from an enemy. It won't be exploded if interact with blast.",
					80, 175);

		}
	}

}
