package CustomExceptions;

public class BorrowLimitException extends Exception {
    public BorrowLimitException() {
        super("You can't borrow more than 2 books.");
    }
    public BorrowLimitException(String message) {
        super(message);
    }
}
