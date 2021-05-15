import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Predicate<String> predicate = s -> s.length() <=n;

        System.out.println(Arrays.stream(sc.nextLine().split("\\s+"))
                .filter(predicate)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
