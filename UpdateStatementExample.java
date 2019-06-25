import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class UpdateStatementExample {

    private static final String UPDATE_USERS_SQL = "update users set name = \"Ram\" where id = 1;";

    public static void main(String[] argv) throws SQLException {
        UpdateStatementExample updateStatementExample = new UpdateStatementExample();
        updateStatementExample.updateRecord();
    }

    public void updateRecord() throws SQLException {
        System.out.println(UPDATE_USERS_SQL);
       
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/update?useSSL=false", "root", "123Alpha");

            
            Statement statement = connection.createStatement();) {

            
            int result = statement.executeUpdate(UPDATE_USERS_SQL);
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {

            
            printSQLException(e);
        }

        
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}