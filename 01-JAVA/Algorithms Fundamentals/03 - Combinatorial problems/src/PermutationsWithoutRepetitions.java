import java.util.Scanner;

public class PermutationsWithoutRepetitions {
    public static String[] elements;
    public static String[] permutes;
    public static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        elements = sc.nextLine().split("\\s+");
        permutes = new String[elements.length];
        used = new boolean[elements.length];

        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) { //ако не е използван дадения елемент
                used[i] = true;
                permutes[index] = elements[i];
                permute(index + 1);
                used[i] = false;  // the Backtracking
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", permutes));
    }
}
