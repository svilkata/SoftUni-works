import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        Scanner sc = new Scanner(System.in);
        String countryName = sc.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement("UPDATE towns\n" +
                "SET name = UPPER(name)\n" +
                "WHERE country = ?;");
        updateTownNames.setString(1, countryName);

        int updatedCount = updateTownNames.executeUpdate(); //връща броя на update-ваните записи или 0 ако не се Update-ва нищо
        System.out.println(updatedCount);

        if (updatedCount == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.println(updatedCount + " town names were affected.");
        PreparedStatement selectAllTownsInCountry = connection.prepareStatement(
                "SELECT name FROM towns WHERE country=?");
        selectAllTownsInCountry.setString(1, countryName);
        ResultSet townsSet = selectAllTownsInCountry.executeQuery();

        List<String> towns = new ArrayList<>();
        while (townsSet.next()) {
            String townName = townsSet.getString("name");
            towns.add(townName);
        }
        System.out.println(towns);

        connection.close();
    }
}
