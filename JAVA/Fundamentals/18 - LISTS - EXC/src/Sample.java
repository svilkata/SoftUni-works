import java.util.Scanner;

public class Sample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] hhh = input.split( "", 5);

        for (String s : hhh) {
            System.out.println(s);
        }
    }
}
