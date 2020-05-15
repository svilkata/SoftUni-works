import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TakeSkipRope {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> nonDigitsString = Arrays.stream(sc.nextLine().split("")).collect(Collectors.toList());
        List<Integer> digitsString = new ArrayList<>();

        int br = 0;
        for (int i = 0; i < nonDigitsString.size(); i++) {
            if (Character.isDigit(nonDigitsString.get(i).charAt(0))) {
                digitsString.add(Integer.parseInt(nonDigitsString.get(i)));
                nonDigitsString.remove(i);
                br++;
                i--;
            }
        }

        List<Integer> take = new ArrayList<>();
        List<Integer> skip = new ArrayList<>();


        for (int i = 0; i < digitsString.size(); i++) {
            if (i % 2 == 0) {
                take.add(digitsString.get(i));
            } else {
                skip.add(digitsString.get(i));
            }
        }
        boolean isOddDigitsString = digitsString.size() % 2 == 1;
        digitsString = null;

        String result = "";


        int k = 0;
        int startIndex = 0;
        while (k <= take.size() - 1) { //take винаги има по-голяма с 1 или равна дължина на skip
            result += takeNonDigits(nonDigitsString, startIndex, take.get(k)) + "";

              startIndex += take.get(k);
            if (!(k == take.size() - 1 && isOddDigitsString)) {
                startIndex += skip.get(k);
            }
            k++;
        }

        System.out.println(result);
    }

    private static String takeNonDigits(List<String> nonDigitsString, int startIndex, Integer takeCharacters) {
        String temp = "";
        int endIndex = startIndex + takeCharacters;
        if (endIndex >= nonDigitsString.size()) {
            endIndex = nonDigitsString.size();
        }
        for (int i = startIndex; i < endIndex; i++) {
            temp += nonDigitsString.get(i) + "";
        }
        return temp;
    }
}
