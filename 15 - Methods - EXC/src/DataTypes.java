import java.util.Scanner;

public class DataTypes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        printDataType(input, sc);
    }

    private static void printDataType(String input, Scanner sc) {
        if (input.equals("int")) {
            int num = Integer.parseInt(sc.nextLine());
            System.out.println(num * 2);
        } else if (input.equals("real")) {
            double num = Double.parseDouble(sc.nextLine());
            System.out.println(String.format("%.2f", num * 1.5));
        } else if (input.equals("string")) {
            String word = sc.nextLine();
            System.out.println("$"+word+"$");
        }
        return;
    }
}
