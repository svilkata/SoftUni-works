import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        while (!input.equals("Party!")) {
            String[] tokens = input.split(" ");

            Predicate<String> predicate = producePredicates(tokens[1], tokens[2]);
            List<String> temp = new ArrayList<>();

            for (String name : names) {
                if (predicate.test(name)) {
                    temp.add(name);
                }
            }

            switch (tokens[0]) {
                case "Remove":
                    names.removeAll(temp);
                    break;
                case "Double":
                    names.addAll(temp);
                    break;
            }
            input = sc.nextLine();
        }

        if (names.isEmpty()) {
            System.out.println("Nobody is going to the party!");
            return;
        }

        System.out.println(names.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "))
                + " are going to the party!");
    }

    private static Predicate<String> producePredicates(String command, String param) {
        Predicate<String> check = null;
        switch (command) {
            case "StartsWith": check = str -> str.startsWith(param);
                break;
            case "EndsWith": check = str -> str.endsWith(param);
                break;
            case "Length": check = str -> str.length() == Integer.parseInt(param);
                break;
        }
        return check;
    }
}
