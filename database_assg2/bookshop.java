import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bookshop{

    public static void main(String[] args) {
        // Database credentials and URL
        String url = "jdbc:postgresql://localhost:5432/DatabaseName";
        String user = "username";
        String password = "password";

        try {
            // Attempting to establish a connection to the database
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Successfully connected to the database.");
               
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
