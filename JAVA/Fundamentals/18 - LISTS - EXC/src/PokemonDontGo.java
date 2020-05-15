import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PokemonDontGo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> sequence = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int sumRemovedElements = 0;

        while (!sequence.isEmpty()) {
            int index = Integer.parseInt(sc.nextLine());

            if (index < 0) {
                index = 0;
                sumRemovedElements += pokemonDontLeft(sequence, index);
            } else if (index > sequence.size()-1) {
                index = sequence.size()-1;
                sumRemovedElements += pokemonDontRight(sequence, index);
            } else {
                sumRemovedElements+= pokemonDont(sequence, index);
            }



        }

        System.out.println(sumRemovedElements);
    }

    private static int pokemonDontRight(List<Integer> sequence, int index) {
        int valueRemovedElement = sequence.get(index);
        sequence.remove(index);

        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) <= valueRemovedElement) {
                sequence.set(i, sequence.get(i) + valueRemovedElement);
            } else {
                sequence.set(i, sequence.get(i) - valueRemovedElement);
            }
        }

        sequence.add(index, sequence.get(0));

        return valueRemovedElement;
    }

    private static int pokemonDontLeft(List<Integer> sequence, int index) {
        int valueRemovedElement = sequence.get(index);
        sequence.remove(index);

        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) <= valueRemovedElement) {
                sequence.set(i, sequence.get(i) + valueRemovedElement);
            } else {
                sequence.set(i, sequence.get(i) - valueRemovedElement);
            }
        }

        sequence.add(index, sequence.get(sequence.size()-1));

        return valueRemovedElement;
    }

    private static int pokemonDont(List<Integer> sequence, int index) {
        int valueRemovedElement = sequence.get(index);
        sequence.remove(index);

        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i) <= valueRemovedElement) {
                sequence.set(i, sequence.get(i) + valueRemovedElement);
            } else {
                sequence.set(i, sequence.get(i) - valueRemovedElement);
            }
        }
        return valueRemovedElement;
    }
}
