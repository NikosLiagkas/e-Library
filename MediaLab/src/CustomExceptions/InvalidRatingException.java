package CustomExceptions;

public class InvalidRatingException extends Exception{
    public InvalidRatingException() {
        super("Invalid Rating. Please rate the book 1 through 5.");
    }
    public InvalidRatingException(String message) {
        super(message);
    }
}
