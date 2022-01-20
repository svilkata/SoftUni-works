import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Shuffling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {13, 15, 12, 24, 59};
        Arrays.sort(arr);

        getAsRand(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void getAsRand(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, rand.nextInt(arr.length-1));
        }
   }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
