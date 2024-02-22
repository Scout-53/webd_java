/**
 * This is the student data access class that is used to perform the CRUD operations on the student table in the database as well as to retrieve the student data from the database.
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
 * 
*/

package webd4201patels;

import java.util.Vector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 * This is the student data access class that is used to perform the CRUD
 * operations on the student table in the database as well as to retrieve the
 * student data from the database.
 */
public class StudentDA {

    /**
     * A vector of students to store the student data
     */
    static Vector<Student> students = new Vector<Student>();
    /**
     * A connection object to connect to the database
     */
    static Connection aConnection;
    /**
     * A statement object to execute the SQL queries
     */
    static Statement aStatement;
    /**
     * A student object
     */
    static Student aStudent;

    /**
     * A prepared statement to retrieve the student data from the database
     */
    static PreparedStatement studentRetrieve;
    /**
     * A prepared statement to retrieve all the student data from the database
     */
    static PreparedStatement studentRetrieveAll;
    /**
     * A prepared statement to delete the student data from the database
     */
    static PreparedStatement studentDeleteUsers;
    /**
     * A prepared statement to delete the student data from the database
     */
    static PreparedStatement studentDeleteStudents;
    /**
     * A prepared statement to insert the student data into users
     */
    static PreparedStatement studentInsertUsers;
    /**
     * A prepared statement to insert the student data into students
     */
    static PreparedStatement studentInsertStudents;
    /**
     * A prepared statement to update the student data in the users table
     */
    static PreparedStatement studentUpdateUsers;
    /**
     * A prepared statement to update the student data in the students table
     */
    static PreparedStatement studentUpdateStudents;

