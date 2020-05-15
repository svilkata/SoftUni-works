import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergingLists {
    private static String joinElementsByDelimeter(List<Integer> items, String delimeter) {
        String output = "";
        for (Integer element : items) {
            output += (element + delimeter);
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> firstList = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).
                collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).
                collect(Collectors.toList());
//        List<Integer> secondList = new ArrayList<Integer>(Arrays.asList(6, 7, 8));

        List<Integer> merged = new ArrayList<>();

        int maxSize = Math.max(firstList.size(), secondList.size());

        for (int i = 0; i < maxSize; i++) {
            if (i < firstList.size()) {
                merged.add(firstList.get(i));
            }

            if (i < secondList.size()) {
                merged.add(secondList.get(i));
            }
        }

        System.out.println(joinElementsByDelimeter(merged, " "));

    }
}
