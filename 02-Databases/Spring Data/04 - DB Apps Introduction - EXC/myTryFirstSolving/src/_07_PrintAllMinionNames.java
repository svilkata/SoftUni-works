import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class _07_PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        PreparedStatement selectAllMinions = connection.prepareStatement(
                "SELECT name FROM minions");
        ResultSet allMinionsSet = selectAllMinions.executeQuery();

        List<String> originalMinions = new ArrayList<>();
        while (allMinionsSet.next()) {
            originalMinions.add(allMinionsSet.getString("name"));
        }

//        List<String> originalMinions = Arrays.asList("A", "B", "C", "D", "E");
        List<String> outputMinions = new ArrayList<>();


        int leng = originalMinions.size();
        for (int i = 0; i < leng / 2; i++) {
            outputMinions.add(originalMinions.get(i));
            outputMinions.add(originalMinions.get(leng - i -1));
        }

        if (leng % 2 != 0) {
            outputMinions.add(originalMinions.get(leng / 2));
        }

        outputMinions.forEach(x -> System.out.println(x));
    }
}
