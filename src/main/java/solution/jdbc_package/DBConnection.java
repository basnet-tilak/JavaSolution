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
        String url = "jdbc:mysql://localhost:3306/userdb";
        String username = "root";
        String password = "root";
        ResultSet resultSet;
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql ="SELECT * FROM users";
            if(connection != null){
                resultSet = connection.createStatement().executeQuery(sql);
                while (resultSet.next()){
                    System.out.println( resultSet.getString("user_id") + ", " + resultSet.getString("full_name") + ", " + resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DriverManager.getConnection(url, username, password).close();
        }
    }
}
