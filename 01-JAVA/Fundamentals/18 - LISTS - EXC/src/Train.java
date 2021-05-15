import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Train {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> trainWagonsPassengers = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int maxCapacityWagon = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();
        while (!"end".equals(input)) {
            String[] token = input.split(" ");

            if (token[0].equals("Add")) { //add another wagon
                trainWagonsPassengers.add(Integer.parseInt(token[1]));
            } else {
                addPassengersInAllWagons(trainWagonsPassengers, Integer.parseInt(token[0]), maxCapacityWagon);
            }
            input = sc.nextLine();
        }

        printArr(trainWagonsPassengers);


    }

    private static void printArr(List<Integer> trainWagonsPassengers) {
        for (Integer el : trainWagonsPassengers) {
            System.out.print(el + " ");
        }
    }

    private static void addPassengersInAllWagons(List<Integer> trainWagonsPassengers, int passengerstoAdd, int maxCapacityWagon) {
        int spaceAvailableToAddCurrentWagon = 0;

        for (int i = 0; i < trainWagonsPassengers.size(); i++) {
            if (trainWagonsPassengers.get(i) < maxCapacityWagon) {
                spaceAvailableToAddCurrentWagon = maxCapacityWagon - trainWagonsPassengers.get(i);
                if (passengerstoAdd <= spaceAvailableToAddCurrentWagon) {
                    trainWagonsPassengers.set(i, trainWagonsPassengers.get(i) + passengerstoAdd);
                    break;
                } else {
                    trainWagonsPassengers.set(i, trainWagonsPassengers.get(i) + spaceAvailableToAddCurrentWagon);
                    passengerstoAdd -= spaceAvailableToAddCurrentWagon;
                }
            }
        }
    }
}
