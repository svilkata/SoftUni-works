import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> train = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int wagonCapacity = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();

        while (!"end".equals(input)) {
            String[] tokens = input.split(" ");

            if (tokens[0].equals("Add")) {
                int people = Integer.parseInt(tokens[1]);
                train.add(people);
            } else {
                int people = Integer.parseInt(tokens[0]);
                for (int i = 0; i < train.size(); i++) {
                    int currentWaggon = train.get(i);
                    int totalCnt = currentWaggon + people;
                    if (totalCnt <= wagonCapacity) {
                        train.set(i, totalCnt);
                        break;
                    }
                }
            }

            input = sc.nextLine();
        }

        printTrain(train);
    }

    private static void printTrain(List<Integer> train) {
        for (Integer wagon : train) {
            System.out.print(wagon + " ");
        }
    }
}
