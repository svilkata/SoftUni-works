import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class AllCapitals {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("src/resources/out.txt");
            List<String> strings = Files.readAllLines(Paths.get("src/resources/input.txt"));

               for (String s : strings) {
                fileWriter.write(s.toUpperCase() + "\r\n");
                fileWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
