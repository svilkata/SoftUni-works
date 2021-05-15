import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> book = new LinkedHashMap<>();
        String line = "";
        String key = "";
        int countLines = 0;

        while (!"stop".equals(line = sc.nextLine())) {
            countLines++;
            if (countLines % 2 == 0) { //even
                book.put(key, line);
            } else { //odd
                key = line;
                if (!book.containsKey(key)) {
                    book.put(key, "");
                }
            }
        }

        for (Map.Entry<String, String> entry : book.entrySet()) {
            if (!(entry.getValue().contains(".us") || entry.getValue().contains(".com") ||
                    entry.getValue().contains(".uk"))) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

        System.out.println();
    }
}
