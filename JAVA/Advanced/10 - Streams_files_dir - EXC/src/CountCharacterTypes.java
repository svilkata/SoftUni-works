import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CountCharacterTypes {
    public static void main(String[] args) {
        int countVowels = 0;
        int countConsonants = 0;
        int countPunctuation = 0;

        try {
            FileWriter fileWriter = new FileWriter("src/resources/out.txt");
            String totalString = String.join("", Files.readAllLines(Paths.get("src/resources/input.txt")));

            for (int i = 0; i < totalString.length(); i++) {
                char symbol = totalString.charAt(i);

                if (symbol == 'a' || symbol == 'e' || symbol == 'i' || symbol == 'o' || symbol == 'u') {
                    countVowels++;
                } else if (Character.isLetterOrDigit(symbol) || symbol == '\'' || symbol == '-') {
                    countConsonants++;
                } else if (symbol == '!' || symbol == '?' || symbol == ',' || symbol == '.') {
                    countPunctuation++;
                }
            }

            fileWriter.write(String.format("Vowels: %d%nConsonants: %d%nPunctuation: %d",
                    countVowels, countConsonants, countPunctuation));
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
