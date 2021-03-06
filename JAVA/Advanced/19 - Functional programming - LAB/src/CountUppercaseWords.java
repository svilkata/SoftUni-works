import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CountUppercaseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        Predicate<String> startWithCapital = str -> Character.isUpperCase(str.charAt(0));
        StringBuilder out = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(0);
        Consumer<String> addToOutput = str -> {
            counter.incrementAndGet();
            out.append(str).append(System.lineSeparator());
        };

        Arrays.stream(text.split("\\s+"))
                .filter(startWithCapital)
                .forEach(addToOutput);

        System.out.println(counter.get());
        System.out.println(out.toString());
    }
}
