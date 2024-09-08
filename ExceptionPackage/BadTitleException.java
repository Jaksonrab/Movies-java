package ExceptionPackage;

public class BadTitleException extends Exception {

    // Default constructor
    public BadTitleException() {
        super("Invalid title. Title not in proper format, is null or empty");
    }

    // Constructor with a custom message
    public BadTitleException(String message) {
        super(message);
    }
}