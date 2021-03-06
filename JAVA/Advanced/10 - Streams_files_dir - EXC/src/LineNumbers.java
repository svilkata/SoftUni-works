import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LineNumbers {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("src/resources/out.txt");
            List<String> strings = Files.readAllLines(Paths.get("src/resources/inputLineNumbers.txt"));

            for (int i = 0; i < strings.size(); i++) {
                fileWriter.write(String.format("%d. %s%n", i+1, strings.get(i)));
                fileWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
