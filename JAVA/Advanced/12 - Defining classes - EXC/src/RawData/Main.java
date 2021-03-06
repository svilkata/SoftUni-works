package RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
//            "<Model> <EngineSpeed> <EnginePower> <CargoWeight> <CargoType> <Tire1Pressure> " +
//            "<Tire1Age> <Tire2Pressure> <Tire2Age> <Tire3Pressure> <Tire3Age> <Tire4Pressure> <Tire4Age>
            String[] tokens = reader.readLine().split("\\s+");
            String model = tokens[0];
            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            double tyre1pressure = Double.parseDouble(tokens[5]);
            int tyre1Age = Integer.parseInt(tokens[6]);
            double tyre2pressure = Double.parseDouble(tokens[7]);
            int tyre2Age = Integer.parseInt(tokens[8]);
            double tyre3pressure = Double.parseDouble(tokens[9]);
            int tyre3Age = Integer.parseInt(tokens[10]);
            double tyre4pressure = Double.parseDouble(tokens[11]);
            int tyre4Age = Integer.parseInt(tokens[12]);


            Car car = new Car(model);
            Engine engine = new Engine(model, engineSpeed, enginePower);
            car.setEngine(engine);
            Cargo cargo = new Cargo(model, cargoWeight, cargoType);
            car.setCargo(cargo);
            FourTyres fourTyres = new FourTyres(model, tyre1pressure, tyre1Age,
                    tyre2pressure, tyre2Age, tyre3pressure, tyre3Age,
                    tyre4pressure, tyre4Age);
            car.setTyres(fourTyres);

            cars.add(car);
        }

        String line = reader.readLine();
        switch (line) {
            case "fragile":
                for (Car car : cars) {
                    if (car.getCargo().getTypeCargo().equals(line) && car.getTyres().isATyreLessPressureThan1()) {
                        System.out.println(car.getModel());
                    }
                }
                break;
            case "flamable":
                for (Car car : cars) {
                    if (car.getCargo().getTypeCargo().equals(line) && car.getEngine().isMoreThan250HP()) {
                        System.out.println(car.getModel());
                    }
                }
                break;
        }


    }


}
