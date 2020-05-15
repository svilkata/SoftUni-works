import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");
        String first = input[0];
        String second = input[1];

        int min = Math.min(first.length(), second.length());
        int max = Math.max(first.length(), second.length());

        int sum = 0;
        for (int i = 0; i < max; i++) {
            if (i < min) {
                sum += first.charAt(i) * second.charAt(i);
            } else {
                if (first.length() == max) {
                    sum += first.charAt(i);
                } else {
                    sum += second.charAt(i);
                }
            }
        }

        System.out.println(sum);

    }
}
