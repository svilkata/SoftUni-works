import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\s+"); //all white spaces

        double finalResult = 0;

        for (String s : input) {
            char firstLetter = s.charAt(0);
            char lastLetter = s.charAt(s.length() - 1);
            String numberAsString = s.substring(1, s.length() - 1);

            double number = Double.parseDouble((numberAsString));
            if (number == 0) {
                System.out.println(0);
                return;
            }
            double result = 0.0;
            if (isUpperCase(firstLetter)) {
                result = number * 1.0 / (firstLetter - 64); //от позиция от ASCI към позицията от азбуката
            } else {
                result = number * (firstLetter - 96);
            }

            if (isUpperCase(lastLetter)) {
                result -= (lastLetter - 64); //от позиция от ASCI към позицията от азбуката
            } else {
                result += (lastLetter - 96);
            }
            finalResult += result;
        }

        System.out.println(String.format("%.2f", finalResult));
    }

    private static boolean isUpperCase(char letter) {
        if (letter >= 65 && letter <= 90) {
            return true;
        }
        return false;
    }
}
