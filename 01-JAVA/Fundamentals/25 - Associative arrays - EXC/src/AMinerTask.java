import java.util.LinkedHashMap;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        LinkedHashMap<String, Integer> resources = new LinkedHashMap<>();

        while (!"stop".equals(input)) {
            int count = Integer.parseInt(sc.nextLine());

            resources.putIfAbsent(input, 0);
            int oldCount = resources.get(input);
            resources.put(input, oldCount + count);

            input = sc.nextLine();
        }

        resources
                .forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));
    }
}