    /**
     * A long variable to store the student id
     */
    static long id;
    /**
     * A string variable to store the student password
     */
    static String password;
    /**
     * A string variable to store the student first name
     */
    static String firstName;
    /**
     * A string variable to store the student last name
     */
    static String lastName;
    /**
     * A string variable to store the student email address
     */
    static String emailAddress;
    /**
     * A date variable to store the student last access date
     */
    static java.util.Date lastAccess;
    /**
     * A date variable to store the student enrol date
     */
    static java.util.Date enrolDate;
    /**
     * A boolean variable to store the student enabled status
     */
    static boolean enabled;
    /**
     * A char variable to store the student type
     */
    static char type;
    /**
     * A string variable to store the student program code
     */
    static String programCode;
    /**
     * A string variable to store the student program description
     */
    static String programDescription;
    /**
     * An integer variable to store the student current year
     */
    static int year;
    /**
     * A vector of marks to store the student marks
     */
    static Vector<Mark> marks;
    /**
     * A simple date formate to format the date in the SQL format
     */
    private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * This method is used to initialize the database connection
     * 
     * @param c - Connection object
     */
    public static void initialize(Connection c) {
        try {
            aConnection = c;
            aStatement = aConnection.createStatement();

            // update week4
            /*
             * So now, here since both the users and students are interconnected with fk
             * const., we need to retrieve the data from both the tables and then join them
             * to get the student data.
             */
            String sqlRetrieve = "SELECT Users.Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, ProgramCode, ProgramDescription, Year FROM Users , Students WHERE Users.id = Students.id AND Users.Id = ?";
            studentRetrieve = aConnection.prepareStatement(sqlRetrieve);

            /*
             * To retrieve all the students from the database
             * Also for this assignment we are not supposed to create a method for the same
             * but I have created it so that for the upcoming assignments it can be used.
             */
            String sqlRetrieveAll = "SELECT Users.Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, ProgramCode, ProgramDescription, Year FROM Users INNER JOIN Students ON Users.id = Students.id";
            studentRetrieveAll = aConnection.prepareStatement(sqlRetrieveAll);

            /*
             * insert the student data into the users and students table again since both
             * are interconnected with fk const.
             */
            String sqlStudentInsertUsers = "INSERT INTO Users (Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            studentInsertUsers = aConnection.prepareStatement(sqlStudentInsertUsers);
            String sqlStudentInsertStudents = "INSERT INTO Students (Id, ProgramCode, ProgramDescription, Year) VALUES (?, ?, ?, ?)";
            studentInsertStudents = aConnection.prepareStatement(sqlStudentInsertStudents);

            /*
             * delete the student data from the users and students table. But since student
             * is connected to user, we first need to delete the student from the students
             * table and then from the users table.
             */
            String sqlStudentDeleteStudents = "DELETE FROM Students WHERE Id = ?;";
            studentDeleteStudents = aConnection.prepareStatement(sqlStudentDeleteStudents);

            /**
             * As mentioned above, we first need to delete the student from the students
             * then users
             */
            String sqlStudentDeleteUsers = "DELETE FROM Users WHERE Id = ?;";
            studentDeleteUsers = aConnection.prepareStatement(sqlStudentDeleteUsers);

            /*
             * update the data in student and user, now it will be exact opposite of the
             * user.
             */
            String sqlStudentUpdateUsers = "UPDATE Users SET Password=?, FirstName=?, LastName=?, EmailAddress=?, " +
                    "LastAccess=?, EnrolDate=?, Enabled=?, Type=? WHERE Id = ?";
            studentUpdateUsers = aConnection.prepareStatement(sqlStudentUpdateUsers);
            String sqlStudentUpdateStudents = "UPDATE Students SET ProgramCode=?, ProgramDescription=?, Year=? " +
                    "WHERE Id = ?";
            studentUpdateStudents = aConnection.prepareStatement(sqlStudentUpdateStudents);

            /* try and catch block to catch the exception if any */
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * This method is used to terminate the database connection
     */
    public static void terminate() {
        try {
            /* Close all the prepared statements and the connection */
            studentRetrieve.close();
            studentRetrieveAll.close();
            studentDeleteStudents.close();
            studentDeleteUsers.close();
            studentInsertUsers.close();
            studentInsertStudents.close();
            studentUpdateUsers.close();
            studentUpdateStudents.close();
            aStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * This method is used to retrieve student data from the database with respect
     * to the student id.
     * 
     * @param id - long student id
     * @return - Student object
     * @throws NotFoundException - throws the exception if the student with the
     *                           given id is not found in the database
     */
    public static Student retrieve(Long id) throws NotFoundException, InvalidUserDataException {
        // a null student instance is created to store the student data
        aStudent = null;
        try {
            /*
             * Execute the query against the db specified and since to my understanding, we
             * are passing just one value i.e id so 1 for the key and id parameter
             */
            studentRetrieve.setLong(1, id);
            ResultSet rs = studentRetrieve.executeQuery();
            /*
             * If the result set has the data then retrieve the data from the result set and
             * store it in the student object
             */
            boolean gotIt = rs.next();
            if (gotIt) {
                id = rs.getLong("id");
                password = rs.getString("password");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                emailAddress = rs.getString("emailAddress");
                lastAccess = rs.getDate("lastAccess");
                enrolDate = rs.getDate("enrolDate");
                enabled = rs.getBoolean("enabled");
                type = rs.getString("type").charAt(0);
                programCode = rs.getString("programCode");
                programDescription = rs.getString("programDescription");
                year = rs.getInt("year");
                /* Create a new student object with the retrieved data */
                try {
                    aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate,
                            enabled, type, programCode, programDescription, year);
                } catch (InvalidUserDataException e) {

                    System.out.println("User Data entered is invalid. Please enter correct set of data");
                }
            } else {
                throw new NotFoundException("Student with id " + id + " not found.");

            }
            /* Close the result set */
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        /* return the student object */
        return aStudent;
    }

    /**
     * This method is used to retrieve all the student data from the database
     * 
     * @return - Vector of Student objects
     * @throws DuplicateException - throws the exception if the student with the
     *                            given id is already found in the database
     * @param aStudent - student object from the student class.
     */
    public static boolean create(Student aStudent) throws DuplicateException, InvalidUserDataException {
        int UserInserted = 0;
        int StudentInserted = 0;
//        boolean inserted = false;
        /**
         * retrieve the student attributes
         */
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();

        /* hash the password - used directly from the customerDA.java file */
        String hashedPassword = "";
        try {
            hashedPassword = get_SHA_1_SecurePassword(password);
        } catch (NoSuchAlgorithmException e1) {

            e1.printStackTrace();
        }

        // see if the student already exists in the database
        try {
            retrieve(id);
            throw (new DuplicateException("Problem with creating a new Student record. Student with id " + id
                    + " already exists in the system."));
        } catch (NotFoundException e) {
            try {
                studentInsertUsers.setLong(1, id);
                studentInsertUsers.setString(2, hashedPassword);
                studentInsertUsers.setString(3, firstName);
                studentInsertUsers.setString(4, lastName);
                studentInsertUsers.setString(5, emailAddress);

                studentInsertUsers.setDate(6, java.sql.Date.valueOf(SQL_DF.format(lastAccess)));
                studentInsertUsers.setDate(7, java.sql.Date.valueOf(SQL_DF.format(enrolDate)));
                // studentInsertUsers.setDate(6, new java.sql.Date(lastAccess.getTime()));
                // studentInsertUsers.setDate(7, new java.sql.Date(enrolDate.getTime()));
                studentInsertUsers.setBoolean(8, enabled);
                studentInsertUsers.setString(9, String.valueOf(type));
                UserInserted = studentInsertUsers.executeUpdate();
                studentInsertStudents.setLong(1, id);
                studentInsertStudents.setString(2, programCode);
                studentInsertStudents.setString(3, programDescription);
                studentInsertStudents.setInt(4, year);
                StudentInserted = studentInsertStudents.executeUpdate();
//                inserted = true;
            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
//                inserted = false;
            }

            return UserInserted + StudentInserted == 2 ? true : false;
        }

        /*
         * try and catch block to catch the exception if any while insertion of the
         * value to the db
         */
        // try {
        // /*
        // * Since multiple values will be inserted into the database so the key values
        // * increments with respect to the attributes
        // */
        // studentInsertUsers.setLong(1, id);
        // studentInsertUsers.setString(2, hashedPassword);
        // studentInsertUsers.setString(3, firstName);
        // studentInsertUsers.setString(4, lastName);
        // studentInsertUsers.setString(5, emailAddress);
        // studentInsertUsers.setDate(6, new java.sql.Date(lastAccess.getTime()));
        // studentInsertUsers.setDate(7, new java.sql.Date(enrolDate.getTime()));
        // studentInsertUsers.setBoolean(8, enabled);
        // studentInsertUsers.setString(9, String.valueOf(type));
        // /* Execute the query since here value insertion for the user is done. */
        // studentInsertUsers.executeUpdate();
        // /* Now, insert the student data into the students table */
        // studentInsertStudents.setLong(1, id);
        // studentInsertStudents.setString(2, programCode);
        // studentInsertStudents.setString(3, programDescription);
        // studentInsertStudents.setInt(4, year);
        // /* Execute the query since here value insertion for the student is done. */
        // studentInsertStudents.executeUpdate();
        // /* set the inserted to true if the data is inserted successfully */
        // inserted = true;
        // /* catch the exception if any */
        // } catch (SQLException e) {
        // System.out.println(e.getMessage());
        // inserted = false;
        // }
        // /* return the inserted value */
        // return inserted;
    }

    /**
     * This method is used to delete the student data from the database
     * 
     * @return - Vector of Student objects
     * @throws NotFoundException - throws the exception if the student with the
     *                           given id is not found in the database
     * @param aStudent - student object from the student class.
     */
    public static int delete(Student aStudent) throws NotFoundException, InvalidUserDataException {

        /* A variable to store the number of records deleted */
        int records = 0;

        /* retrieve the student id */
        id = aStudent.getId();
        // id = aUser.getId();
        /* try and catch block to catch the exception if any */
        try {
            /* Check if the id exists otherwise execute the query. */
            retrieve(id);
            /*
             * Since student is connected to user, we first need to delete the student from
             * the students table and then from the users table.
             */
            studentDeleteStudents.setLong(1, id);
            studentDeleteUsers.setLong(1, id);
            /* Execute the query */
            records = studentDeleteStudents.executeUpdate();
            studentDeleteUsers.executeUpdate();
        } catch (NotFoundException e) {
            /* throw the exception if the student is not found */
            throw new NotFoundException("Customer with phone number " + id + " cannot be deleted, does not exist.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        /* return the number of records deleted */
        return records;
    }

    /**
     * This method is used to update the student data in the database
     * 
     * @return - records number of records updated
     * @throws NotFoundException - throws the exception if the student with the
     *                           given id is not found in the database
     * @param aStudent - student object from the student class.
     */
    public static int update(Student aStudent) throws NotFoundException, InvalidUserDataException {
        int records = 0;
        /**
         * retrieve the student attributes
         */
        id = aStudent.getId();
        password = aStudent.getPassword();
        firstName = aStudent.getFirstName();
        lastName = aStudent.getLastName();
        emailAddress = aStudent.getEmailAddress();
        lastAccess = aStudent.getLastAccess();
        enrolDate = aStudent.getEnrolDate();
        enabled = aStudent.isEnabled();
        type = aStudent.getType();
        programCode = aStudent.getProgramCode();
        programDescription = aStudent.getProgramDescription();
        year = aStudent.getYear();

        /* hash the password - used directly from the customerDA.java file */
        String hashedPassword = "";
        try {
            hashedPassword = get_SHA_1_SecurePassword(password);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        try {
            /* Check if the id exists or not. */
            retrieve(id);
            /*
             * Since, we are retrieving value of id we directly start from the password
             * field.
             */
            studentUpdateUsers.setString(1, hashedPassword);
            studentUpdateUsers.setString(2, firstName);
            studentUpdateUsers.setString(3, lastName);
            studentUpdateUsers.setString(4, emailAddress);
            studentUpdateUsers.setDate(5, new java.sql.Date(lastAccess.getTime()));
            studentUpdateUsers.setDate(6, new java.sql.Date(enrolDate.getTime()));
            studentUpdateUsers.setBoolean(7, enabled);
            studentUpdateUsers.setString(8, String.valueOf(type));
            studentUpdateUsers.setLong(9, id);
            /* Execute the query */
            records = studentUpdateUsers.executeUpdate();
            studentUpdateStudents.setString(1, programCode);
            studentUpdateStudents.setString(2, programDescription);
            studentUpdateStudents.setInt(3, year);
            studentUpdateStudents.setLong(4, id);
            /* Execute the query */
            records += studentUpdateStudents.executeUpdate();
            /* catch the exception if any */
        } catch (NotFoundException e) {
            throw new NotFoundException(
                    "Customer with phone number " + id + " cannot be updated, does not exist in the system.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        /* return the number of records updated */
        return records;
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
