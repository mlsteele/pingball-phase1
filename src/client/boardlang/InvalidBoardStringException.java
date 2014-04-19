package client.boardlang;

/**
 * Exception to signify that a rep invariant has been violated.
 */
public class InvalidBoardStringException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a InvalidBoardStringException with a detail message.
     * @param message description of the error
     */
    public InvalidBoardStringException(String message) {
        super(message);
    }

    /**
     * Constructs a InvalidBoardStringException with a detail message and cause.
     * @param message description of the error
     * @param cause exception that caused this error
     */
    public InvalidBoardStringException(String message, Throwable cause) {
        super(message, cause);
    }
}
