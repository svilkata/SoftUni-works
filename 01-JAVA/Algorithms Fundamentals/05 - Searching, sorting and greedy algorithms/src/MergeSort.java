import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();
        mergeSort(arr, 0, arr.length - 1);

        StringBuilder builder = new StringBuilder();
        for (int num : arr) {
            builder.append(num).append(" ");
        }
        System.out.println(builder.toString());
    }

    private static void mergeSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = (begin + end) / 2;

        mergeSort(arr, begin, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, begin, mid, end); //backtracking
    }

    private static void merge(int[] arr, int begin, int mid, int end) {
        if (mid < 0 || mid >= arr.length || arr[mid] < arr[mid + 1]) { // ако последния елемент на сортирания вече събмасив е по-малък от първият елемент на следващия сортиран събмасив, то пропускаме сортировката
            return;
        }
        int left = begin;
        int right = mid + 1;

        int[] helper = new int[arr.length];
        for (int i = begin; i <= end; i++) {
            helper[i] = arr[i];
        }

        for (int i = begin; i <= end; i++) {
            if (left > mid) { //when 1st substring is over
                arr[i] = helper[right++]; //we take next element from the sorted 2nd substring
            } else if (right > end) { //when 2nd substring is over
                arr[i] = helper[left++]; //we take next element from the sorted 1st substring
            } else if (helper[left] < helper[right]) { //when the element of 1st substring is lower than the element of 2nd substing
                arr[i] = helper[left++]; //arr[i] is with new value helper[left]
            } else {
                arr[i] = helper[right++]; //arr[i] is with new value helper[right]
            }
        }
    }

}
