import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class SumLines {
    public static void main(String[] args) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter("src/resources/out.txt");
            List<String> strings = Files.readAllLines(Paths.get("src/resources/input.txt"));
            for (String s : strings) {
                long asciiSum = 0;
                for (int i = 0; i < s.length(); i++) {
                    asciiSum+=s.charAt(i);
                }
                System.out.println(asciiSum);
                fileWriter.write(asciiSum + "\n");
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
