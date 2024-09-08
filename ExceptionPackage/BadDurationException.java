package ExceptionPackage;

public class BadDurationException extends Exception {

    public BadDurationException() {
        super("Invalid duration. Duration must between interval 30-300.");
    }

    public BadDurationException(String message) {
        super(message);
    }

}
