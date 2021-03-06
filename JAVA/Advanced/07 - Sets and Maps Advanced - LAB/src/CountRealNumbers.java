import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] values = Arrays.stream(sc.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();

        Map<Double, Integer> valuesWithOccurences = new LinkedHashMap<>();

        for (double value : values) {
            if (!valuesWithOccurences.containsKey(value)) {
                valuesWithOccurences.put(value, 1);
            } else {
                valuesWithOccurences.put(value, valuesWithOccurences.get(value) + 1);
            }
        }

        for (Map.Entry<Double, Integer> entry : valuesWithOccurences.entrySet()) {
            System.out.println(String.format("%.1f -> %d", entry.getKey(), entry.getValue()));
        }

    }
}
