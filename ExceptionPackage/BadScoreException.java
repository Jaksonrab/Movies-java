package ExceptionPackage;

public class BadScoreException extends Exception {

    
    public BadScoreException() {
        super("Invalid score. Score must be in between interval of 0-10.");
    }

  
    public BadScoreException(String message) {
        super(message);
    }
}