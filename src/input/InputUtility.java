package input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import logic.GameController;

public class InputUtility {
	private static boolean isSpaceTriggered;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keycode)) {
				keyPressed.add(0, keycode);
			}
		} else {
			keyPressed.remove(keycode);
		}
	}

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static boolean isSpaceTriggered() {
		return isSpaceTriggered;
	}

	public static void setSpaceTriggered(boolean isSpaceTriggered) {
		InputUtility.isSpaceTriggered = isSpaceTriggered;
	}

	public static ArrayList<KeyCode> getKeyPressed() {
		return keyPressed;
	}

	public static void setKeyPressed(ArrayList<KeyCode> keyPressed) {
		InputUtility.keyPressed = keyPressed;
	}

	public static void postUpdate() {
		isSpaceTriggered = false;
		if (GameController.isOver() || GameController.isPaused()) {
			keyPressed.clear();
		}
	}
}
