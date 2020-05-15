import java.util.Scanner;

public class RepeatString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String toRepeat = sc.nextLine();
        int times = Integer.parseInt(sc.nextLine());

        String result = repeat(toRepeat, times);

        System.out.println(result);
    }

    private static String repeat(String toRepeat, int times) {
        String temp = "";
        for (int i = 1; i <=times ; i++) {
            temp = temp.concat(toRepeat);
        }
        return temp;
    }
}
