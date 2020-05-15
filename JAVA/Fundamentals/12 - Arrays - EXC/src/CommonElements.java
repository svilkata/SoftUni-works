import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] firstArr = sc.nextLine().split(" ");
        String[] secondArr = sc.nextLine().split(" ");

        for (String secondEl : secondArr) {
            for (String firstEl : firstArr) {
                if (secondEl.equals(firstEl)) {
                    System.out.print(firstEl + " ");
                }
            }
        }
    }
}
