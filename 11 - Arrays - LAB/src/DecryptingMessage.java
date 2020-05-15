import java.util.Scanner;

public class DecryptingMessage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int key = Integer.parseInt(sc.nextLine());
        int nLines = Integer.parseInt(sc.nextLine());
        String decryptedMessage = "";

        for (int i = 1; i <= nLines ; i++) {
            int inputChar = sc.nextLine().charAt(0);

            decryptedMessage += ""+ (char)(inputChar+key);
        }

        System.out.println(decryptedMessage);
    }
}
