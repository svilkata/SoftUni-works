import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {5, 4, 2, 1, 3};
        sort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) { //брой мехурчета изплували / най-тежкия накрая
            for (int j = 1; j < arr.length - i; j++) { //самото сравнение на съседни
                if (arr[j - 1] > arr[j]) { //възходящо
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }


}
