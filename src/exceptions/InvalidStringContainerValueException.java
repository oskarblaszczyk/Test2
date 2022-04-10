package exceptions;

public class InvalidStringContainerValueException extends RuntimeException{
    public InvalidStringContainerValueException() {
    }

    public InvalidStringContainerValueException(String message) {
        super(message);
    }

    public InvalidStringContainerValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStringContainerValueException(Throwable cause) {
        super(cause);
    }
}
