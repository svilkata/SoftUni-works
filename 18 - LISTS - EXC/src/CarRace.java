import java.util.Arrays;
import java.util.Scanner;

public class CarRace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] timeToPassSteps = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        double sumLeft = 0.0, sumRight = 0.0;

        for (int i = 0; i < timeToPassSteps.length / 2; i++) {
            if (timeToPassSteps[i] == 0) {
                sumLeft *= 0.8;
            } else {
                sumLeft += timeToPassSteps[i];
            }

            if (timeToPassSteps[timeToPassSteps.length - i - 1] == 0) {
                sumRight *= 0.8;
            } else {
                sumRight += timeToPassSteps[timeToPassSteps.length - i - 1];
            }
        }

        if (sumLeft <= sumRight) {
            System.out.printf("The winner is left with total time: %.1f", sumLeft);
        } else {
            System.out.printf("The winner is right with total time: %.1f", sumRight);
        }

    }
}
