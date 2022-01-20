import java.util.*;
import java.util.stream.Collectors;

public class WordCruncher {
    public static List<String> words;
    public static List<String> combined = new ArrayList<>();
    public static String target;

    public static Map<Integer, List<String>> table = new HashMap<>();
    public static Map<String, Integer> occurences = new HashMap<>();

    public static Set<String> out = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        words = Arrays.stream(sc.nextLine().split(", ")).collect(Collectors.toList());
        target = sc.nextLine();

        words.removeIf(next -> !target.contains(next));

        for (String substr : words) {
            occurences.putIfAbsent(substr, 0);
            occurences.put(substr, occurences.get(substr) + 1);

            int index = target.indexOf(substr);

            while (index != -1) {
                table.putIfAbsent(index, new ArrayList<>());
                table.get(index).add(substr);
                index = target.indexOf(substr, index + 1);
            }
        }

        permute(0);

        for (String t : out) {
            System.out.println(t);
        }
    }

    private static void permute(int index) {
        if (index == target.length()) {
            print();
        } else if (table.containsKey(index)) {
            List<String> strings = table.get(index);

            for (String str : strings) {
                if (occurences.get(str) > 0) {
                    occurences.put(str, occurences.get(str) - 1); // броим го, че е използвано
                    combined.add(str);
                    permute(index + str.length());
                    combined.remove(combined.size() - 1);
                    occurences.put(str, occurences.get(str) + 1); // backTracking
                }

            }
        }
    }

    private static void print() {
        String actual = String.join("", combined);
        if (actual.contains(target)) {
            out.add(String.join(" ", combined));
        }
    }
}
