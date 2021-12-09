package ua.lviv.iot.dal.presistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SqlConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ubergym";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection setConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception:" + sqlException.getMessage());
            System.out.println("SQL State:" + sqlException.getSQLState());
            System.out.println("Vendor Error:" + sqlException.getErrorCode());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                System.out.println("SQL Exception:" + sqlException.getMessage());
                System.out.println("SQL State:" + sqlException.getSQLState());
                System.out.println("Vendor Error:" + sqlException.getErrorCode());
            }
        }
    }
}
