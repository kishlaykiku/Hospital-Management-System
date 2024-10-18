package exception;

/**
 * Custom exception class for handling database connection errors.
 * Extends RuntimeException to allow unchecked exception handling.
 * Provides additional context by including the original cause of the exception.
*/
public class DatabaseConnectionException extends RuntimeException {

    /**
     * Constructs a new DatabaseConnectionException with a specified detail message and cause.
     * 
     * @param message the detail message that explains the reason for the exception.
     * @param cause the original throwable cause that led to this exception.
    */
    public DatabaseConnectionException(String message, Throwable cause) {

        super(message, cause);
    }
}