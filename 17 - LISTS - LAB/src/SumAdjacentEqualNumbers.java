import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumAdjacentEqualNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Double::parseDouble).collect(Collectors.toList());

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i).equals(numbers.get(i + 1))) {
                double sum = numbers.get(i) * 2;
                numbers.remove(i); // {3, 6, 1}
                numbers.set(i, sum);
                i = -1;
            }
        }

        for (Double el : numbers) {
            System.out.print(new DecimalFormat("0.#").format(el) + " ");
        }


    }
}
