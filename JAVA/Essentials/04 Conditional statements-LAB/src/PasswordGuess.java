import java.util.Scanner;

public class PasswordGuess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String pass = sc.nextLine();

        if (!pass.equals("s3cr3t!P@ssw0rd")) {
            System.out.println("Wrong password!");
        }  else System.out.println("Welcome");
    }
}
