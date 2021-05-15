import java.util.Scanner;

public class FishTank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = Integer.parseInt(sc.nextLine());
        int width = Integer.parseInt(sc.nextLine());
        int height = Integer.parseInt(sc.nextLine());
        double procent = Double.parseDouble(sc.nextLine());

        double volume = length * width * height * 0.001;

        System.out.printf("%.3f", volume * (100-procent) / 100.0);
    }
}
