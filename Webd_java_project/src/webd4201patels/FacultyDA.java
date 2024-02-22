package webd4201patels;

/**
 * FacultyDA - This is the faculty data access class file for the CRUD operations in the database.
 * @author - Shan Patel
 * @version 1.0
 * @since 1.0
 */
import java.util.Vector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class FacultyDA {

    /**
     * A SimpleDateFormat object to format the date in SQL format
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * A Vector of Faculty to store the faculty data
     */
    static Vector<Faculty> facultyVector = new Vector<Faculty>();

    /**
     * A Connection object to store the connection to the database
     */
    static Connection aConnection;

    /**
     * A Statement object to store the statement for the database
     */
    static Statement aStatement;
    /**
     * A Faculty object to store the faculty data
     */
    static Faculty aFaculty;

    /**
     * A PreparedStatement object to store the prepared statement for the retrieve
     */
    static PreparedStatement facultyRetrieve;

    /**
     * A PreparedStatement object to store the prepared statement for the update -
     * users
     */
    static PreparedStatement facultyUpdateUsers;

    /**
     * A PreparedStatement object to store the prepared statement for the update -
     * faculty
     */
    static PreparedStatement facultyUpdateFaculty;

    /**
     * A PreparedStatement object to store the prepared statement for the delete -
     * faculty
     */
    static PreparedStatement facultyDeleteFaculty;
    /**
     * A PreparedStatement object to store the prepared statement for the delete -
     * users
     */
    static PreparedStatement facultyDeleteUsers;

    /**
     * A PreparedStatement object to store the prepared statement for the insert -
     * users
     */
    static PreparedStatement facultyInsertUsers;

    /**
     * A PreparedStatement object to store the prepared statement for the insert -
     * faculty
     */
    static PreparedStatement facultyInsertFaculty;

    /**
     * A long variable to store the faculty id
     */
    static long id;
    /**
     * A string variable to store the faculty password
     */
    static String password;
    /**
     * A string variable to store the faculty first name
     */
    static String firstName;
    /**
     * A string variable to store the faculty last name
     */
    static String lastName;
    /**
     * A string variable to store the faculty email address
     */
    static String emailAddress;
    /**
     * A date variable to store the faculty last access date
     */
    static java.util.Date lastAccess;
    /**
     * A date variable to store the faculty enrol date
     */
    static java.util.Date enrolDate;
    /**
     * A boolean variable to store the faculty enabled status
     */
    static boolean enabled;
    /**
     * A char variable to store the faculty type
     */
    static char type;
    /**
     * A string variable to store the faculty school code
     */
    static String schoolCode;

    /**
     * A string variable to store the faculty school description
     */
    static String schoolDescription;

    /**
     * A string variable to store the faculty office
     */
    static String office;

    /**
     * An int variable to store the faculty extension
     */
    static int extension;

    /**
     * This method is used to initialize the connection and the prepared statements
     * 
     * @param c - Connection object
     */
    public static void initialize(Connection c) {
        try {
            aConnection = c;
            aStatement = aConnection.createStatement();
            /**
             * For the retrieve operation
             */
            String sqlRetrieve = "SELECT Users.Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, schoolCode, schoolDescription, Office, Extension FROM Users , Faculty WHERE Users.id = Faculty.id AND Users.Id = ?";
            facultyRetrieve = aConnection.prepareStatement(sqlRetrieve);

            /**
             * For the insert user operation
             */
            String sqlFacultyInsertUsers = "INSERT INTO Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            facultyInsertUsers = aConnection.prepareStatement(sqlFacultyInsertUsers);
            String sqlFacultyInsertFaculty = "INSERT INTO Faculty (Id, schoolCode, schoolDescription, Office, Extension) VALUES (?, ?, ?, ?, ?)";
            facultyInsertFaculty = aConnection.prepareStatement(sqlFacultyInsertFaculty);

            /**
             * For the delete operation
             */
            String sqlFacultyDeleteFaculty = "DELETE FROM Faculty WHERE Id = ?;";
            facultyDeleteFaculty = aConnection.prepareStatement(sqlFacultyDeleteFaculty);
            String sqlFacultyDeleteUsers = "DELETE FROM Users WHERE Id = ?;";
            facultyDeleteUsers = aConnection.prepareStatement(sqlFacultyDeleteUsers);

            /**
             * For the update operation
             */
            String sqlFacultyUpdateUsers = "UPDATE Users SET Password=?, FirstName=?, LastName=?, EmailAddress=?, " +
                    "LastAccess=?, EnrolDate=?, Enabled=?, Type=? WHERE Id = ?";
            facultyUpdateUsers = aConnection.prepareStatement(sqlFacultyUpdateUsers);
            String sqlFacultyUpdateFaculty = "UPDATE faculty SET schoolCode=?, schoolDescription=?, office=?, extension=? "
                    +
                    "WHERE Id = ?";
            facultyUpdateFaculty = aConnection.prepareStatement(sqlFacultyUpdateFaculty);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    /**
     * This method is used to terminate the connection and the prepared statements
     */
    public static void terminate() {
        try {
            facultyDeleteFaculty.close();
            facultyDeleteUsers.close();
            facultyInsertFaculty.close();
            facultyInsertUsers.close();
            facultyRetrieve.close();
            facultyUpdateFaculty.close();
            facultyUpdateUsers.close();
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * This method is used to retrieve the faculty data from the database
     * 
     * @param id - Long id of the faculty
     * @return - Faculty object
     * @throws NotFoundException        - if the faculty is not found
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public static Faculty retrieve(Long id) throws NotFoundException, InvalidUserDataException {
        aFaculty = null;
        try {
            facultyRetrieve.setLong(1, id);
            ResultSet rs = facultyRetrieve.executeQuery();
            if (rs.next()) {
                id = rs.getLong("Id");
                password = rs.getString("Password");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                emailAddress = rs.getString("EmailAddress");
                lastAccess = rs.getDate("LastAccess");
                enrolDate = rs.getDate("EnrolDate");
                enabled = rs.getBoolean("Enabled");
                type = rs.getString("Type").charAt(0);
                schoolCode = rs.getString("schoolCode");
                schoolDescription = rs.getString("schoolDescription");
                office = rs.getString("Office");
                extension = rs.getInt("Extension");

                try {
                    // faulty object to store the data of the faculty
                    aFaculty = new Faculty(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
                            enabled, type, schoolCode, schoolDescription, office, extension);
                } catch (InvalidUserDataException e) {

                    System.out.println("User Data entered is invalid. Please enter correct set of data");
                }

            } else {
                throw (new NotFoundException("Faculty with id " + id + " not found in the system."));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return aFaculty;
    }

    /**
     * This method is used to create a new faculty record in the database
     * 
     * @param aFaculty - Faculty object
     * @return - boolean value
     * @throws DuplicateException       - if the faculty already exists
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public static boolean create(Faculty aFaculty) throws DuplicateException, InvalidUserDataException {
        int UserInserted = 0;
        int FacultyInserted = 0;

        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = aFaculty.getLastAccess();
        enrolDate = aFaculty.getEnrolDate();
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        schoolCode = aFaculty.getSchoolCode();
        schoolDescription = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        extension = aFaculty.getExtension();

        // hashing the password
        String hashedPassword = "";
        try {
            hashedPassword = get_SHA_1_SecurePassword(password);
        } catch (NoSuchAlgorithmException e1) {

            e1.printStackTrace();
        }

        try {
            retrieve(id);
            throw (new DuplicateException(
                    "Problem Creating a new record.Faculty with id " + id + " already exists in the system."));
        } catch (NotFoundException e) {
            try {
                facultyInsertUsers.setLong(1, id);
                facultyInsertUsers.setString(2, hashedPassword);
                facultyInsertUsers.setString(3, firstName);
                facultyInsertUsers.setString(4, lastName);
                facultyInsertUsers.setString(5, emailAddress);
                facultyInsertUsers.setDate(6, java.sql.Date.valueOf(SQL_DF.format(lastAccess)));
                facultyInsertUsers.setDate(7, java.sql.Date.valueOf(SQL_DF.format(enrolDate)));
                facultyInsertUsers.setBoolean(8, enabled);
                facultyInsertUsers.setString(9, String.valueOf(type));
                // inserting the user data first
                UserInserted = facultyInsertUsers.executeUpdate();

                facultyInsertFaculty.setLong(1, id);
                facultyInsertFaculty.setString(2, schoolCode);
                facultyInsertFaculty.setString(3, schoolDescription);
                facultyInsertFaculty.setString(4, office);
                facultyInsertFaculty.setInt(5, extension);
                // inserting the faculty data
                FacultyInserted = facultyInsertFaculty.executeUpdate();

            } catch (SQLException ee) {
                System.out.println(ee);

            }
            // if both the user and faculty data is inserted then return true
            return UserInserted + FacultyInserted == 2 ? true : false;
        }

    }

    /**
     * This method is used to delete the faculty record from the database
     * 
     * @param aFaculty - Faculty object
     * @return - int value
     * @throws NotFoundException        - if the faculty is not found
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public static int delete(Faculty aFaculty) throws NotFoundException, InvalidUserDataException {

        int record = 0;

        id = aFaculty.getId();

        try {
            retrieve(id);
            facultyDeleteFaculty.setLong(1, id);
            facultyDeleteUsers.setLong(1, id);
            // First delete the faculty data and then the user data
            record = facultyDeleteFaculty.executeUpdate();
            record += facultyDeleteUsers.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Faculty with id " + id + " not found in the system.");
        }

        return record;
    }

    /**
     * This method is used to update the faculty record in the database
     * 
     * @param aFaculty - Faculty object
     * @return - int value
     * @throws NotFoundException        - if the faculty is not found
     * @throws InvalidUserDataException - if the user data is invalid
     */
    public static int update(Faculty aFaculty) throws NotFoundException, InvalidUserDataException {
        int record = 0;

        id = aFaculty.getId();
        password = aFaculty.getPassword();
        firstName = aFaculty.getFirstName();
        lastName = aFaculty.getLastName();
        emailAddress = aFaculty.getEmailAddress();
        lastAccess = aFaculty.getLastAccess();
        enrolDate = aFaculty.getEnrolDate();
        enabled = aFaculty.isEnabled();
        type = aFaculty.getType();
        schoolCode = aFaculty.getSchoolCode();
        schoolDescription = aFaculty.getSchoolDescription();
        office = aFaculty.getOffice();
        extension = aFaculty.getExtension();

        // again hasging the password
        String hashedPassword = "";
        try {
            hashedPassword = get_SHA_1_SecurePassword(password);
        } catch (NoSuchAlgorithmException e1) {

            e1.printStackTrace();
        }

        try {
            retrieve(id);
            facultyUpdateUsers.setString(1, hashedPassword);
            facultyUpdateUsers.setString(2, firstName);
            facultyUpdateUsers.setString(3, lastName);
            facultyUpdateUsers.setString(4, emailAddress);
            facultyUpdateUsers.setDate(5, java.sql.Date.valueOf(SQL_DF.format(lastAccess)));
            facultyUpdateUsers.setDate(6, java.sql.Date.valueOf(SQL_DF.format(enrolDate)));
            facultyUpdateUsers.setBoolean(7, enabled);
            facultyUpdateUsers.setString(8, String.valueOf(type));
            facultyUpdateUsers.setLong(9, id);

            // updating the user data first
            record = facultyUpdateUsers.executeUpdate();

            facultyUpdateFaculty.setString(1, schoolCode);
            facultyUpdateFaculty.setString(2, schoolDescription);
            facultyUpdateFaculty.setString(3, office);
            facultyUpdateFaculty.setInt(4, extension);
            facultyUpdateFaculty.setLong(5, id);
            // updating the faculty data
            record += facultyUpdateFaculty.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Faculty with id " + id + " not found in the system.");
        }

        return record;
    }

    /**
     * This method is used to hash the password using SHA-1 algorithm
     * 
     * @param passwordToHash - String password to be hashed
     * @return - String hashed password
     * @throws NoSuchAlgorithmException - throws the exception if the algorithm is
     *                                  not found
     */
    private static String get_SHA_1_SecurePassword(String passwordToHash) throws NoSuchAlgorithmException {
        String generatedPassword = null;

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(passwordToHash.getBytes());
        byte[] bytesOfGeneratedPassword = md.digest();
        generatedPassword = decToHex(bytesOfGeneratedPassword);
        return generatedPassword;
    }

    /**
     * decToHex(byte[]) - A static method used for converting an array of bytes into
     * a hexadecimal string. This method is used for hashing passwords, a password
     * is converted into its byte values and then provided to this function. The
     * bytes in each index in the array are converted to hexadecimals and then
     * appended to a string.
     * 
     * @param bytes An array of bytes to be converted into a concatenated string of
     *              hexadecimals.
     * @return A string containing hexadecimals.
     */
    private static String decToHex(byte[] bytes) {
        // Declarations
        /**
         * A String used to hold the final concatenated hexadecimals
         */
        String hex = "";
        /**
         * A StringBuilder object used for appending hexadecimals in a single String.
         */
        StringBuilder sb = new StringBuilder();

        // Loop through each byte in the array and convert it to hex
        for (int index = 0; index < bytes.length; index++) {
            sb.append(String.format("%02x", bytes[index]));
        }

        // Assign hex a String value from the concatenated StringBuilder value
        hex = sb.toString();

        // return the final string
        return hex;
    }

}
