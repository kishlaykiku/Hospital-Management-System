package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString(String propertyFileName) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(propertyFileName)) {
            properties.load(input);

            String hostname = properties.getProperty("hostname");
            String port = properties.getProperty("port");
            String dbname = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            // Build and return the connection string
            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
