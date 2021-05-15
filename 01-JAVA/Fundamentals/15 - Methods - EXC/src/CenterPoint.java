import java.util.Scanner;

public class CenterPoint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x1 = Double.parseDouble(sc.nextLine());
        double y1 = Double.parseDouble(sc.nextLine());
        double x2 = Double.parseDouble(sc.nextLine());
        double y2 = Double.parseDouble(sc.nextLine());

        closestTo00(x1, y1, x2, y2);
    }

    private static void closestTo00(double x1, double y1, double x2, double y2) {
        double distanceFromZeroZero1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2));
        double distanceFromZeroZero2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2));


        if (distanceFromZeroZero1 <= distanceFromZeroZero2) {
            System.out.printf("(%.0f, %.0f)", x1, y1);
        } else {
            System.out.printf("(%.0f, %.0f)", x2, y2);
        }

    }
}
