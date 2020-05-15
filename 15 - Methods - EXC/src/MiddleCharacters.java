import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        middleLetters(input);
    }

    private static void middleLetters(String input) {
        String output = "";

        if (input.length() % 2 == 0) {
            output = input.charAt(input.length() / 2 -1) + ""+ input.charAt(input.length()/2);
        } else {
            output = input.charAt(input.length() / 2) + "";
        }

        System.out.println(output);
    }
}
