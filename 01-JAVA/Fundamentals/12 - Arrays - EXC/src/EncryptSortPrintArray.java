import java.util.Arrays;
import java.util.Scanner;

public class EncryptSortPrintArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] sumCodes = new int[n];
        String input = "";

        for (int i = 0; i < n; i++) {
            input = sc.nextLine();

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'a' || input.charAt(j) == 'e' || input.charAt(j) == 'i'
                        || input.charAt(j) == 'o' || input.charAt(j) == 'u') {
                    sumCodes[i] += input.charAt(j) * input.length();
                } else {
                    sumCodes[i] += input.charAt(j) / input.length();
                }
            }
        }

       Arrays.sort(sumCodes);


        for (int el : sumCodes) {
            System.out.println(el);
        }


    }
}
