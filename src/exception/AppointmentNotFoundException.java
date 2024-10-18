package exception;

/**
 * Custom exception class for handling cases where an appointment is not found.
 * Extends RuntimeException to allow unchecked exception handling.
*/
public class AppointmentNotFoundException extends RuntimeException {

    /**
     * Constructs a new AppointmentNotFoundException with a specified detail message.
     * 
     * @param message the detail message which explains the reason for the exception.
    */
    public AppointmentNotFoundException(String message) {

        super(message);
    }
}