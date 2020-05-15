import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        Map<Double, Integer> map = new TreeMap<>();
        for (double number : numbers) {
            if (!map.containsKey(number)) {
                map.put(number, 1);
            } else {
                map.put(number, map.get(number) + 1);
            }
        }

        for (Map.Entry<Double, Integer> entry : map.entrySet()) {
            System.out.printf("%.0f -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
