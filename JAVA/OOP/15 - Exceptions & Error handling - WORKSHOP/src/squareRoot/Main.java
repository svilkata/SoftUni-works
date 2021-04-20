package squareRoot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            if (num < 0) {
                throw new NumberFormatException();
            }
            System.out.println(Math.sqrt(num));
        } catch (NumberFormatException e) {
            try {
                throw new InvNumException();
            } catch (InvNumException inv) {
                System.out.println(inv.getMessage());
            }
        } finally {
            System.out.println("Good bye");
        }


    }
}
