package ExceptionPackage;

public class BadYearException extends Exception{


	    // Default constructor
	    public BadYearException() {
	        super("Invalid year. Year must be between interval 1990-1989.");
	    }

	    // Constructor with a custom message
	    public BadYearException(String message) {
	        super(message);
	    }
	}
