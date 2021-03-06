import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        Set<String> userNames = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            userNames.add(sc.nextLine());
        }

        userNames.stream()
                .forEach(x -> System.out.println(x));
    }
}
