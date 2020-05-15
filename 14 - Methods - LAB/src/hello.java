import java.text.DecimalFormat;
import java.util.Scanner;

public class hello {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int firstNumber = Integer.parseInt(sc.nextLine());
        char operator = sc.nextLine().charAt(0);
        int secondNumber = Integer.parseInt(sc.nextLine());


        System.out.println(new DecimalFormat("0.##").format(calculate(firstNumber, operator, secondNumber)));
    }

    private static double calculate(int firstNumber, char operator, int secondNumber) {
        double result = 0.0;

        switch (operator) {
            case '*': {
                result = firstNumber * secondNumber * 1.0;
                break;
            }
            case '/': {
                result = 1.0 * firstNumber/ secondNumber;
                break;
            }
            case '+': {
                result = firstNumber + secondNumber;
                break;
            }
            case '-': {
                result = firstNumber - secondNumber;
                break;
            }
        }

        return result;

    }
}
