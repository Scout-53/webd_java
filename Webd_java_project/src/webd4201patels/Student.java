package webd4201patels;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 * Student - This is the student.java class file extends from User class.
 * 
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 */

public class Student extends User {

    /** Constant for program code */
    public static final String DEFAULT_PROGRAM_CODE = "UNDC";

    /** Constant for program description */
    public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";

    /** Constant for year */
    public static final int DEFAULT_YEAR = 1;

    /** Instance variable for program code */
    private String programCode;

    /** Instance variable for program description */
    private String programDescription;

    /** Instance variable for year */
    private int year;

    /** Instance variable for marks */
    private Vector<Mark> marks;

    /**
     * getter for the program code
     * 
     * @return program code for the student
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * setter for the program code
     * 
     * @param programCode for the student
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * getter for the program description
     * 
     * @return program description for the student
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * setter for the program description
     * 
     * @param programDescription for the student
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * getter for the year
     * 
     * @return year for the student
     */
    public int getYear() {
        return year;
    }

    /**
     * setter for the year
     * 
     * @param year for the student
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * getter for the marks vector
     * 
     * @return marks for the student
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    /**
     * setter for the mark vector
     * 
     * @param marks for the student
     */
    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }
    //
    // public Student(String programCode, String programDescription, int year,
    // Vector<Mark> marks) {
    // this.programCode = programCode;
    // this.programDescription = programDescription;
    // this.year = year;
    // this.marks = marks;
    // }
    //
    // public Student(long id, String password, String firstName, String lastName,
    // String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char
    // type, String programCode, String programDescription, int year) {
    // super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
    // enabled, type);
    // this.programCode = programCode;
    // this.programDescription = programDescription;
    // this.year = year;
    // this.marks = marks;
    // }
    //
    // public Student() {
    // super(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME,
    // DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,
    // new Date(), new Date(), DEFAULT_TYPE,
    // DEFAULT_ENABLE_STATUS, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION,
    // DEFAULT_YEAR);
    //
    //
    // }

    // public Student(String programCode, String programDescription, int year,
    // Vector<Mark> marks) {
    // this.programCode = programCode;
    // this.programDescription = programDescription;
    // this.year = year;
    // this.marks = marks;
    // }
    //
    // public Student(long id, String password, String firstName, String lastName,
    // String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char
    // type, String programCode, String programDescription, int year) {
    // super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
    // enabled, type);
    // this.programCode = programCode;
    // this.programDescription = programDescription;
    // this.year = year;
    // this.marks = marks;
    // }
    //
    // public Student() {
    // super(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME,
    // DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,
    // new Date(), new Date(), DEFAULT_TYPE,
    // DEFAULT_ENABLE_STATUS, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION,
    // DEFAULT_YEAR);
    //
    //
    // }

    /**
     * Parameterized constructor that takes one argument for all attributes of the
     * student and sets the values accordingly.
     * 
     * @param id                 user's id
     * @param password           user's password
     * @param firstName          user's first name
     * @param lastName           user's last name
     * @param emailAddress       user's email address
     * @param lastAccess         user's last access
     * @param enrolDate          user's enrol date
     * @param enabled            user's enabled
     * @param c                  user's type
     * @param programCode        user's program code
     * @param programDescription user's program description
     * @param year               user's current year
     * @throws InvalidUserDataException when the user data is invalid
     */
    public Student(long id, String password, String firstName, String lastName, String emailAddress,
            Date lastAccess, Date enrolDate, boolean enabled, char c,
            String programCode, String programDescription, int year) throws InvalidUserDataException {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, c);
        this.setProgramCode(programCode);
        this.setProgramDescription(programDescription);
        this.setYear(year);
        this.setMarks(marks);
    }

    // public Student(long id, String password, String firstName, String lastName,
    // String emailAddress,
    // Date lastAccess, Date enrolDate, boolean enabled, char type,
    // String programCode, String programDescription, int year, Vector<Mark> marks)
    // throws InvalidUserDataException
    // {
    // this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
    // enabled, type, programCode, programDescription, year);
    // setMarks(marks);
    // }

    /**
     * an overloaded constructor that takes all student attributes accept the vector
     * of marks and sends it to the above constructor with a null value for the
     * marks vector.
     * 
     * @param programCode        student's program code
     * @param programDescription student's program description
     * @param year               student's current year
     * @throws InvalidUserDataException when the user data is invalid
     */
    public Student(String programCode, String programDescription, int year) throws InvalidUserDataException {
        super();
        this.programCode = programCode;
        this.programDescription = programDescription;
        this.year = year;
        this.marks = new Vector<Mark>();

    }

    /**
     * Default constructor that sets the default values for the student attributes
     * and sends them to the above constructor.
     * 
     * @throws InvalidUserDataException when the use data is invalid
     */
    public Student() throws InvalidUserDataException {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,
                new Date(), new Date(), DEFAULT_ENABLE_STATUS, DEFAULT_TYPE,
                DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
    }

    /**
     * This method returns the student's information in a formatted string.
     * 
     * @return student's information
     */
    public String toString() {
        String suffixYear;
        if (getYear() == 1) {
            suffixYear = "st";
        } else if (getYear() == 2) {
            suffixYear = "nd";
        } else if (getYear() == 3) {
            suffixYear = "rd";
        } else {
            suffixYear = "th";
        }

        return "Student Info for:\n\t" + getFirstName() + " " + getLastName() + " (" + getId() + ")" +
                "\n\tCurrently in " + getYear() + suffixYear + " year of \"" + getProgramDescription() + "\" ("
                + getProgramCode() + ")" +
                "\n\tEnrolled: " + DF.format(getEnrolDate());

    }

    /**
     * This method is used to initialize the student data access object.
     * 
     * @param c - Connection object
     * @throws SQLException - if there is an error in the database connection
     */
    public static void initialize(Connection c) throws SQLException {
        StudentDA.initialize(c);
    }

    /**
     * This method is used to terminate the student data access object.
     * 
     * @throws SQLException - if there is an error in the database connection
     */
    public static void terminate() throws SQLException {
        StudentDA.terminate();
    }

    // /**
    // * This method is used to retrieve the student from the database.
    // * @param id - student id
    // * @return Student object
    // * @throws NotFoundException - if the student is not found
    // * @throws SQLException - if there is an error in the database connection
    // */
    // public static Student retrieve(long id) throws NotFoundException,
    // SQLException {
    // return StudentDA.retrieve(id);
    // }

    // /**
    // * instance method to create a student in the database
    // * @return if the student is created
    // * @throws DuplicateException - if the student is already found in the
    // database
    // * @throws SQLException - if there is an error in the database connection
    // */
    // public boolean create() throws DuplicateException, SQLException {
    // return StudentDA.create(this);
    // }

    // /**
    // * instance method to update a student in the database
    // * @return if the student is updated
    // * @throws NotFoundException - if the student is not found
    // * @throws SQLException - if there is an error in the database connection
    // */
    // public int update() throws NotFoundException, SQLException {
    // return StudentDA.update(this);
    // }

    // /**
    // * instance method to delete a student in the database
    // * @return if the student is deleted
    // * @throws NotFoundException - if the student is not found
    // * @throws SQLException - if there is an error in the database connection
    // */
    // public int delete() throws NotFoundException, SQLException {
    // return StudentDA.delete(this);
    // }

    /**
     * This method is used to retrieve the student from the database.
     * 
     * @param key - represents the student id
     * @return - Student object
     * @throws NotFoundException        - if the student is not found
     * @throws InvalidUserDataException - if the user data is invalid
     * @throws InvalidNameException     - if the name is invalid
     * @throws InvalidIdException       - if the id is invalid
     * @throws InvalidPasswordException - if the password is invalid
     */
    public static Student retrieve(long key) throws NotFoundException, InvalidUserDataException, InvalidNameException,
            InvalidIdException, InvalidPasswordException {
        return StudentDA.retrieve(key);
    }

    /**
     * This method is used to create a student in the database.
     * 
     * @throws DuplicateException       - if the student is already found in the
     *                                  database
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public void create() throws DuplicateException, InvalidUserDataException {
        StudentDA.create(this);
    }

    /**
     * This method is used to delete a student in the database.
     * 
     * @throws InvalidUserDataException - if the user data is invalid
     * @throws NotFoundException        - if the student is not found
     */
    public void delete() throws InvalidUserDataException, NotFoundException {
        StudentDA.delete(this);
    }

    /**
     * This method is used to update a student in the database.
     * 
     * @throws NotFoundException        - if the student is not found
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public void update() throws NotFoundException, InvalidUserDataException {
        StudentDA.update(this);
    }

}
