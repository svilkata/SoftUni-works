import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ThePartyReservationFilterModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        String input = sc.nextLine();
        Map<String, Predicate<String>> predicate = new HashMap<>();


        while (!"Print".equals(input)) {
            String[] tokens = input.split(";");
            if (tokens[0].contains("Add")) {
                predicate.put(tokens[1] + tokens[2], producePredicates(tokens[1], tokens[2]));
            } else { //remove filter
                predicate.remove(tokens[1] + tokens[2]);
            }

            input = sc.nextLine();
        }

        names.stream()
                .filter(n -> {
                    boolean isValid = true;
                    for (Predicate<String> prd : predicate.values()) {
                        if (prd.test(n)) {
                            isValid = false;
                        }
                    }
                    return isValid;
                })
                .forEach(e -> System.out.print(e + " "));
    }

    private static Predicate<String> producePredicates(String command, String param) {
        Predicate<String> check = null;

        switch (command) {
            case "Starts with":
                check = str -> str.startsWith(param);
                break;
            case "Ends with":
                check = str -> str.endsWith(param);
                break;
            case "Length":
                check = str -> str.length() == Integer.parseInt(param);
                break;
            case "Contains":
                check = str -> str.contains(param);
                break;

        }

        return check;
    }
}
