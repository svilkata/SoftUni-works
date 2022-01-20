import java.util.Scanner;

public class VariationsWithRepetition {
    public static String[] elements;
    public static String[] variations;
    public static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        elements = sc.nextLine().split("\\s+");

        int k = Integer.parseInt(sc.nextLine()); // k Slots
        variations = new String[k];
        used = new boolean[elements.length];

        variationsMethod(0);
    }

    private static void variationsMethod(int index) {
        if (index == variations.length) {
            print(variations);
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            variations[index] = elements[i];
            variationsMethod(index + 1);

        }
    }


    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
