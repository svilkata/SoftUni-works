import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindSmallestElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Function<List<Integer>, Integer> findRightMostLement =
                list -> {
            int min = Integer.MAX_VALUE;
            int index = -1;
                    for (int i = 0; i < list.size(); i++) {
                        if (min >= list.get(i)) {
                            min = list.get(i);
                            index = i;
                        }
                    }

                    return index;
                };

        System.out.println(findRightMostLement.apply(Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList())));

    }
}
