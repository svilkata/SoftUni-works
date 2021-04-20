package telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        List<String> URLs = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Smartphone smartphone = new Smartphone(numbers, URLs);
        System.out.println(smartphone.call());
        System.out.println(smartphone.browse());

    }
}
