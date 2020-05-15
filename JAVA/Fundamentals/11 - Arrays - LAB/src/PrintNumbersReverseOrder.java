import java.util.Scanner;

public class PrintNumbersReverseOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(sc.nextLine());
        }

        for (int i = n-1; i >=0 ; i--) {
            System.out.print(arr[i] + " ");
        }

    }
}
