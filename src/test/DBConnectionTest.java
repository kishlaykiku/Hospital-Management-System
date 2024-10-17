package test;
import util.DBConnection;
import java.sql.Connection;

public class DBConnectionTest {
    public static void main(String[] args) {
        Connection connection = DBConnection.getDBConn();

        if (connection != null) {
            System.out.println("Connection successful!");
            DBConnection.dbClose();
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}