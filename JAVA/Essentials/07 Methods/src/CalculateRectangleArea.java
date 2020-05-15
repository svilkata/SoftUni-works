import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculateRectangleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = Double.parseDouble(sc.nextLine());
        double b = Double.parseDouble(sc.nextLine());

        System.out.println(new DecimalFormat("0.####").format(getArea(a, b)));
    }

    private static double getArea(double a, double b) {
        double area = a * b;
        return area;
    }
}
