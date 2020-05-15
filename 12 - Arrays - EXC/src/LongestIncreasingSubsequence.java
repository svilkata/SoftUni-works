import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();
        int[] len = new int[input.length];
        int[] previous = new int[input.length];

        int left = 0;
        for (int p = 0; p < input.length; p++) {
            if (left < p) {
                len[p] = 1;
            } else {
                len[p] = 1 + len[left];
            }
        }

    }
}
