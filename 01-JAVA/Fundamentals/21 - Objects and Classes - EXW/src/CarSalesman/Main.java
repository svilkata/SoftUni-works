package CarSalesman;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // създаваме списък с двигатели
        int linesEngines = Integer.parseInt(sc.nextLine());
        List<Engine> engineList = new ArrayList<>();
        for (int i = 0; i < linesEngines; i++) {
//            <Model> <Power> <Displacement> <Efficiency>
            String[] tokens = sc.nextLine().split(" ");

            Engine currEngine = new Engine(tokens[0], tokens[1]);
            if (tokens.length == 3) {
                try {
                    int displacement = Integer.parseInt(tokens[2]);
                    currEngine.setDisplacementEngine(tokens[2]);
                } catch (NumberFormatException e) {
                    currEngine.setEfficiencyEngine(tokens[2]);
                }
            } else if (tokens.length == 4) {
                currEngine.setDisplacementEngine(tokens[2]);
                currEngine.setEfficiencyEngine(tokens[3]);
            }

            engineList.add(currEngine);
        }
        //System.out.println(engineList);

        //Създаваме списък с колите
        int numberOfCars = Integer.parseInt(sc.nextLine());
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
//            <Model> <Engine> <Weight> <Color>
            String[] tokens = sc.nextLine().split(" ");


            Car currCar = new Car(tokens[0]);
            if (tokens.length == 3) {
                try {
                    int weight = Integer.parseInt(tokens[2]);
                    currCar.setWeightCar(tokens[2]);
                } catch (NumberFormatException e) {
                    currCar.setColor(tokens[2]);
                }
            } else if (tokens.length == 4) {
                currCar.setWeightCar(tokens[2]);
                currCar.setColor(tokens[3]);
            }

            for (Engine engine : engineList) {
                if (engine.getModelEngine().equals(tokens[1])) {
                    currCar.setConnectWithEnginClassModelEngine(engine);
                }
            }
            carList.add(currCar);
        }

        for (Car car : carList) {
            System.out.println(car.getModelCar() + ":");
            System.out.println(car.getConnectWithEnginClassModelEngine().toString());
            System.out.println(car.toString());
        }
//             <CarModel>: -от Car

//             <EngineModel>: - от Car или от Engine
//             Power: <EnginePower> - от Egine
//             Displacement: <EngineDisplacement> - от Egine
//             Efficiency: <EngineEfficiency> - от Egine

//             Weight: <CarWeight> - от Car
//             Color: <CarColor> - от Car


    }
}
