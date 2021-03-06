import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MergeTwoFiles {
    public static void main(String[] args) {

        try {
            FileWriter fileWriter = new FileWriter("src/resources/results.txt");
            List<String> file1 = Files.readAllLines(Paths.get("src/resources/inputOne.txt"));
            List<String> file2 = Files.readAllLines(Paths.get("src/resources/inputTwo.txt"));

            for (String word : file1) {
                fileWriter.write(word + "\r\n");
                fileWriter.flush();
            }

            for (String word : file2) {
                fileWriter.write(word + "\r\n");
                fileWriter.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
