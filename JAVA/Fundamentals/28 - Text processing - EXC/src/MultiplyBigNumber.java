import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String numberAsString = sc.nextLine();
        int mutliplier = Integer.parseInt(sc.nextLine());
        if (mutliplier == 0) {
            System.out.println(0);
            return;
        }

        while (numberAsString.charAt(0) == '0') { // 00000002
            numberAsString = numberAsString.substring(1);
        }

        StringBuilder result = new StringBuilder();
        int reminder = 0;

        for (int i = numberAsString.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(numberAsString.charAt(i) + "");
            int product = digit * mutliplier + reminder;
            result.append(product % 10);
            reminder = product / 10;
        }

        if (reminder != 0) {
            result.append(reminder);
        }


        System.out.println(result.reverse().toString());
    }
}
