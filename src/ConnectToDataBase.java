import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDataBase {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/fishdatabase";
    //private static final String DATABASE_URL ="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
    //final String USERNAME = "root";
    //final String PASSWORD = "";

    public static Connection Connector(String USERNAME, String PASSWORD) {
        try {

            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
