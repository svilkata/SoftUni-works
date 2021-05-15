//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = Integer.parseInt(sc.nextLine());
//        List<Car> allCars = new ArrayList<>();
//
//        while (n-- > 0) {
//            String[] tokens = sc.nextLine().split("\\s+");
//            String brand = tokens[0];
//            String model = tokens[1];
//            int horsePower = Integer.parseInt(tokens[2]);
//
//            Car car = new Car(brand, model, horsePower);
//
//            allCars.add(car);
//        }
//
//        for (Car car : allCars) {
//            System.out.println(car.carInfo());
//        }
//    }
//}
