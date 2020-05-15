import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergingList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> left = readList(sc);
        List<Integer> right = readList(sc);
        List<Integer> result = new ArrayList<>();

        int i = 0;
        while (i < left.size() && i < right.size()) {
            result.add(left.get(i));
            result.add(right.get(i));

            i++;
        }

        if (i < left.size()) {
            addLeftOvers(result, left, i);
        } else if (i < right.size()) {
            addLeftOvers(result, right, i);
        }

        printResult(result);

    }

    private static void printResult(List<Integer> result) {
        for (Integer el : result) {
            System.out.print(el + " ");
        }

    }

    private static void addLeftOvers(List<Integer> result, List<Integer> left, int i) {
        for (int j = i; j < left.size(); j++) {
            result.add(left.get(j));
        }
    }

    private static List<Integer> readList(Scanner sc) {
        String[] split = sc.nextLine().split(" ");

        List<Integer> result = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        return result;
    }
}
