package CustomExceptions;

public class DateException extends Exception {
    public DateException() {
        super("Invalid Date");
    }
    public DateException(String message) {
        super(message);
    }
}
