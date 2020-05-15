import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> deck1 = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> deck2 = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int initialSizeOfDeck = deck1.size();

        while (deck1.size() > 0 && deck2.size() > 0) {
            int i = 0;


                if (deck1.get(0).compareTo(deck2.get(0)) > 0) {
                    deck1.add(deck1.get(0));
                    deck1.add(deck2.get(0));
                    deck1.remove(0);
                    deck2.remove(0);
                } else if (deck1.get(i) < deck2.get(i)) {
                    deck2.add(deck2.get(0));
                    deck2.add(deck1.get(0));
                    deck1.remove(0);
                    deck2.remove(0);
                } else {
                    deck1.remove(0);
                    deck2.remove(0);
                }

        }

        int sum = 0;
        if (deck2.size() == 0) {
            for (Integer el : deck1) {
                sum += el;
            }
            System.out.printf("First player wins! Sum: %d", sum);
        } else if (deck1.isEmpty()) {
            for (Integer el : deck2) {
                sum += el;
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        }



    }
}
