package ExceptionPackage;

public class BadNameException extends Exception {
	
	public BadNameException() {
		super("Invalid name. Name entered is invalid, null or empty");
	}
	
	public BadNameException(String message) {
		super(message);
	}
}
