import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        Scanner sc = new Scanner(System.in);
        int[] minionsIDUpdate = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        Object[] minionObjects = Arrays.stream(minionsIDUpdate).mapToObj(x -> Integer.valueOf(x)).toArray();
//        Array sqlMinionsIDsUpdateArr = connection.createArrayOf("int", minionObjects);

        for (int id : minionsIDUpdate) {
            PreparedStatement updateOneMinion = connection.prepareStatement(
                    "UPDATE minions\n" +
                            "SET age=age+1, name=LOWER(SUBSTRING(name, 1))\n" +
                            "WHERE id=?;");
            updateOneMinion.setInt(1, id);
            updateOneMinion.executeUpdate();
        }

        PreparedStatement selectAll = connection.prepareStatement("SELECT `id`, `name`, `age` FROM `minions`;");
        ResultSet resultSet = selectAll.executeQuery();
        while (resultSet.next()){
            System.out.println(String.format("%s %d",
                    resultSet.getString("name"),
                    resultSet.getInt("age")));
        }


    }
}
