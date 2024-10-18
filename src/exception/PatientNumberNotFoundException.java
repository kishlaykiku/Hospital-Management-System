package exception;

/**
 * Custom exception class for handling cases where a patient number is not found.
 * Extends RuntimeException to allow unchecked exception handling.
*/
public class PatientNumberNotFoundException extends RuntimeException {

    /**
     * Constructs a new PatientNumberNotFoundException with a specified detail message.
     * 
     * @param message the detail message that explains the reason for the exception.
    */
    public PatientNumberNotFoundException(String message) {

        super(message);
    }
}