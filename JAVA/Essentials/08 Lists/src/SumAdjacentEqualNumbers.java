import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SumAdjacentEqualNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split(" ")).
                map(Double::parseDouble).collect(Collectors.toList());


        List<Integer> nonNegative = new ArrayList<>();

        int i = 0;
        while (i < numbers.size() - 1) {
            double currentNumber = numbers.get(i);
            double nextNumber = numbers.get(i + 1);

            if (currentNumber == nextNumber) {
                double sum = currentNumber + nextNumber;
                numbers.set(i, sum);
                numbers.remove(i + 1);
                i = 0;
            } else {
                i++;
            }
        }

        String output = joinElementsByDelimeter(numbers, " ");
        System.out.println(output);

//        for (Double element : numbers) {
////            System.out.print(element + " ");
////        }


    }

    private static String joinElementsByDelimeter(List<Double> items, String delimeter) {
        String output = "";
        for (Double item : items) {
            output+= (new DecimalFormat("0.#").format(item)+delimeter);
        }
        return output;

    }
}
