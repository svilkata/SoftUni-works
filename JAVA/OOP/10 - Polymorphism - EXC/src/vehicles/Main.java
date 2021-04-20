package vehicles;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();
        vehicles.put("Car", readVehicleInput(sc.nextLine()));
        vehicles.put("Truck", readVehicleInput(sc.nextLine()));

        int n = Integer.parseInt(sc.nextLine());

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split("\\s+");
            String command = tokens[0];
            String typeVehicle = tokens[1];
            double value = Double.parseDouble(tokens[2]);

            if (command.equals("Drive")) {
                vehicles.get(typeVehicle).drive(value);
            } else { //refuel
                vehicles.get(typeVehicle).refuel(value);
            }
        }

        vehicles.values().forEach(e -> System.out.println(e));
    }

    private static Vehicle readVehicleInput(String nextLine) {
        String[] tokens = nextLine.split("\\s+");
        return tokens[0].equals("Car") ? new Car(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])) :
                new Truck(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));

    }
}
