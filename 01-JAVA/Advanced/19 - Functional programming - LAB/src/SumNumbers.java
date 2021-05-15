import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Function<String, Integer> parseInt = x -> Integer.parseInt(x);
        ToIntFunction<String> parsInt = x -> Integer.parseInt(x);

        int[] numbers = Arrays.stream(sc.nextLine().split(", "))
                .mapToInt(parsInt)
                .toArray();


        Function<int[], Integer> printCount = arr -> arr.length;

        Function<int[], String> formatArrSum = arr ->   "Sum = " + Arrays.stream(arr).sum();

        System.out.println("Count = " + printCount.apply(numbers));
        System.out.println(formatArrSum.apply(numbers));
    }
}
