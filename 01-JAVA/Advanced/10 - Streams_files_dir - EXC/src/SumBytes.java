import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SumBytes {
    public static void main(String[] args) {

        try {
            FileWriter fileWriter = new FileWriter("src/resources/out.txt");
            List<String> strings = Files.readAllLines(Paths.get("src/resources/input.txt"));
            long asciiSum = 0;
            for (String s : strings) {
                for (int i = 0; i < s.length(); i++) {
                    asciiSum += s.charAt(i);
                }
            }
            fileWriter.write(asciiSum + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
