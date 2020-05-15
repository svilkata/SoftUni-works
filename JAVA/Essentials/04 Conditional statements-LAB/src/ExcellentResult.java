import java.util.Scanner;

public class ExcellentResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double num = Double.parseDouble(sc.nextLine());

        if (num >= 5.50) {
            System.out.println("Excellent!");
        }

    }

}
