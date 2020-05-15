import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.sort;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] sumCodes = new int[n];
        String input = "";

        for (int i = 0; i < n; i++) {
            input = sc.nextLine();
            int currentSum = 0;

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'a' || input.charAt(j) == 'e' || input.charAt(j) == 'i'|| input.charAt(j) == 'o' || input.charAt(j) == 'u'
                || input.charAt(j) == 'A' || input.charAt(j) == 'E' || input.charAt(j) == 'I'|| input.charAt(j) == 'O' || input.charAt(j) == 'U') {
                    currentSum += input.charAt(j) * input.length();
                } else {
                    currentSum += input.charAt(j) / input.length();
                }
            }

            sumCodes[i] = currentSum;
        }

        sort(sumCodes);


        for (int el : sumCodes) {
            System.out.println(el);
        }

    }
}
