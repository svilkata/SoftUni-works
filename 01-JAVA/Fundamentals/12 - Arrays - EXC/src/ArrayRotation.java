import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");
        int rotate = Integer.parseInt(sc.nextLine());
        rotate %= input.length;

        for (int i = 0; i < rotate; i++) {
            String temp = input[0];
            for (int j = 0; j < input.length-1; j++) {
                input[j] = input[j+1];
            }
            input[input.length-1] = temp;
        }

        System.out.println(String.join(" ", input));

    }
}
