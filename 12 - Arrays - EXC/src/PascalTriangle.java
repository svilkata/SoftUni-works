import java.util.Scanner;

public class PascalTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        //int[] arr1 = new int[] {1};
        int[] arr = new int[] {1, 1};
        int[] currArr;

        for (int i = 1; i <= n; i++) {


            currArr = new int[i];
            currArr[0] = 1;
            for (int j = 1; j <= currArr.length-2; j++) {
                currArr[j] = arr[j-1] + arr[j];
              }
            currArr[i-1] = 1;

            arr = currArr;

            for (int el: arr) {
                System.out.print(el + " ");
            }
            System.out.println();

        }
    }
}
