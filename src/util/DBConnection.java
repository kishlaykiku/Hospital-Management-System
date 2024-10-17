package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.DatabaseConnectionException;

public class DBConnection {

    private static Connection conn;

    public static Connection getDBConn() {
        try {
            // Load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionString = PropertyUtil.getPropertyString("resources/db.properties");

            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseConnectionException("Failed to establish database connection.", e);
        }

        return conn;
    }

    public static void dbClose() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}