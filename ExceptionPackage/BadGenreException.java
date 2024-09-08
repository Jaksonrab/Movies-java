package ExceptionPackage;

public class BadGenreException extends Exception {

   
    public BadGenreException() {
        super("Invalid genre. Genre entered is invalid, empty or null.");
    }

    public BadGenreException(String message) {
        super(message);
    }
}
