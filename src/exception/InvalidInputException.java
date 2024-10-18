package exception;

/**
 * Custom exception class for handling invalid user input scenarios.
 * Extends RuntimeException to allow unchecked exception handling.
*/
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new InvalidInputException with a specified detail message.
     * 
     * @param message the detail message that explains the reason for the exception.
    */
    public InvalidInputException(String message) {

        super(message);
    }
}