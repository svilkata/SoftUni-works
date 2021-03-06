package CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<Engine> engines = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String engineModel = tokens[0];

            Engine engine;

            if (tokens.length == 4) {
                engine = new Engine(engineModel, Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
            } else if (tokens.length == 3) {
                try { // когато имаме само displacement
                    int displacementEngine = Integer.parseInt(tokens[2]); //ако не е int = displacement, то ще е efficeinecy
                    engine = new Engine(engineModel, Integer.parseInt(tokens[1]));
                    engine = engine.EngineDisplacement(engineModel, Integer.parseInt(tokens[1]), tokens[2]);
                } catch (NumberFormatException e) { //когато имаме само efficiency
                    engine = new Engine(engineModel, Integer.parseInt(tokens[1]));
                    engine = engine.EngineEfficiency(engineModel, Integer.parseInt(tokens[1]), tokens[2]);
                }
            } else { // tokens.length == 2
                engine = new Engine(engineModel, Integer.parseInt(tokens[1]));
            }

            engines.add(engine);
        }

        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            String[] tok = reader.readLine().split("\\s+");
            String carModel = tok[0];
            String engineModel = tok[1];

            Car car = new Car();
            for (Engine engine : engines) {
                if (engine.getModelEngine().equals(engineModel)) {
                    if (tok.length == 4) {
                        car = new Car(carModel, engine, tok[2], tok[3]);
                    } else if (tok.length == 3) {
                        try { // когато имаме само weight
                            int weightCar = Integer.parseInt(tok[2]); //ако не е int = weight, то ще е Colour
                            car = car.CarWithWeight(carModel, engine, tok[2]);
                        } catch (NumberFormatException e) { //когато имаме само Colour
                            car = car.CarWithColour(carModel, engine, tok[2]);
                        }
                    } else { // tok == 2
                        car = new Car(carModel, engine);
                    }

                    break;
                }
            }

            cars.add(car);
        }

        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.getModelCar() + ":").append(System.lineSeparator());
            sb.append(car.getEngineCar().toString()).append(System.lineSeparator());
            sb.append("Weight: " + car.getWeightCar()).append(System.lineSeparator());
            sb.append("Color: " + car.getColourCar()).append(System.lineSeparator());
        }
        System.out.println(sb);

    }
}
