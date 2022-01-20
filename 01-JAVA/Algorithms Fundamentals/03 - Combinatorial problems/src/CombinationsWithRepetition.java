import java.util.Scanner;

public class CombinationsWithRepetition {
    public static String[] elements;
    public static String[] variations;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        elements = sc.nextLine().split("\\s+");

        int k = Integer.parseInt(sc.nextLine());
        variations = new String[k];

        combinations(0, 0);
    }

    private static void combinations(int index, int start) {
        if (index == variations.length) { //дъно
            print(variations);
        } else {
            for (int i = start; i < elements.length; i++) {
                variations[index] = elements[i];
                combinations(index + 1, i);
            }
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
