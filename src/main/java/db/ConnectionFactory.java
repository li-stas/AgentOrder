package db;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * https://ibytecode.com/blog/pagination-in-servlet-and-jsp/
 */
public class ConnectionFactory {
    //static reference to itself
    private static ConnectionFactory instance =
            new ConnectionFactory();
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "STUDENT";
    String password = "admin";
    String driverClass = "oracle.jdbc.OracleDriver";

    //private constructor
    private ConnectionFactory() {
        try {
            Class.forName(driverClass);
            DriverManager.setLogStream(System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance()	{
        return instance;
    }

    public Connection getConnection() throws SQLException,
            ClassNotFoundException {
        Connection connection =
                DriverManager.getConnection(url, user, password);

        DatabaseMetaData dma = connection.getMetaData();
        // Печать сообщения об успешном соединении
        System.out.println("\njdbc");
        System.out.println("Connected to " + dma.getURL());
        System.out.println("Driver " + dma.getDriverName());
        System.out.println("Version " + dma.getDriverVersion());

        return connection;
    }

}
