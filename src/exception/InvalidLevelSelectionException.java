package exception;

public class InvalidLevelSelectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidLevelSelectionException() {
		this.message = "Please Select A Level";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
