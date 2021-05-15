import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class ReverseStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();

        while (!word.equals("end")) {
            String reversed = reverse(word);

            System.out.println(word + " = " + reversed);

            word = sc.nextLine();
        }

    }


    private static String reverse(String word) {
        String result = "";
        for (int i = word.length()-1; i >= 0 ; i--) {
            result+= word.charAt(i);
        }
        return  result;
    }
}
