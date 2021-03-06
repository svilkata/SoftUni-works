import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FindEvensOdds {
    public static class Nums {
        private int lll;

        public int getLll() {
            return lll;
        }

        public void setLll(int lll) {
            this.lll = lll;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] ints = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(x -> Integer.parseInt(x))
                .toArray();

        int lowerBound = ints[0];
        int upperBound = ints[1];
        String command = sc.nextLine();

        List<Nums> allNums = new LinkedList<>();

        for (int i = lowerBound; i <= upperBound ; i++) {
            Nums number = new Nums();
            number.setLll(i);
            allNums.add(number);
        }

        Map<String, Predicate<Nums>> predicateMap = new LinkedHashMap<>();

        predicateMap.put("odd", (z) -> z.getLll() % 2 != 0);
        predicateMap.put("even", (z) -> z.getLll() % 2 == 0);

        Map<String, Consumer<Nums>> consumerMap = new LinkedHashMap<>();
        consumerMap.put("odd", z -> System.out.print(z.getLll() + " "));
        consumerMap.put("even", z -> System.out.print(z.getLll() + " "));

        allNums.stream()
                .filter(predicateMap.get(command))
                .forEach(consumerMap.get(command));



    }
}
