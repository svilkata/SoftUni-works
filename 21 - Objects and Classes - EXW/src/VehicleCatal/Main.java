package VehicleCatal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Vehicle> vehiclesList = new ArrayList<>();
        String input = sc.nextLine();
        while (!"End".equals(input)) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            String model = tokens[1];
            String color = tokens[2];
            int horsepower = Integer.parseInt(tokens[3]);
            Vehicle vehicle;
            if ("car".equals(type)) {
                vehicle = new Vehicle("Car", model, color, horsepower);
            } else {
                vehicle = new Vehicle("Truck", model, color, horsepower);
            }
            vehiclesList.add(vehicle);

            input = sc.nextLine();
        }


        String model = sc.nextLine();
        while (!"Close the Catalogue".equals(model)) {
            for (Vehicle vehicle : vehiclesList) {
                if (vehicle.getModelOfVehicle().equals(model)) {
                    System.out.println(vehicle);
                    break;
                }
            }
            model = sc.nextLine();
        }

        double carHP = printHorsepower(vehiclesList, "Car");
        System.out.printf("Cars have average horsepower of: %.2f.%n", carHP);
        double truckHP = printHorsepower(vehiclesList, "Truck");
        System.out.printf("Trucks have average horsepower of: %.2f.", truckHP);

    }

    public static double printHorsepower(List<Vehicle> vehiclesList, String type) {
        double sum = 0.0;
        int counter = 0;

        for (Vehicle vehicle : vehiclesList) {
            if (vehicle.getTypeOfVehicle().equals(type)) {
                sum+= vehicle.getHorsepowerOfVehicle();
                counter++;
            }
        }
        if (counter == 0) {
            return 0;
        }
        return sum / counter;
    }
}
