import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Robotics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(";");

        String[] robots = new String[input.length];
        int[] processTimes = new int[input.length];
        int[] currentRobotTime = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            String[] tokens = input[i].split("-");
            robots[i] = tokens[0];
            processTimes[i] = Integer.parseInt(tokens[1]);
            currentRobotTime[i] = 0;
        }

        int[] inputTime = Arrays.stream(sc.nextLine().split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int time = inputTime[0] * 60*60 + inputTime[1] * 60 + inputTime[2];

        ArrayDeque<String> products = new ArrayDeque<>();

        String product = "";

        while (!(product = sc.nextLine()).equals("End")) {
            products.offer(product);
        }

        while (!products.isEmpty()) {
            for (int i = 0; i < robots.length; i++) {
                if (currentRobotTime[i] > 0) {
                    currentRobotTime[i]--;
                }
            }

            time++;

            String currentItem = products.poll();
            boolean isTaken = false;
            for (int i = 0; i < robots.length; i++) {
                if (currentRobotTime[i] == 0) {
                    System.out.printf("%s - %s [%s]%n", robots[i], currentItem, getTime(time));
                    currentRobotTime[i] = processTimes[i];
                    isTaken = true;
                    break;
                }
            }

            if (!isTaken) {
                products.offer(currentItem);
            }
        }


    }

    private static String getTime(int time) {
        int hours = time / 3600 % 24;
        int minutes = time / 60 % 60;
        int seconds = time % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
