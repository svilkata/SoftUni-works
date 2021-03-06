import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {

        Map<String, Integer> wordsAndCount = new HashMap<>();
        try {
            FileWriter fileWriter = new FileWriter("src/resources/results.txt");
            List<String> words = Files.readAllLines(Paths.get("src/resources/words.txt"));
            List<String> target = Files.readAllLines(Paths.get("src/resources/text.txt"));

            String[] words2 = words.get(0).split("\\s+");
            String[] text = target.get(0).replaceAll("[,.]", "").split("\\s+");
            System.out.println();

            for (String word : words2) {
                wordsAndCount.putIfAbsent(word, 0);

                for (String targetWord : text) {
                    if (word.equals(targetWord)) {
                        int count = wordsAndCount.get(word) + 1;
                        wordsAndCount.put(word, count);
                    }
                }
            }

            wordsAndCount.entrySet().stream()
                    .sorted((x1, x2) -> {
                        return Integer.compare(x2.getValue(), x1.getValue());
                    })
                    .forEach(entry -> {
                        try {
                            fileWriter.write(String.format("%s - %d%n", entry.getKey(), entry.getValue()));
                            fileWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        System.out.printf("%s - %d%n", entry.getKey(), entry.getValue());
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
