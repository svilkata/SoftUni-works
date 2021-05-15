package vehiclesExtensions;

import vehiclesExtensions.garage.Bus;
import vehiclesExtensions.garage.Car;
import vehiclesExtensions.garage.Truck;
import vehiclesExtensions.garage.Vehicle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String[] objectNames = {"Car", "Truck", "Bus"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        for (String objectName : objectNames) {
            vehicles.put(objectName, readVehicleInput(sc.nextLine()));
        }

        int n = Integer.parseInt(sc.nextLine());

        while (n-- > 0) {
            String[] tokens = sc.nextLine().split("\\s+");
            String command = tokens[0];
            String typeVehicle = tokens[1];
            double consumpt = Double.parseDouble(tokens[2]);

            try {
                if (command.equals("Drive") && typeVehicle.equals("Bus")) {
                    vehicles.get(typeVehicle).driveWithPassengers(consumpt);
                } else if (command.equals("Refuel")) { //refuel
                    vehicles.get(typeVehicle).refuel(consumpt);
                } else { //drive empty bus or drive car or drive truck
                    vehicles.get(typeVehicle).drive(consumpt);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        vehicles.values().

                forEach(e -> System.out.println(e));
    }

    private static Vehicle readVehicleInput(String nextLine) {
        String[] tokens = nextLine.split("\\s+");
        double quantity = Double.parseDouble(tokens[1]);
        double consumption = Double.parseDouble(tokens[2]);
        double capacity = Double.parseDouble(tokens[3]);

        return tokens[0].equals("Car") ? new Car(quantity, consumption, capacity)
                : tokens[0].equals("Bus") ?
                new Bus(quantity, consumption, capacity)
                :
                new Truck(quantity, consumption, capacity);

    }
}
