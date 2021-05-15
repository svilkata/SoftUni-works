import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<Character, Integer> dictionary = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (!dictionary.containsKey(symbol)) {
                dictionary.put(symbol, 1);
            } else {
                int count = dictionary.get(symbol) +1;
                dictionary.put(symbol, count);
            }
        }

//        System.out.println(dictionary.keySet());

        for (Map.Entry<Character, Integer> entry : dictionary.entrySet()) {
            System.out.printf("%c: %d time/s\n", entry.getKey(), entry.getValue());
        }
    }
}
