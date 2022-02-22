import java.sql.*;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://root:@localhost:3306/minions_db?useSSL=false");

        Scanner sc = new Scanner(System.in);
        int idMinionToUpdate = Integer.parseInt(sc.nextLine());

        //Създаване на процедура
        String createProcedure =
                "CREATE PROCEDURE usp_get_older(minion_id INT) \n" +
                        "BEGIN\n" +
                        "\tUPDATE minions\n" +
                        "\tSET age=age+1  \n" +
                        "\tWHERE id=minion_id;\n" +
                        "END";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(createProcedure);

        //prepare and execute CallableStatement
        CallableStatement callableStatement = connection.prepareCall("{CALL usp_get_older(?)}");
        callableStatement.setInt(1, idMinionToUpdate);
        ResultSet rs = callableStatement.executeQuery();

        //runs the result
        PreparedStatement selectOutputUpdatedMinion = connection.prepareStatement(
                "SELECT name, age FROM minions WHERE id=?");
        selectOutputUpdatedMinion.setInt(1, idMinionToUpdate);
        ResultSet resultSet = selectOutputUpdatedMinion.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getString("name") + " " + resultSet.getString("age"));

        //dropping the procedure
        String queryDrop = "DROP PROCEDURE IF EXISTS usp_get_older";
        Statement stmtDrop = connection.createStatement();
        System.out.println("Calling DROP PROCEDURE");
        stmtDrop.execute(queryDrop);

    }
}
