package webd4201patels;
/**
 * InvalidUserDataException - This is the custom exception class for invalid user data.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public class InvalidUserDataException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidUserDataException() {
        super("Invalid Data: Please enter the user data as per the requirement.");
    }

    /**
     * Parameterized constructor
     * @param message for the exception
     */
    public InvalidUserDataException(String message) {
        super(message);
    }


}
