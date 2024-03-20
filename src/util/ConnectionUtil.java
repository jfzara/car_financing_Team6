package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static Connection conn = null;

    private ConnectionUtil() {

    }

    public static Connection getConnection() {

        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Using a previously made connection");
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Properties prop = loadDatabaseProperties();
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
            System.out.println("Established Connection to Database!");
        } catch (SQLException e) {
            System.out.println("Cannot establish connection");
            e.printStackTrace();
            throw new RuntimeException("Failed to establish database connection", e);
        }

        return conn;
    }

    private static Properties loadDatabaseProperties() {

        try (InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println(" unable to find application.properties");
                throw new RuntimeException("application.properties file not found ");
            }

            prop.load(input);
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading database properties", e);
        }
    }
}
