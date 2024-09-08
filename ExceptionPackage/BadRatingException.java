package ExceptionPackage;

public class BadRatingException extends Exception {

    public BadRatingException() {
        super("Invalid rating. Rating must be valid rating.");
    }

    public BadRatingException(String message) {
        super(message);
    }
}