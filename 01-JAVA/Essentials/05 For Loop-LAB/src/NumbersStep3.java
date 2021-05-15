import java.util.Scanner;

public class NumbersStep3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <=num ; i+=3) {
            System.out.println(i);
        }
    }
}
