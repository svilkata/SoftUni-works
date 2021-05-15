import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] arrTrain = new int[n];

        for (int i = 0; i < arrTrain.length; i++) {
            arrTrain[i] = Integer.parseInt(sc.nextLine());
        }

        int sum = 0;
        for (int wagon : arrTrain) {
            sum += wagon;
            System.out.print(wagon + " ");
        }

        System.out.println();
        System.out.println(sum);


    }
}
