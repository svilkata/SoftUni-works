import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsОfHonor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Consumer<String> printer = e -> System.out.println(e);
        Arrays.stream(sc.nextLine().split("\\s+"))
                .map(e -> "Sir " + e)
                .forEach(е-> printer.accept(е));
    }
}
