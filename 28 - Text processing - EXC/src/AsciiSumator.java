import java.util.Scanner;

public class AsciiSumator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char symbol1 = sc.nextLine().charAt(0);
        char symbol2 = sc.nextLine().charAt(0);
        if (symbol1 > symbol2) {
            char temp = symbol1;
            symbol1 = symbol2;
            symbol2 = temp;
        }
        String input = sc.nextLine();

        int sum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (symbol1 < input.charAt(i) && input.charAt(i) < symbol2) {
                sum+= input.charAt(i);
            }
        }

        System.out.println(sum);
    }
}
