import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class SortEvenNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(num -> Integer.parseInt(num))
                .filter(num -> num % 2 == 0)
                .toArray();

        Consumer<int[]> consumer = (arr) -> System.out.println(Arrays.stream(numbers)
                .mapToObj(num -> Integer.toString(num))
                .collect(Collectors.joining(", ")));

        consumer.accept(numbers);

        Arrays.sort(numbers);

        consumer.accept(numbers);



    }


}
