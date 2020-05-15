package RawData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split(" ");

            Engine currEngine = new Engine(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
            Cargo currCargo = new Cargo(Integer.parseInt(tokens[3]), tokens[4]);
            Tires currTires = new Tires(Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]),
                    Double.parseDouble(tokens[7]), Integer.parseInt(tokens[8]),
                    Double.parseDouble(tokens[9]), Integer.parseInt(tokens[10]),
                    Double.parseDouble(tokens[11]), Integer.parseInt(tokens[12]));

            Car currCar = new Car(tokens[0], currEngine, currCargo, currTires);
            carList.add(currCar);
//            “<Model>
//            <EngineSpeed> <EnginePower>
//            <CargoWeight> <CargoType>
//            <Tire1Pressure> <Tire1Age> <Tire2Pressure> <Tire2Age> <Tire3Pressure> <Tire3Age> <Tire4Pressure> <Tire4Age>
        }
      //  System.out.println(carList);
        String command = sc.nextLine();
        if ("fragile".equals(command)) {
            for (Car car : carList) {
                if (car.getCargo().getCargoType().equals("fragile") &&
                        (car.getTires().getTire1Pressure() < 1.0 || car.getTires().getTire2Pressure() < 1.0
                        || car.getTires().getTire3Pressure() < 1.0 || car.getTires().getTire4Pressure() < 1.0)) {
                    System.out.println(car.getModel());
                }
            }
        } else if ("flamable".equals(command)) {
            for (Car car : carList) {
                if (car.getCargo().getCargoType().equals("flamable") && car.getEngine().getEnginePower() > 250) {
                    System.out.println(car.getModel());
                }
            }
        }

    }
}
