package ExceptionPackage;

public class MissingQuotesException extends Exception {

    public MissingQuotesException() {
        super("Missing quotes. There is missing quotes.");
    }

    public MissingQuotesException(String message) {
        super(message);
    }
}