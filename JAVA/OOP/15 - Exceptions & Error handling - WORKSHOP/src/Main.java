import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            throw new TankException("Not enough fuel to travel");
        } catch (TankException e) {
            System.out.println(e.getMessage());
        }
    }
}
