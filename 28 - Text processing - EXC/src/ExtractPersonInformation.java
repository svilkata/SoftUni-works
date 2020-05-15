import java.util.ArrayList;
import java.util.Scanner;

public class ExtractPersonInformation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();

            ArrayList<String> names = findAllNames(input);
            ArrayList<String> ages = findAllAges(input);

            int minSize = Math.min(names.size(), ages.size());
            for (int j = 0; j < minSize; j++) {
                System.out.println(String.format("%s is %s years old.", names.get(j), ages.get(j)));
            }


        }
    }

    private static ArrayList<String> findAllAges(String input) {
        ArrayList<String> ages = new ArrayList<>();

        while (input.contains("#")) {
            int startIndexName = input.indexOf('#');
            int endIndexName = input.indexOf('*');
            String currNumberAsString = input.substring(startIndexName + 1, endIndexName);
            ages.add(currNumberAsString);

            input = input.substring(endIndexName + 1);
        }

        if (ages.isEmpty()) {
            return null;
        }

        return ages;
    }


    private static ArrayList<String> findAllNames(String input) {

        ArrayList<String> names = new ArrayList<>();

        while (input.contains("@")) {
            int startIndexName = input.indexOf('@');
            int endIndexName = input.indexOf('|');
            String currName = input.substring(startIndexName + 1, endIndexName);
            names.add(currName);

            input = input.substring(endIndexName + 1);
        }

        if (names.isEmpty()) {
            return null;
        }

        return names;


    }
}
