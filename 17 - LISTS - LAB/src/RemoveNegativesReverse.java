import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveNegativesReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());


        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < 0) {
                numbers.remove(i);
                i--;
            }
        }

        //Collections.sort(numbers);
        Collections.reverse(numbers);

        if (numbers.isEmpty()) {
            System.out.println("empty");
        } else {
            printResult(numbers);
        }


    }

    private static void printResult(List<Integer> result) {
        for (int el : result) {
            System.out.print(el + " ");
        }


    }
}
