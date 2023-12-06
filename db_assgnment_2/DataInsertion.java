import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInsertion {

    private String url = "jdbc:postgresql://localhost:5432/dataassign2";
    private String user = "postgres";
    private String password = "sadiq129";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Insert a new author
    public void insertAuthor(int authorId, String name) {
        String sql = "INSERT INTO Authors (AuthorID, Name) VALUES (?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, authorId);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("Author inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Author insertion failed: " + e.getMessage());
        }
    }

    // Insert a new customer
    public void insertCustomer(int customerId, String name, String email) {
        String sql = "INSERT INTO Customers (CustomerID, Name, Email) VALUES (?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Customer inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Customer insertion failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DataInsertion insertion = new DataInsertion();

        // Inserting an author
        insertion.insertAuthor(1, "John Doe");

        // Inserting a customer
        insertion.insertCustomer(1, "Alice", "alice@example.com");
    }
}
