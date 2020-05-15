import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        inBetween(sc);
    }

    private static void inBetween(Scanner sc) {
        int firstSymbol = sc.nextLine().charAt(0);
        int secondSymbol = sc.nextLine().charAt(0);

        if (firstSymbol > secondSymbol) {
            int temp = firstSymbol;
            firstSymbol = secondSymbol;
            secondSymbol = temp;
        }

        for (int i = firstSymbol + 1; i <= secondSymbol -1 ; i++) {
            System.out.print((char)i + " ");
        }

    }
}
