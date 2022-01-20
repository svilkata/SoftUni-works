import java.util.Arrays;

public class SortWithoutComparison {
    public static int[] counts;

    public static void main(String[] args) {
        int[] arr = {13, 5, 2, 2, 5};
        int max = Arrays.stream(arr).max().getAsInt();

        counts = new int[max + 1];
        sort(arr);

        for (int index = 0; index < counts.length; index++) {
            if (counts[index] != 0) {
                for (int i = 0; i < counts[index]; i++) {
                    System.out.print(index + " ");
                }
            }
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i]]++;
        }
    }

}
