import java.util.Scanner;

public class TribSeq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nNum = Integer.parseInt(sc.nextLine());

        if (nNum == 1) {
            System.out.println("1");
        } else if (nNum == 2) {
            System.out.println("1 1");
        } else {
            int[] arr = new int[nNum];
            arr[0] = 1;
            arr[1] = 1;
            arr[2] = 2;

            for (int i = 3; i < arr.length; i++) {
                arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
            }

            for (int el : arr) {
                System.out.print(el + " ");

            }
        }


    }
}
