import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _02_GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        //1. Load jdbc driver - optional
        try {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Driver loaded successfully");


        //2. Connect to DB
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");
        System.out.println("Connected successfully");

        //3. PreparedStatement
        PreparedStatement statement = connection.prepareStatement(
                "SELECT v.name, COUNT(DISTINCT mv.minion_id) AS `count_of_minions`\n" +
                        "FROM villains AS v\n" +
                        "LEFT JOIN minions_villains AS mv\n" +
                        "ON v.id = mv.villain_id\n" +
                        "GROUP BY mv.villain_id\n" +
                        "HAVING `count_of_minions` >15\n" +
                        "ORDER BY `count_of_minions` DESC;");

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.printf("%-15.15s %d\n",
                    rs.getString("name"),
                    rs.getInt("count_of_minions"));
        }

        connection.close();
    }
}
