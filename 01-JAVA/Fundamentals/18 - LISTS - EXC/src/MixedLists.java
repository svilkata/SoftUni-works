import java.util.*;
import java.util.stream.Collectors;

public class MixedLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> firstList = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> resultList = new ArrayList<>();

        boolean isTwoElementsReachedFirstList = false;
        boolean isTwoElementsReachedSecondList = false;
        while (firstList.size() > 0 && secondList.size() > 0 && (firstList.size() >= 2 || secondList.size() >= 2)) {
            resultList.add(Integer.valueOf(firstList.get(0)));
            firstList.remove(0);

            resultList.add(Integer.valueOf(secondList.get(secondList.size() - 1)));
            secondList.remove(secondList.size() - 1);
            //System.out.println(resultList);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if (firstList.size() == 2) {
            min = Math.min(firstList.get(0), firstList.get(1));
            max = Math.max(firstList.get(0), firstList.get(1));
        } else if (secondList.size() == 2) {
            min = Math.min(secondList.get(0), secondList.get(1));
            max = Math.max(secondList.get(0), secondList.get(1));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i) >= max || resultList.get(i) <= min) {
                resultList.remove(i);
                i--;
            }
        }

        Collections.sort(resultList);

        //System.out.println(min + " " + max);
        printResult(resultList);
    }

    private static void printResult(List<Integer> resultList) {
        for (Integer el : resultList) {
            System.out.print(el + " ");
                    }
    }
}




