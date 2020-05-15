import java.util.Arrays;
import java.util.Scanner;

public class Largest3Numbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(x -> Integer.parseInt(x))
                .sorted((x1, x2) -> x2.compareTo(x1))
                .limit(3)
                .forEach(a -> System.out.print(a + " "));
    }
}
