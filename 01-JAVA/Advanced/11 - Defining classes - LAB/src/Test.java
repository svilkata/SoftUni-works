import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long start = System.currentTimeMillis();
        run(нещо си);
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    private static void run(Scanner sc) {
        int sum = 0;
        for (int i = 0; i < 200000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
