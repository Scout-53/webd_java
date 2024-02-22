/**
 * DataBaseConnect - Used to manage database connection to the database
 * @author Shan Patel
 * @version 1.0
 * @since 1.0
*/

package webd4201patels;

import java.sql.*;

/**
 * This class is used to manage the database connection to the database
 */
public class DatabaseConnect {
    /**
     * Location of the database
     */
    static String url = "jdbc:postgresql://127.0.0.1:5432/patels_db";

    /**
     * Connection object to connect to the database
     */
    static Connection aConnection;

    /**
     * Database user
     */
    static String user = "patels";

    /**
     * Database password
     */
    static String password = "100900902";

    /**
     * This method is used to initialize the database connection
     * 
     * @return Connection object
     */
    public static Connection initialize() {
        try {
            /**
             * Load the driver
             */
            Class.forName("org.postgresql.Driver");

            /**
             * Create a database connection instance
             */
            aConnection = DriverManager.getConnection(url, user, password);

        }
        /**
         * A try...catch if there is any error in the driver import so that the error
         * can be caught and displayed
         */
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        /**
         * A try...catch if there is any error in the database connection like db_name,
         * db_user, db_password
         */
        catch (SQLException e) {
            System.out.println(e);
        }

        return aConnection;
    }

    /**
     * This method is used to terminate the database connection
     */
    public static void terminate() {
        /**
         * Try...catch to catch any error while closing the database connection
         */
        try {
            aConnection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
