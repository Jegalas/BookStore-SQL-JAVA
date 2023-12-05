import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookstoreManager {

    private String url = "jdbc:postgrsql://hostname:port/databaseName";
    private String user = "username";
    private String password = "password";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Insert a new book
    public void insertBook(String title, int authorId, double price, int stock) {
        String sql = "INSERT INTO Books (Title, AuthorID, Price, Stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, authorId);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieve all books with author and order information
    public void retrieveBooks() {
        String sql = "SELECT b.Title, a.Name, b.Stock "
                   + "FROM Books b JOIN Authors a ON b.AuthorID = a.AuthorID";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Title: " + rs.getString("Title") +
                                   ", Author: " + rs.getString("Name") +
                                   ", Stock: " + rs.getInt("Stock"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update book details
    public void updateBook(int bookId, String title, int stock) {
        String sql = "UPDATE Books SET Title = ?, Stock = ? WHERE BookID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, stock);
            pstmt.setInt(3, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete a book
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Books WHERE BookID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
