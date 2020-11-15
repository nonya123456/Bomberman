package gui;

import javafx.scene.control.Label;

public class ErrorMessage extends Label {
	private String message;
	private UpdateOpacityThread thread;

	public ErrorMessage(String message) {
		super(message);
		this.message = message;
		this.setStyle("-fx-font-size: 18pt;" + "-fx-text-fill: red;"
				+ "-fx-font-weight: bold;");
		startUpdating();
	}

	public void update() {
		this.setOpacity(this.getOpacity() - 0.01);
	}

	public void setMessage(String newMessage) {
		thread.setRunning(false);
		this.setText(newMessage);
		this.setOpacity(1);
		startUpdating();
	}

	private void startUpdating() {
		thread = new UpdateOpacityThread(this);
		thread.start();
	}

	public UpdateOpacityThread getThread() {
		return thread;
	}

	public void setThread(UpdateOpacityThread thread) {
		this.thread = thread;
	}

	public String getMessage() {
		return message;
	}

}
