import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AppendArrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\|");
        List<String> result = new ArrayList<>();

        for (int i = input.length - 1; i >= 0; i--) {
            String[] arr = input[i].split("\\s+");
            for (String element : arr) {
                result.add(element);
            }
            result.remove("");
        }

        System.out.println(String.join(" ", result));
    }
}
