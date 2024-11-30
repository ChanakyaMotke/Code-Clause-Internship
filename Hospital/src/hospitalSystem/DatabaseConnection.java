package hospitalSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Include the database name in the connection URL
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hospitaldb";
    private static final String USER = "new";
    private static final String PASSWORD = "YES";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
