import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> items = Arrays.stream(sc.nextLine().split("\\s+")).collect(Collectors.toList());
        int bombValue = sc.nextInt();
        int bombPower = sc.nextInt();
        //System.out.println();

        while (items.contains(bombValue + "")) {
            int bombIndex = items.indexOf(bombValue + "");

            int leftBound = Math.max(bombIndex - bombPower, 0);
            int rightBound = Math.min(bombIndex + bombPower, items.size() - 1);

            for (int i = rightBound; i >= leftBound; i--) {
                items.remove(i);
            }
        }


        int sum = 0;
        for (String el : items) {
            sum += Integer.parseInt(el);
        }
        System.out.println(sum);
    }
}
