import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }
        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = reader.readLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(set);
        }
        List<int[]> chosenSets = chooseSets(sets, universe);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sets to take (%d):%n", chosenSets.size()));
        for (int[] set : chosenSets) {
            sb.append("{ ");
            sb.append(Arrays.toString(set).replaceAll("\\[|]", ""));
            sb.append(" }").append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> selectedSets = new ArrayList<>();
        List<Integer> universeSet = Arrays.stream(universe).boxed().collect(Collectors.toList());

        while (!universeSet.isEmpty()) {

            //finds the one set with most elements contained in the universe - eah step finds the most elements, and this is greedy
            int notChosenCount = 0;
            int[] chosenSet = sets.get(0);
            for (int[] set : sets) {
                int count = 0;
                for (int el : set) {
                    if (universeSet.contains(el)) {
                        count++;
                    }
                }

                if (notChosenCount < count) {
                    notChosenCount = count;
                    chosenSet = set;
                }
            }

            selectedSets.add(chosenSet);
            for (int i = 0; i < chosenSet.length; i++) {
                if (universeSet.contains(chosenSet[i])) {
                    universeSet.remove(Integer.valueOf(chosenSet[i]));
                }
            }
//            removeElementsOfChosenSetFromUniverseSet(chosenSet, universeSet);

            //TO DO
        }

        return selectedSets;
    }
}
