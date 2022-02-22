package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main2 {
    public static void main(String[] args) throws SQLException, IOException {
        Properties props = new Properties();
//        String appConfigPath = Main2.class.getClassLoader()
////                .getResource("db.properties").getPath();
        String appConfigPath = "C:\\Users\\svilk\\OneDrive\\Soft Engineer\\JAVA & JS path\\2_DATABASES\\Spring Data\\real - February 2022\\03 - DB Apps Introduction - LAB\\jdbc intro\\src\\db.properties";
        System.out.println(appConfigPath);

        props.load(new FileInputStream(appConfigPath));
//        props.loadFromXML(new FileInputStream(appConfigPath));

        //1. Load jdbc driver - optional
        try {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Driver loaded successfully");

        //Using try with resources
        //2. Connect to DB and 3. PreparedStatement  in try with resources
        //Interface-а Connection e AutoCloseable
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni?useSSL=false", props);
             PreparedStatement stmt =
                     connection.prepareStatement("SELECT * FROM employees WHERE salary > ?")) {
            System.out.println("Connected successfully");

            System.out.print("Enter minimal salary (default 20 000): ");
            String salaryStr = props.getProperty("salary", "20000");
            double salary = Double.parseDouble(salaryStr);
            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();//Runs the SQL statement and returns retrieved result
            while (rs.next()) {
                System.out.printf("| %-15.15s | %-15.15s | %10.2f\n",
                        rs.getString("first_name"), //column label
                        rs.getString("last_name"), //column label
                        rs.getDouble("salary")); //get by column label
            }
        }
    }
}
