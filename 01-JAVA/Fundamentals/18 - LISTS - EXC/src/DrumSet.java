import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DrumSet {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double savings = Double.parseDouble(sc.nextLine());
        List<Integer> drumSet = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        //List<Integer> initialStateDrumSet = List.copyOf(drumSet);
        List<Integer> initialStateDrumSet = new ArrayList<>();
        for (int i = 0; i < drumSet.size(); i++) {
            initialStateDrumSet.add(drumSet.get(i));
        }


        String input = sc.nextLine();

        while (!input.equals("Hit it again, Gabsy!")) {
            int currPowerApplied = Integer.parseInt(input);
            for (int i = 0; i < drumSet.size(); i++) {
                int beforeApplying = drumSet.get(i);
                drumSet.set(i, beforeApplying - currPowerApplied);

                if (drumSet.get(i) <= 0) { //барабана е счупен
                    int initialQtity = initialStateDrumSet.get(i);
                    if ((savings - (initialQtity * 3.0)) >= 0.0) {//купуваме нов барабан
                        savings -= initialQtity * 3.0;
                        drumSet.set(i, initialQtity);
                    } else {
                        drumSet.remove(i);
                        initialStateDrumSet.remove(i);
                        i--;
                    }
                }
            }

            input = sc.nextLine();
        }

        printDrumSet(drumSet);
        System.out.printf("Gabsy has %.2flv.", savings);

    }

    private static void printDrumSet(List<Integer> drumSet) {
        for (Integer el : drumSet) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
}
