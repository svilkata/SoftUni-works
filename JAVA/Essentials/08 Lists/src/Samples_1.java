import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Samples_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 4, 5));
        List<Integer> secondList = new ArrayList<>(Arrays.asList(2, 3, 6, 7, 8));

        List<Integer> merged = new ArrayList<>();

        while (!firstList.isEmpty() || !secondList.isEmpty()) {
            if (firstList.isEmpty()) {
                merged.add(secondList.get(0));
                secondList.remove(0);
            } else if (secondList.isEmpty()) {
                merged.add(firstList.get(0));
                firstList.remove(0);
            } else {
                if (firstList.get(0) < secondList.get(0)) {
                    merged.add(firstList.get(0));
                    firstList.remove(0);
                } else {
                    merged.add(secondList.remove(0));
                }
            }
        }



        for (Integer element : merged) {
            System.out.print(element + " ");
        }

    }
}
