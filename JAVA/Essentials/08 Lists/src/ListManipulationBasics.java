import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationBasics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        while (true) {
            String line = sc.nextLine();

            if ("end".equals(line)) {
                break;
            }

            String[] token = line.split(" ");

            switch (token[0]) {
                case "Add": {
                    int numberToAdd = Integer.parseInt(token[1]);
                    numbers.add(numberToAdd);
                    break;
                }
                case "Remove": {
                    int numberToRemove = Integer.parseInt(token[1]);
                    numbers.remove(Integer.valueOf(numberToRemove));
                    break;
                }
                case "RemoveAt": {
                    int indexToRemove = Integer.parseInt(token[1]);
                    numbers.remove(indexToRemove);
                    break;
                }
                case "Insert": {
                    int numberToInsert = Integer.parseInt(token[1]);
                    int indexToInsert = Integer.parseInt(token[2]);
                    numbers.add(indexToInsert, numberToInsert);
                    break;
                }
            }
        }

        System.out.println(numbers.toString().replaceAll("[\\[\\],]", ""));


    }
}
