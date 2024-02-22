package webd4201patels;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * User - This is the user.java class file that implements CollegeInterface.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public class User implements CollegeInterface{

    /**
     * The Abstract class from the interface file is overridden and returns the user value.
     * @return User 
     */
    @Override
    public String getTypeForDisplay() {
        return "User";
    }

    /** Constant for the default ID */
    protected static final long DEFAULT_ID = 100123456;

    /** Constant for the default password */
    protected static final String DEFAULT_PASSWORD = "password";

    /** Constant for the min length of the password */
    protected static final byte MINIMUM_PASSWORD_LENGTH = 8;

    /** Constant for the max length of the password */
    protected static final byte MAXIMUM_PASSWORD_LENGTH = 40;

    /** Constant for the default first name */
    protected static final String DEFAULT_FIRST_NAME = "John";

    /** Constant for the default last name */
    protected static final String DEFAULT_LAST_NAME = "Doe";

    /** Constant for the default email address */
    protected static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.ca";

    /** Constant for the default enable status */
    protected static final boolean DEFAULT_ENABLE_STATUS = true ;

    /** Constant for the default user type */
    protected static final char DEFAULT_TYPE = 's';

    /** Constant for the max ID length */
    protected static final byte ID_NUMBER_LENGTH = 9;

    /** DateFormate */
    protected static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);



    /** Instance variable for ID */
    private long id;

    /** Instance variable for password */
    private String password;

    /** Instance variable for first name */
    private String firstName;

    /** Instance variable for last name */
    private String lastName;

    /** Instance variable for email address */
    private String emailAddress;

    /** Instance variable for last access */
    private Date lastAccess;

    /** Instance variable for endrol date */
    private Date enrolDate;

    /** Instance variable for enabled */
    private boolean enabled;

    /** Instance variable for type */
    private char type;


    
    /**
     * Getter for ID
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for ID but first it also checks whether the ID length is valid or not.
     * @param id the id to set
     * @throws InvalidIdException when the user id is invalid
     */
    public void setId(long id) throws InvalidIdException {
        if (!verifyId(id)) {
            throw new InvalidIdException("Invalid ID : User ID must be between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER + " inclusive.");
        }
        this.id = id;
    }

    /**
     * Getter for password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password but first it also checks whether the password length is valid or not.
     * @param password the password to set
     * @throws InvalidPasswordException when the password is invalid
     */
	public void setPassword(String password) throws InvalidPasswordException {
        if (!(password.length() >= MINIMUM_PASSWORD_LENGTH && password.length() <= MAXIMUM_PASSWORD_LENGTH)) {
            throw new InvalidPasswordException("Invalid Password :  Password must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH + " characters.");
        }
        this.password = password;
    }
	
	/**
     * Getter for first name
     * @return first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for last name
     * @return last  name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for first name but first it also checks whether the first name is valid or not.
     * @param firstName users first name
     * @throws InvalidNameException when the first name is invalid
     */
    public void setFirstName(String firstName) throws InvalidNameException {
        if (firstName.isEmpty()) {
            throw new InvalidNameException("Invalid First Name : First name cannot be an empty/null value.");
        }
        this.firstName = firstName;
    }
    
    /**
     * Setter for last name but first it also checks whether the last name is valid or not.
     * @param lastName users last name
     * @throws InvalidNameException when the last name is invalid
     */
    public void setLastName(String lastName) throws InvalidNameException {
        if (lastName.isEmpty()) {
            throw new InvalidNameException("Invalid Last Name : Last name cannot be an empty/null value.");
        }
        this.lastName = lastName;
    }


    /**
     * Getter for email address
     * @return email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for email address
     * @param emailAddress users email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for last access
     * @return last access
     */
    public Date getLastAccess() {
        return lastAccess;
    }

    /**
     * Setter for last access
     * @param lastAccess users last access
     */
    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Getter for enrol date
     * @return enrol date
     */
    public Date getEnrolDate() {
        return enrolDate;
    }

    /**
     * Setter for enrol date
     * @param enrolDate users enrol date
     */
    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    /**
     * Getter for enabled
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Setter for enabled
     * @param enabled users enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Getter for type
     * @return type
     */
    public char getType() {
        return type;
    }

    /**
     * Setter for type
     * @param type users type
     */
    public void setType(char type) {
        this.type = type;
    }


    /**
     * Default constructor for the user
     * @throws InvalidUserDataException when the user data is invalid
     */
    public User() throws InvalidUserDataException{
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,
                DEFAULT_EMAIL_ADDRESS, new Date(), new Date(),
                DEFAULT_ENABLE_STATUS, DEFAULT_TYPE);
    }


    /**
     * Parameterized constructor for the user and also since all the  conditions are checked and verified in the respective setter method, we do not need to again check and just with the help of a try....catch , throw the InvalidUserDataException.
     * @param id user's id
     * @param password user's password
     * @param firstName user's first name
     * @param lastName user's last name
     * @param emailAddress user's email address
     * @param lastAccess user's last access
     * @param enrolDate user's enrol date
     * @param enabled user's enabled
     * @param type  user's type
     * @throws InvalidUserDataException when the user data is invalid
     */
    public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException {
        try {
            this.setId(id);
            this.setPassword(password);
            this.setFirstName(firstName);
            this.setLastName(lastName);
            this.setEmailAddress(emailAddress);
            this.setLastAccess(lastAccess);
            this.setEnrolDate(enrolDate);
            this.setEnabled(enabled);
            this.setType(type);
        } catch (InvalidIdException | InvalidPasswordException | InvalidNameException e) {
            throw new InvalidUserDataException("Passed to create a new User object");    
        }
    }

    // /**
    //  * This can be done but this just causes the same thing to be done again and again.
    //  * @param id
    //  * @param password
    //  * @param firstName
    //  * @param lastName
    //  * @param emailAddress
    //  * @param lastAccess
    //  * @param enrolDate
    //  * @param enabled
    //  * @param type
    //  * @throws InvalidUserDataException 
    //  */
    // public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException {
    //     if (!verifyId(id)) {
    //         throw new InvalidUserDataException("Invalid ID. ID must be between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER + ".");
    //     }

    //     /**
    //      * Validate password length
    //      */
    //     if (password.length() > MAXIMUM_PASSWORD_LENGTH) {
    //         throw new InvalidUserDataException("Invalid Password :  Password must be less than " + MAXIMUM_PASSWORD_LENGTH + " characters.");
    //     }

    //     /**
    //      *  Validate first name
    //      */
    //     if (firstName.isEmpty()) {
    //         throw new InvalidUserDataException("Invalid First Name : First name cannot be an empty/null value.");
    //     }

    //     /**
    //      *  Validate last name
    //      */
    //     if (lastName.isEmpty()) {
    //         throw new InvalidUserDataException("Invalid Last Name : Last name cannot be an empty/null value.");
    //     }
    //     this.id = id;
    //     this.password = password;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.emailAddress = emailAddress;
    //     this.lastAccess = lastAccess;
    //     this.enrolDate = enrolDate;
    //     this.enabled = enabled;
    //     this.type = type;
    // }

    /**
     * This is to to display the user information in a formatted way.
     * @return the user information in a formatted string.
     */
    @Override
    public String toString() {
        return "User Info for: " + id +
                "\n\tName: " + firstName + " " + lastName + " (" + emailAddress + ")" +
                "\n\tCreated on: " + DF.format(enrolDate) +
                "\n\tLast access: " + DF.format(lastAccess);
    }


    /**
     * This method is used to display the user details
     */
    public void dump(){
        System.out.println(this.toString());
    }

    /**
     * This method is used to verify the ID and check its length
     * @param id user's id
     * @return true if the ID is valid, false otherwise
     */
    public static boolean verifyId(long id) {
        return id >= CollegeInterface.MINIMUM_ID_NUMBER && id <= CollegeInterface.MAXIMUM_ID_NUMBER;
    }





}
