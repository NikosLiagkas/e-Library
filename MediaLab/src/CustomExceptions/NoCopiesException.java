package CustomExceptions;

public class NoCopiesException extends Exception {
    public NoCopiesException() {
        super("There are no available copies for this book.");
    }
    public NoCopiesException(String message) {
        super(message);
    } 
}
