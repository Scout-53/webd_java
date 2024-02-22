package webd4201patels;
/**
 * This is the custom exception class that handles the exception when the record is already found in the database.
 * @author Shan Patel 
 * @version 1.0 
 * @since 1.0
 */

/**
 * This is the custom exception class that handles the exception when the record is already found in the database.
 */
public class DuplicateException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default Constructor
     */
    public DuplicateException(){
        super();
    }

    /**
     * Parameterized Constructor    
     * @param message - the message to be displayed when the exception is thrown
     */
    public DuplicateException(String message){
        super(message);
    }
    
}
