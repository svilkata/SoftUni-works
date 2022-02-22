import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class _08_IncreaseMinionsAge_2 {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        Scanner sc = new Scanner(System.in);
        String minionsIDsUpdate = Arrays.toString(Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray());
        minionsIDsUpdate = minionsIDsUpdate.replace("[", "(");
        minionsIDsUpdate = minionsIDsUpdate.replace("]", ")");
        System.out.println(minionsIDsUpdate);
        String query = String.format("UPDATE minions\n SET age=age+1, name=LOWER(SUBSTRING(name, 1))\n WHERE id IN %s;",
                minionsIDsUpdate);

        PreparedStatement updateSepcifiedMinions = connection.prepareStatement(
                query);
        updateSepcifiedMinions.executeUpdate();

        PreparedStatement selectAll = connection.prepareStatement("SELECT `id`, `name`, `age` FROM `minions`;");
        ResultSet resultSet = selectAll.executeQuery();
        while (resultSet.next()) {
            System.out.println(String.format("%s %d",
                    resultSet.getString("name"),
                    resultSet.getInt("age")));
        }


    }
}
