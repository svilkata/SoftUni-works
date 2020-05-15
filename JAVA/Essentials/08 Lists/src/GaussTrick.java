import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTrick {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" ")).
                map(Integer::parseInt).collect(Collectors.toList());

        int i = 0;
        int initialSize = numbers.size();
        while (i < initialSize / 2) {
            numbers.set(i, numbers.get(i) + numbers.get(numbers.size() - 1));
            numbers.remove(numbers.size() - 1);
            i++;
        }


        String output = joinElementsByDelimeter(numbers, " ");
        System.out.println(output);
    }

    private static String joinElementsByDelimeter(List<Integer> items, String delimeter) {
        String output = "";
        for (Integer element : items) {
            output += (element + delimeter);
        }
        return output;
    }
}
