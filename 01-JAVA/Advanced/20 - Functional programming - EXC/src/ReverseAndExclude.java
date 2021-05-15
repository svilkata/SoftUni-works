import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.reverse(numbers);

        int n = Integer.parseInt(sc.nextLine());
        System.out.println(numbers.stream()
                .filter(num -> num % n != 0)
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(" ")));


    }
}
