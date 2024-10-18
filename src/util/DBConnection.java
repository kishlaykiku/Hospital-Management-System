package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.DatabaseConnectionException;

/**
 * Utility class for managing database connections.
 * Provides methods to establish and close connections to the database.
*/
public class DBConnection {

    private static Connection conn;

    /**
     * Establishes a connection to the database using details from the properties file.
     * Loads the MySQL JDBC driver and utilizes the connection string generated from PropertyUtil.
     * 
     * @return a Connection object to interact with the database.
     * @throws DatabaseConnectionException if a connection cannot be established.
    */
    public static Connection getDBConn() {

        try {

            // Loading the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Retrieving the connection string from PropertyUtil
            String connectionString = PropertyUtil.getPropertyString("resources/db.properties");

            // Establishing the database connection
            conn = DriverManager.getConnection(connectionString);
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseConnectionException("Failed to establish database connection.", e);
        }
        return conn;
    }

    /**
     * Closes the database connection if it is open.
     * Ensures that the connection is properly released when no longer needed.
    */
    public static void dbClose() {

        try {

            if (conn != null) {
                
                conn.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}