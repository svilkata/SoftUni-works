import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        //2. Connect to DB
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db?useSSL=false", props);

//        Minion: Robert 14 Berlin
//        Villain: Gru
        Scanner sc = new Scanner(System.in);
        String[] minionInfo = sc.nextLine().split(" "); //start after index 0
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villianName = sc.nextLine().split(" ")[1];

        int townId = getOrInsertTown(connection, minionTown);
        int villianId = getOrInsertVillian(connection, villianName);

        PreparedStatement insertMinionStmnt = connection.prepareStatement(
                "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)");
        insertMinionStmnt.setString(1, minionName);
        insertMinionStmnt.setInt(2, minionAge);
        insertMinionStmnt.setInt(3, townId);
        int updateRowCount = insertMinionStmnt.executeUpdate(); //returns 1 if data updated

        PreparedStatement getLastMinion = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id DESC LIMIT 1");
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next(); //отиди на посленидя миньон
        int lastMinionId = lastMinionSet.getInt("id");

        PreparedStatement insertMinionsVillains = connection.prepareStatement(
                "INSERT INTO minions_villains VALUES(?, ?) ");
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villianId);
        insertMinionsVillains.executeUpdate();

        System.out.println(String.format("Successfully added %s to be minion of %s.", minionName, villianName));

        connection.close();
    }

    private static int getOrInsertVillian(Connection connection, String villianName) throws SQLException {
        PreparedStatement selectVillianStatement = connection.prepareStatement(
                "SELECT id FROM villains WHERE name=?");
        selectVillianStatement.setString(1, villianName);

        ResultSet villianSet = selectVillianStatement.executeQuery();

        int villianId = 0;
        if (!villianSet.next()) {
            PreparedStatement insertVillianStatement = connection.prepareStatement(
                    "INSERT INTO villains(name, evilness_factor) VALUES(?, ?)");
            insertVillianStatement.setString(1, villianName);
            insertVillianStatement.setString(2, "evil");
            insertVillianStatement.executeUpdate(); //в случая с тази заявка добавяме лошко

            ResultSet newVillianSet = selectVillianStatement.executeQuery();
            newVillianSet.next(); //премести показателя на последния добавен villain / лошковец
            villianId = newVillianSet.getInt("id");
            System.out.println(String.format("Villain %s was added to the database.", villianName));
        } else {
            villianId = villianSet.getInt("id");
        }

        connection.close();

        return villianId;
    }

    private static int getOrInsertTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement selectTownStatement = connection.prepareStatement(
                "SELECT id FROM towns WHERE name=?");
        selectTownStatement.setString(1, minionTown);

        ResultSet townSet = selectTownStatement.executeQuery();

        int townId = 0;
        //Ако няма такъв град
        if (!townSet.next()) {
            PreparedStatement insertTownStatement = connection.prepareStatement("" +
                    "INSERT INTO towns(name) VALUES(?)");
            insertTownStatement.setString(1, minionTown);
            insertTownStatement.executeUpdate(); //в случая с тази заявка Добавяме град

            ResultSet newTownSet = selectTownStatement.executeQuery();
            newTownSet.next(); //премести показателя на последния добавен град
            townId = newTownSet.getInt("id");
            System.out.println(String.format("Town %s was added to the database.", minionTown));
        } else {
            townId = townSet.getInt("id");
        }

        connection.close();

        return townId;
    }
}
