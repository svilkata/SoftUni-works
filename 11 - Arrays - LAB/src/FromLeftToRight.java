import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class FromLeftToRight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nLines = Integer.parseInt(sc.nextLine());
        BigInteger num1, num2, numToCalculate;
        String[] arr = new String[2];

        for (int i = 0; i < nLines; i++) {
            arr = sc.nextLine().split(" ");
            num1 = new BigInteger(arr[0]);
            num2 = new BigInteger(arr[1]);


            if (num1.compareTo(num2) > 0) {
                numToCalculate = num1;
            } else {
                numToCalculate = num2;
            }

            int sum = 0;

            String toCalculateNumber = numToCalculate.toString();

            for (int j = 0; j < toCalculateNumber.length(); j++) {
                if (toCalculateNumber.charAt(j) != '-') {
                    sum+= Integer.parseInt(toCalculateNumber.charAt(j) + "");
                }

            }

            System.out.println(Math.abs(sum));
        }

    }
}
