package CustomExceptions;

public class NegativeCopiesException extends Exception {
    public NegativeCopiesException() {
        super("Books can't have negative copies.");
    }
}
