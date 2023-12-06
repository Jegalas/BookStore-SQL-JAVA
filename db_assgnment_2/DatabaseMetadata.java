import java.sql.*;

public class DatabaseMetadata {

    private String url = "jdbc:postgresql://localhost:5432/dataassign2";
    private String user = "postgres";
    private String password = "sadiq129";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Display names and structures of all tables
    public void displayTablesInfo() {
        try (Connection conn = this.connect()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                System.out.println("Table: " + tables.getString("TABLE_NAME"));
                displayColumnsInfo(tables.getString("TABLE_NAME"), metaData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display details on columns of a table
    private void displayColumnsInfo(String tableName, DatabaseMetaData metaData) throws SQLException {
        ResultSet columns = metaData.getColumns(null, null, tableName, "%");
        while (columns.next()) {
            System.out.println("\tColumn: " + columns.getString("COLUMN_NAME") +
                               ", Type: " + columns.getString("TYPE_NAME") +
                               ", Size: " + columns.getInt("COLUMN_SIZE"));
        }
    }

    // Display information on primary keys
    public void displayPrimaryKeysInfo(String tableName) {
        try (Connection conn = this.connect()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);

            while (primaryKeys.next()) {
                System.out.println("Table: " + primaryKeys.getString("TABLE_NAME") +
                                   ", Primary Key: " + primaryKeys.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display information on foreign keys
    public void displayForeignKeysInfo(String tableName) {
        try (Connection conn = this.connect()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);

            while (foreignKeys.next()) {
                System.out.println("Table: " + foreignKeys.getString("FKTABLE_NAME") +
                                   ", Foreign Key: " + foreignKeys.getString("FKCOLUMN_NAME") +
                                   ", References: " + foreignKeys.getString("PKTABLE_NAME") +
                                   "." + foreignKeys.getString("PKCOLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
