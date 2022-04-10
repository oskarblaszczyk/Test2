package exceptions;

public class InvalidStringContainerPatternException extends RuntimeException{
    public InvalidStringContainerPatternException() {
    }

    public InvalidStringContainerPatternException(String message) {
        super(message);
    }

    public InvalidStringContainerPatternException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStringContainerPatternException(Throwable cause) {
        super(cause);
    }
}
