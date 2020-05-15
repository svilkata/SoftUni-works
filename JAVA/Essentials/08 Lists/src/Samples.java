import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> inputNumbers = new ArrayList<Integer>(Arrays.asList(1, 5, -10, 15, 13));
        inputNumbers.add(1);
        inputNumbers.set(2, 42);
        inputNumbers.remove(1);
        inputNumbers.add(2, 42);

        int number = sc.nextInt();

        while (number != -1) {
            inputNumbers.add(number);
            number = sc.nextInt();
        }

        for (Integer element : inputNumbers) {
            System.out.print(element + " ");
        }

        for (int i = inputNumbers.size() - 1; i >= 0; i--) {
            System.out.print(inputNumbers.get(i) + " ");
        }


    }
}
