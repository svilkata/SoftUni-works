import java.util.*;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Set<String> periodicTable = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] elements = sc.nextLine().split("\\s+");
            periodicTable.addAll(Arrays.asList(elements));
//            for (String element : elements) {
//                periodicTable.add(element);
//            }
        }

        System.out.println(String.join(" ", periodicTable));
    }
}
