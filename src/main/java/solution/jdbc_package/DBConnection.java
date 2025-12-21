package solution.jdbc_package;
import java.sql.*;

public class DBConnection{
    /**
     * Steps of the JDBC connection are
     *   Step 1 -> Connection
     *   Step 2 -> Statement
     *   Step 3 -> ResultSet rs = connection.createStatement().executeQuery();
     *   Step 4 -> Close()
     */
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/kuberdb";
        String username = "root";
        String password = "Kalit@1579";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            // CREATE - Insert operation with duplicate check
            String insertSql = "INSERT IGNORE INTO users (username, email, phone, password_hash) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setString(1, "johndoe" + System.currentTimeMillis());
            insertStmt.setString(2, "john" + System.currentTimeMillis() + "@example.com");
            insertStmt.setString(3, "+1234567890");
            insertStmt.setString(4, "hashedpassword123");
            System.out.println("Rows inserted: " + insertStmt.executeUpdate());

            // READ - Select operation
            String selectSql = "SELECT * FROM users";
            ResultSet resultSet = connection.createStatement().executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getLong("id") + ", Username: " + resultSet.getString("username"));
            }

            // UPDATE - Update operation
            String updateSql = "UPDATE users SET email = ? WHERE username = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setString(1, "newemail@example.com");
            updateStmt.setString(2, "johndoe");
            System.out.println("Rows updated: " + updateStmt.executeUpdate());

            // DELETE - Delete operation
            String deleteSql = "DELETE FROM users WHERE username = ?";
           // PreparedStatement deleteStmt = connection.prepareStatement(deleteSql);
          //  deleteStmt.setString(1, "johndoe");
          //  System.out.println("Rows deleted: " + deleteStmt.executeUpdate());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
