package design.moviebooking.exceptions;

public class LockFailureException extends Exception {
    public LockFailureException(String message) {
        super(message);
    }
}
