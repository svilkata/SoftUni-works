import java.util.Scanner;

public class Samples {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 5;
        int k = 3;
        int[] arr = new int[k];

        while (true) {
            print(arr);
            int index = k - 1;
            while (index >= 0 && arr[index] == n-1)
                index--;
            if (index < 0)
                break;
            arr[index]++;
            for (int i = index + 1; i < k; i++)
                arr[i] = 0;
        }
    }
    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }



}
