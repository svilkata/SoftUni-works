import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = Integer.parseInt(sc.nextLine());
        ArrayDeque<Integer> bits = new ArrayDeque<>();

        if (number == 0) {
            System.out.println(0);
            return;
        }

        while (number > 0) {
            int leftOver = number % 2;
            bits.push(leftOver);
            number /= 2;
        }

        int size = bits.size();
        for (int i = 0; i < size; i++) {
            System.out.print(bits.pop());
        }
    }
}
