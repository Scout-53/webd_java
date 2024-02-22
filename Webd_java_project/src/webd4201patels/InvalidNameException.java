package webd4201patels;
/**
 * InvalidNameException - This is the custom exception class for invalid name.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public class InvalidNameException extends Exception{

    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidNameException() {
        super();
    }

    /**
     * Parameterized constructor
     * @param message for the exception
     */
    public InvalidNameException(String message) {
        super(message);
    }

}
