import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNames {
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
        PreparedStatement villianStatement = connection.prepareStatement(
                "SELECT `name` FROM villains WHERE id = ?;");

        System.out.print("Enter villian id: ");
        int villianID = Integer.parseInt(sc.nextLine().trim());

        villianStatement.setInt(1, villianID);

        ResultSet villianSet = villianStatement.executeQuery();
        if (!villianSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.\n", villianID);
            return;
        }

        String villianName = villianSet.getString("name");
        System.out.println("Villain: " + villianName);


        PreparedStatement minionStatement = connection.prepareStatement(
                "SELECT DISTINCT m.`name`, m.age\n" +
                        "FROM villains AS v\n" +
                        "JOIN minions_villains AS mv\n" +
                        "ON v.id = mv.villain_id\n" +
                        "JOIN minions AS m\n" +
                        "ON m.id = mv.minion_id\n" +
                        "WHERE v.id = ?;");
        minionStatement.setInt(1, villianID);

        ResultSet minionSet = minionStatement.executeQuery();

        for (int i = 1; minionSet.next(); i++) {
            String name = minionSet.getString("name");
            int age = minionSet.getInt("age");

            System.out.println(String.format("%d. %s %d", i, name, age));
        }

        /*if (villianID < 1 || villianID > 6) {
            System.out.printf("No villain with ID %d exists in the database.\n", villianID);
        } else {
            int counter = 1;
            while (resultSet.next()) {
                if (counter == 1) {
                    System.out.println("Villain: " + resultSet.getString("VilName"));
                }
                System.out.printf("%d. %s %d\n",
                        counter++,
                        resultSet.getString("name"),
                        resultSet.getInt("age"));
            }
        }*/

        connection.close();
    }
}
