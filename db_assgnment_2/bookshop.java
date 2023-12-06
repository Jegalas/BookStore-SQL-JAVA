import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class bookshop {

    public static void main(String[] args) {
        DatabaseMetadata metadata = new DatabaseMetadata();
        BookstoreManager manager = new BookstoreManager();
        BookstoreTransaction transaction = new BookstoreTransaction();
        DataInsertion insertion = new DataInsertion();

        // Establishing database connection - replace with your actual credentials
        String url = "jdbc:postgresql://localhost:5432/dataassign2";
        String user = "postgres";
        String password = "sadiq129";

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(url, user, password)) {

            if (connection != null) {
                System.out.println("Successfully connected to the database.");

                while (true) {
                    System.out.println("\n--- Bookstore Menu ---");
                    System.out.println("1. Display Tables and Columns Info");
                    System.out.println("2. Insert a Book");
                    System.out.println("3. Retrieve Books");
                    System.out.println("4. Update a Book");
                    System.out.println("5. Delete a Book");
                    System.out.println("6. Make an Order");
                    System.out.println("7. Add Author");
                    System.out.println("8. Add Customer");
                    System.out.println("9. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            metadata.displayTablesInfo();
                            break;
                        case 2:
                            System.out.print("Enter Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter Author ID: ");
                            int authorId = scanner.nextInt();
                            System.out.print("Enter Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter Stock: ");
                            int stock = scanner.nextInt();
                            manager.insertBook(title, authorId, price, stock);
                            break;
                        case 3:
                            manager.retrieveBooks();
                            break;
                        case 4:
                            System.out.print("Enter Book ID: ");
                            int bookId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Updated Title: ");
                            String updatedTitle = scanner.nextLine();
                            System.out.print("Enter Updated Stock: ");
                            int updatedStock = scanner.nextInt();
                            manager.updateBook(bookId, updatedTitle, updatedStock);
                            break;
                        case 5:
                            System.out.print("Enter Book ID to delete: ");
                            int bookIdToDelete = scanner.nextInt();
                            manager.deleteBook(bookIdToDelete);
                            break;
                        case 6:
                            System.out.print("Enter Customer ID: ");
                            int customerId = scanner.nextInt();
                            System.out.print("Enter Book ID: ");
                            int bookIdForOrder = scanner.nextInt();
                            System.out.print("Enter Quantity: ");
                            int quantity = scanner.nextInt();
                            transaction.makeOrder(customerId, bookIdForOrder, quantity);
                            break;
                        case 7:
                            System.out.print("Enter Author ID: ");
                            int authorID = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Author Name: ");
                            String authorName = scanner.nextLine();
                            insertion.insertAuthor(authorID, authorName);
                            break;
                        case 8:
                            System.out.print("Enter Customer ID: ");
                            int customerID = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter Customer Name: ");
                            String customerName = scanner.nextLine();
                            System.out.print("Enter Customer Email: ");
                            String customerEmail = scanner.nextLine();
                            insertion.insertCustomer(customerID, customerName, customerEmail);
                            break;
                        case 9:
                            System.out.println("Exiting the bookstore. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 9.");
                            break;
                    }
                }
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
