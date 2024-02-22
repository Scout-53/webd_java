package webd4201patels;
/**
 * This is the custom exception class that handles the exception when the record is not found in the database.
 * @author Shan Patel
 * @version 1.0 
 * @since 1.0
 */

/**
 * This is the custom exception class that handles the exception when the record is not found in the database.
 */
public class NotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * This is the default constructor that calls the super class constructor.
     */
    public NotFoundException(){
        super();
    }
    /**
     * This is the parameterized constructor that calls the super class constructor
     * @param message string
     */
    public NotFoundException(String message){
        super(message);
    }


}
