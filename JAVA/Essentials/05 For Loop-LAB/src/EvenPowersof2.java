import java.util.Scanner;

public class EvenPowersof2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i <= n ; i++) {
            if (i % 2 == 0) {
                System.out.println(Math.round(Math.pow(2, i)));
            }
        }
    }
}
