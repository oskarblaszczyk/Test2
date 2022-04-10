package exceptions;

public class DuplicatedElementOnListException extends RuntimeException {
    public DuplicatedElementOnListException() {
    }

    public DuplicatedElementOnListException(String message) {
        super(message);
    }

    public DuplicatedElementOnListException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedElementOnListException(Throwable cause) {
        super(cause);
    }

}
