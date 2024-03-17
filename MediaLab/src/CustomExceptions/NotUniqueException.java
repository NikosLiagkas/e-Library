package CustomExceptions;

public class NotUniqueException extends Exception{
    public NotUniqueException() {
        super("Not Unique.");
    }
    public NotUniqueException(String message) {
        super(message);
    } 
}
