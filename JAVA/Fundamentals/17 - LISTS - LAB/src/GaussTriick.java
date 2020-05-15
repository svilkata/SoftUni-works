import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTriick {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        int size = numbers.size() / 2;
        for (int i = 0; i < size; i++) {
            int lastIndex = numbers.size()-1;
            int currentSum = numbers.get(i) + numbers.get(lastIndex);

            numbers.remove(lastIndex);
            numbers.set(i, currentSum);
        }

        System.out.println(numbers);
        for (Integer el : numbers) {
            System.out.print(el + " ");
        }

    }
}
