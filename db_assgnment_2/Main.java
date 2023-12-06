public class Main {
    public static void main(String[] args) {
        
        BookstoreTransaction manager = new BookstoreTransaction();

        // Example customer ID, book ID, and quantity
        int customerId = 1; // Replace with an actual customer ID
        int bookId = 101;   // Replace with an actual book ID
        int quantity = 2;   // Quantity of the book to order

        // Call the method to place an order
        manager.makeOrder(customerId, bookId, quantity);



            // Create an instance of DatabaseMetadataViewer
            DatabaseMetadata metadataViewer = new DatabaseMetadata();

            // Displaying names and structures of all tables
            metadataViewer.displayTablesInfo();
    
            // Displaying primary key information for a specific table
            // Replace 'YourTableName' with the actual table name
            metadataViewer.displayPrimaryKeysInfo("YourTableName");
    
            // Displaying foreign key information for a specific table
            // Replace 'YourTableName' with the actual table name
            metadataViewer.displayForeignKeysInfo("YourTableName");




        BookstoreManager bookstoreManager = new BookstoreManager();

        // Example usage of the methods in BookstoreManager

        // Inserting a new book
        bookstoreManager.insertBook("Book Title", 1, 19.99, 10);

        // Retrieving all book information
        bookstoreManager.retrieveBooks();

        // Updating details of a book
        int bookIdToUpdate = 101; // Replace with the actual book ID to update
        bookstoreManager.updateBook(bookIdToUpdate, "Updated Title", 15);

        // Removing an existing book
        int bookIdToDelete = 102; // Replace with the actual book ID to delete
        bookstoreManager.deleteBook(bookIdToDelete);
    }
}
