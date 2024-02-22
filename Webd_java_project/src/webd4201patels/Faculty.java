package webd4201patels;

/**
 * Faculty - This is the faculty.java class file extends from User class.
 * 
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Faculty extends User {

    /** Constant for school code */
    public static final String DEFAULT_SCHOOL_CODE = "SET";

    /** Constant for school description */
    public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";

    /** Constant for office */
    public static final String DEFAULT_OFFICE = "H-140";

    /** Constant for phone extension */
    public static final int DEFAULT_PHONE_EXTENSION = 1234;

    /** Instance variable for School code */
    private String schoolCode;

    /** Instance variable for School description */
    private String schoolDescription;

    /** Instance variable for Office */
    private String office;

    /** Instance variable for Extension */
    private int extension;

    /**
     * getter for the school code
     * 
     * @return school code for the faculty
     */
    public String getSchoolCode() {
        return schoolCode;
    }

    /**
     * setter for the school code
     * 
     * @param schoolCode for the faculty
     */
    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    /**
     * getter for the school description
     * 
     * @return school description for the faculty
     */
    public String getSchoolDescription() {
        return schoolDescription;
    }

    /**
     * setter for the school description
     * 
     * @param schoolDescription for the faculty
     */
    public void setSchoolDescription(String schoolDescription) {
        this.schoolDescription = schoolDescription;
    }

    /**
     * getter for the office
     * 
     * @return office for the faculty
     */
    public String getOffice() {
        return office;
    }

    /**
     * setter for the office
     * 
     * @param office for the faculty
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * getter for the extension
     * 
     * @return extension for the faculty
     */
    public int getExtension() {
        return extension;
    }

    /**
     * setter for the extension
     * 
     * @param extension for the faculty
     */
    public void setExtension(int extension) {
        this.extension = extension;
    }

    /**
     * Parameterized constructor for faculty
     * 
     * @param id                in user's id
     * @param password          in user's password
     * @param firstName         in user's first name
     * @param lastName          in user's last name
     * @param emailAddress      in user's email address
     * @param lastAccess        in user's last access
     * @param enrolDate         in user's enrol date
     * @param enabled           in user's enabled
     * @param type              in user's type
     * @param schoolCode        user's school code
     * @param schoolDescription user's school description
     * @param office            user's office
     * @param extension         user's extension
     * @throws InvalidUserDataException when the user data is invalid
     */
    public Faculty(long id, String password, String firstName, String lastName, String emailAddress,
            Date lastAccess, Date enrolDate, boolean enabled, char type,
            String schoolCode, String schoolDescription, String office, int extension) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        this.schoolCode = schoolCode;
        this.schoolDescription = schoolDescription;
        this.office = office;
        this.extension = extension;

    }

    /**
     * Default constructor for faculty
     * 
     * @param schoolCode        for faculty
     * @param schoolDescription for faculty
     * @param office            for faculty
     * @param extension         for faculty
     * @throws InvalidUserDataException when the user data is invalid.
     */
    public Faculty(String schoolCode, String schoolDescription, String office, int extension)
            throws InvalidUserDataException {
        super();
        setSchoolCode(DEFAULT_SCHOOL_CODE);
        setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
        setOffice(DEFAULT_OFFICE);
        setExtension(DEFAULT_PHONE_EXTENSION);

    }

    /**
     * override method for getTypeForDisplay
     */
    @Override
    public String getTypeForDisplay() {
        return "Faculty";
    }

    /**
     * @return string for faculty using the getter methods by overriding the
     *         toString method (users info and adding the faculty info)
     *         return for faculty
     */
    public String toString() {
        String output = super.toString();

        output = output.replaceAll("User", getTypeForDisplay());

        // Increment the string and return the incremented variable.
        output += "\n\t" + getSchoolDescription() + " (" + getSchoolCode() + ")" +
                "\n\tOffice: " + getOffice() +
                "\n\t(905)721-2000 x" + getExtension();

        return output;
    }

    /**
     * Method to initialize the facultyDA connections
     * 
     * @param c - connection object
     * @throws SQLException - if there is an error in the SQL
     */
    public static void initialize(Connection c) throws SQLException {
        FacultyDA.initialize(c);
    }

    /**
     * Method to terminate the facultyDA connections
     * 
     * @throws SQLException - if there is an error in the SQL
     */
    public static void terminate() throws SQLException {
        FacultyDA.terminate();
    }

    /**
     * Method to retrieve the faculty by the key
     * 
     * @param key - key for the faculty
     * @return - faculty object
     * @throws NotFoundException        - if the faculty is not found
     * @throws InvalidUserDataException - if the user data is invalid
     * @throws InvalidNameException     - if the name is invalid
     * @throws InvalidIdException       - if the id is invalid
     * @throws InvalidPasswordException - if the password is invalid
     */
    public static Faculty retrieve(long key) throws NotFoundException, InvalidUserDataException, InvalidNameException,
            InvalidIdException, InvalidPasswordException {
        return FacultyDA.retrieve(key);
    }

    /**
     * Method to retrieve the faculty by the email
     * 
     * @throws DuplicateException       - if the faculty is duplicate
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public void create() throws DuplicateException, InvalidUserDataException {
        FacultyDA.create(this);
    }

    /**
     * Method to delete the faculty
     * 
     * @throws InvalidUserDataException - if the user data is invalid
     * @throws NotFoundException        - if the faculty is not found
     */
    public void delete() throws InvalidUserDataException, NotFoundException {
        FacultyDA.delete(this);
    }

    /**
     * Method to update the faculty
     * 
     * @throws NotFoundException        - if the faculty is not found
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public void update() throws NotFoundException, InvalidUserDataException {
        FacultyDA.update(this);
    }

}
