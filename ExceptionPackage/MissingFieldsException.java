package ExceptionPackage;

public class MissingFieldsException extends Exception {

    public MissingFieldsException() {
        super("Missing fields. Some required fields are missing.");
    }

    public MissingFieldsException(String message) {
        super(message);
    }
}
