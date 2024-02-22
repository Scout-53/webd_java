package webd4201patels;
/**
 * InvalidPasswordException - This is the custom exception class for invalid password.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public class InvalidPasswordException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidPasswordException() {
        super("Invalid Password: Password is not of the required length.");
    }

    /**
     * Parameterized constructor
     * @param message for the exception
     */
    public InvalidPasswordException(String message) {
        super(message);
    }


}
