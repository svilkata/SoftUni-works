//package Constructors;
//
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
//            Car car;
//
//            if (tokens.length == 3) {
//                car = new Car(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
//            } else if (tokens.length == 2) {
//                car = new Car(tokens[0], tokens[1]);
//            } else {
//                car = new Car(tokens[0]);
//            }
//
//            allCars.add(car);
//        }
//
//        for (Car car : allCars) {
//            System.out.println(car.carInfo());
//        }
//    }
//
//}
