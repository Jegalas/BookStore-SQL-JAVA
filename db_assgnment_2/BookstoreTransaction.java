import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookstoreTransaction {

    private String url = "jdbc:postgresql://localhost:5432/dataassign2";
    private String user = "postgres";
    private String password = "sadiq129";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    
    public void makeOrder(int customerId, int bookId, int quantity) {
        String checkStockSql = "SELECT Stock, Price FROM Books WHERE BookID = ?";
        String insertOrderSql = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES (?, NOW(), ?)";
        String updateBookSql = "UPDATE Books SET Stock = Stock - ? WHERE BookID = ?";

        try (Connection conn = this.connect()) {
           
            conn.setAutoCommit(false);

            try (PreparedStatement checkStockStmt = conn.prepareStatement(checkStockSql)) {
                checkStockStmt.setInt(1, bookId);
                ResultSet rs = checkStockStmt.executeQuery();

                if (rs.next()) {
                    int stock = rs.getInt("Stock");
                    double price = rs.getDouble("Price");

                    if (stock >= quantity) {
                        double totalAmount = price * quantity;

                    
                        try (PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderSql)) {
                            insertOrderStmt.setInt(1, customerId);
                            insertOrderStmt.setDouble(2, totalAmount);
                            insertOrderStmt.executeUpdate();
                        }

                        
                        try (PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {
                            updateBookStmt.setInt(1, quantity);
                            updateBookStmt.setInt(2, bookId);
                            updateBookStmt.executeUpdate();
                        }

                        
                        conn.commit();
                    } else {
                        throw new SQLException("Not enough books in stock");
                    }
                } else {
                    throw new SQLException("Book not found");
                }
            } catch (SQLException e) {
                System.out.println("Transaction failed: " + e.getMessage());
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    
}
