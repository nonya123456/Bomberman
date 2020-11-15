package exception;

public class InvalidLandMinePlantingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidLandMinePlantingException() {
		this.message = "You cannot plant a land mine here";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
