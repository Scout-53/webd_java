package webd4201patels;
/**
 * CollegeInterface - This is the interface class for the college details.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public interface CollegeInterface {

    /** Constant for the college name */
    public static final String COLLEGE_NAME = "Durham College";

    /** Constant for the phone number */
    public static final String PHONE_NUMBER = "(905) 721-2000";

    /** Constant for the minimum id number */
    public static final long MINIMUM_ID_NUMBER = 100000000;

    /** Constant for the maximum id number */
    public static final long MAXIMUM_ID_NUMBER = 999999999;

    /** Constant for the minimum password length */
    public static final byte MINIMUM_PASSWORD_LENGTH = 8;

    /** Constant for the maximum password length */
    public static final byte MAXIMUM_PASSWORD_LENGTH = 20;

    
    /** method that takes no arguments and is publicly accessible.
     * @return string value */
    public abstract String getTypeForDisplay();

}
