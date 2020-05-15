import java.util.Scanner;

public class Samples {
    static void printBorder(int length, char symbol) {
        for (int i = 1; i < length; i++) {
            System.out.println(symbol);
        }
    }

    static void printBorder(int length) {
        printBorder(length, '*');
    }

    static int getMax(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    static int getMax(int a, int b, int c) {
        return getMax(getMax(a, b), c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int max = getMax(3, 5, -1);
        System.out.println(max);


    }
}
