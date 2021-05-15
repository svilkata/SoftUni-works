import java.util.Scanner;

public class InchesToCentimeters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double num = Double.parseDouble(sc.nextLine());
        num*= 2.54;

        System.out.println(num);
    }
}
