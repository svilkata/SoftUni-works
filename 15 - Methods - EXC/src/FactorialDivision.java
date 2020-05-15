import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double firstNumber = Double.parseDouble(sc.nextLine());
        double secondNumber = Double.parseDouble(sc.nextLine());

        double result = factorialDiv(firstNumber, secondNumber);
        System.out.printf("%.2f", result);

    }

    private static double factorialDiv(double num1, double num2) {
        double firstFctorial = findFactorial(num1);
        double secondFctorial = findFactorial(num2);
        double result = firstFctorial / secondFctorial;

        return result;
    }

    private static double findFactorial(double num1) {
        double result = 1.0;
        for (int i = 1; i <= num1; i++) {
            result *= i;
        }
        return result;
    }
}
