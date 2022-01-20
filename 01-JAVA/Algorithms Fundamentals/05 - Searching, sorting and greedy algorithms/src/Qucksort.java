import java.util.Arrays;
import java.util.Scanner;

public class Qucksort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();
        quickSort(arr, 0, arr.length - 1);

        StringBuilder builder = new StringBuilder();
        for (int num : arr) {
            builder.append(num).append(" ");
        }
        System.out.println(builder.toString());
    }

    // low is the start index
    // high is the end index
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); //pi in the beginning is the 1st element with 0 index

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /*This method takes last element as pivot, places the pivot at its correct position in sorted array,
    and places all smaller (smaller than pivot) to left of pivot, and all greater elements to right of pivot
    */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of the smaller element
        for (int j = low; j < high; j++) {
            //If current element is smaller or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);

        return i + 1;
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

}
