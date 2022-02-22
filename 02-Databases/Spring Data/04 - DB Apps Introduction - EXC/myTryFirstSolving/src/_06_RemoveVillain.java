import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _06_RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        Scanner sc = new Scanner(System.in);
        int villainId = Integer.parseInt(sc.nextLine());

        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT name FROM villains WHERE id=?");
        selectVillain.setInt(1, villainId);
        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainSet.getString("name");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id=?");
            deleteMinionsVillains.setInt(1, villainId);
            int countReleasedMinions = deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(
                    "DELETE FROM villains WHERE id=?");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            connection.commit();
            System.out.println(villainName + " was deleted");
            System.out.println(countReleasedMinions + " minions released");
        } catch (SQLException e){
            connection.rollback();
        }

    }
}
