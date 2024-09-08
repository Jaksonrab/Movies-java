package ExceptionPackage;

public class ExcessFieldsException extends Exception {

    public ExcessFieldsException() {
        super("Excess fields. There are too many fields provided.");
    }

    public ExcessFieldsException(String message) {
        super(message);
    }
}
