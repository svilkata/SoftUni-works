import java.util.Arrays;
import java.util.Scanner;

public class PermutationsWIthRepetSwapOptimized {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = {"3", "5", "1", "5", "5"};
        Arrays.sort(arr); // 1 3 5 5 5
        permuteRep(arr, 0, arr.length - 1);
    }

    static void permuteRep(String[] arr, int start, int end) {
        print(arr);
        for (int left = end - 1; left >= start; left--)
            for (int right = left + 1; right <= end; right++) {
                if (!arr[left].equals(arr[right])) {
                    swap(arr, left, right);
                    permuteRep(arr, left + 1, end);
                }
                String firstElement = arr[left];
                for (int i = left; i <= end - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[end] = firstElement;
            }
    }

    private static void swap(String[] arr, int first, int second) {
        String temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;

    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}

