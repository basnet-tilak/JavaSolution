package solution.interview.jdbc;

import java.sql.*;
import java.math.BigDecimal;

/**
 * Clean JDBC Interview Examples
 */
public class JDBCExamplesClean {
    
    private static final String URL = "jdbc:mysql://localhost:3306/kuberdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Kalit@1579";
    
    /**
     * Q: Basic JDBC CRUD operations
     */
    public static void basicCRUDOperations() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            
            // Create table
            String createTable = "CREATE TABLE IF NOT EXISTS employees (" +
                               "id INT PRIMARY KEY AUTO_INCREMENT, " +
                               "name VARCHAR(100), " +
                               "salary DECIMAL(10,2))";
            conn.createStatement().execute(createTable);
            
            // INSERT
            String insertSQL = "INSERT INTO employees (name, salary) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "John Doe");
                pstmt.setBigDecimal(2, new BigDecimal("50000.00"));
                pstmt.executeUpdate();
                System.out.println("Employee inserted");
            }
            
            // SELECT
            String selectSQL = "SELECT * FROM employees";
            try (ResultSet rs = conn.createStatement().executeQuery(selectSQL)) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + 
                                     ", Name: " + rs.getString("name") + 
                                     ", Salary: " + rs.getBigDecimal("salary"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }
    
    /**
     * Q: Transaction management with commit/rollback
     */
    public static void transactionExample() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.setAutoCommit(false);
            
            String createTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                               "id INT PRIMARY KEY, " +
                               "balance DECIMAL(10,2))";
            conn.createStatement().execute(createTable);
            
            // Insert test data
            String insertSQL = "INSERT INTO accounts VALUES (?, ?) ON DUPLICATE KEY UPDATE balance = VALUES(balance)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setInt(1, 1);
                pstmt.setBigDecimal(2, new BigDecimal("1000.00"));
                pstmt.executeUpdate();
                
                pstmt.setInt(1, 2);
                pstmt.setBigDecimal(2, new BigDecimal("500.00"));
                pstmt.executeUpdate();
            }
            
            // Transfer money
            BigDecimal amount = new BigDecimal("200.00");
            
            String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(debitSQL)) {
                pstmt.setBigDecimal(1, amount);
                pstmt.setInt(2, 1);
                pstmt.executeUpdate();
            }
            
            String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(creditSQL)) {
                pstmt.setBigDecimal(1, amount);
                pstmt.setInt(2, 2);
                pstmt.executeUpdate();
            }
            
            conn.commit();
            System.out.println("Transaction committed successfully");
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== JDBC Examples ===");
        
        System.out.println("\n1. Basic CRUD Operations:");
        basicCRUDOperations();
        
        System.out.println("\n2. Transaction Management:");
        transactionExample();
    }
}