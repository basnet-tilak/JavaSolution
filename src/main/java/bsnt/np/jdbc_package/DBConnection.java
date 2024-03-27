package bsnt.np.jdbc_package;
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
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "password";
        ResultSet resultSet;
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql ="SELECT * FROM client";
            if(connection != null){
                resultSet = connection.createStatement().executeQuery(sql);
                while (resultSet.next()){
                    System.out.println( resultSet.getString("id") + " " + resultSet.getString("Name") + " " + resultSet.getString("address"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DriverManager.getConnection(url, username, password).close();
        }
    }
}
