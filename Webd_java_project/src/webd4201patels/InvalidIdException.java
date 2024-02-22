package webd4201patels;
/**
 * InvalidIdException - This is the custom exception class for invalid ID.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */


public class InvalidIdException extends Exception {


    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidIdException() {
        super();
    }

    /**
     * Parameterized constructor
     * @param message for the exception
     */
    public InvalidIdException(String message) {
        super(message);
    }
}




