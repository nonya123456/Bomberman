package gui;

import javafx.application.Platform;

public class UpdateOpacityThread extends Thread {
	private boolean isRunning;
	private ErrorMessage errorMessage;

	public UpdateOpacityThread(ErrorMessage errorMessage) {
		super();
		isRunning = true;
		this.errorMessage = errorMessage;
	}

	public void run() {
		while (errorMessage.getOpacity() > 0 && isRunning) {
			try {
				Thread.sleep(15);
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						errorMessage.update();
					}

				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
