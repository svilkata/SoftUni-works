import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tok = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = tok[0];
        int m = tok[1];
//        System.out.println();
        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            firstSet.add(Integer.parseInt(sc.nextLine()));
        }
        for (int i = 0; i < m; i++) {
            secondSet.add(Integer.parseInt(sc.nextLine()));
        }

        firstSet.retainAll(secondSet);
        System.out.println(firstSet.toString().replaceAll("[\\[\\],]", ""));

    }
}
