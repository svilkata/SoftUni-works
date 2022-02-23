package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private static Connection connection;
    private static final String jdbcString = "jdbc:mysql://localhost:3306/";

    //Singleton
    //всеки път ще работим с комплета, който сме създали
    //private MyConnector() {} // конструкторът по подразборане да не може да се инициализира

    public static void createConnection(String user, String password, String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(jdbcString + dbName, properties);
    }

    public static Connection getConnection() {
        //Singleton
        /*if (connection == null){
            new MyConnector.createConnection();//ала бала
        }*/

        return connection;
    }
}
