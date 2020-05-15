import java.util.*;
import java.util.stream.Collectors;

public class SoftUniExamResults {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        Pesho-Java-91
//        Gosho-C#-84
//        Kiro-Java-90
//        Kiro-C#-50
//        Kiro-banned
//        exam finished
        TreeMap<String, Integer> listResultsStudent = new TreeMap<>();
        TreeMap<String, Integer> countSubmits = new TreeMap<>();
        String input = sc.nextLine();
        while (!"exam finished".equals(input)) {
            String[] tokens = input.split("-");

            if (!input.contains("banned")) {
                String name = tokens[0];
                String language = tokens[1];
                int points = Integer.parseInt(tokens[2]);

                //
                if (!listResultsStudent.containsKey(name)) {
                    listResultsStudent.put(name, points);
                } else { // ако вече има такъв студент
                    if (points > listResultsStudent.get(name)) {
                        listResultsStudent.put(name, points);
                    }
                }

                //
                if (!countSubmits.containsKey(language)) {
                    countSubmits.put(language, 1);
                } else {
                    countSubmits.put(language,countSubmits.get(language)+1);
                }
            } else { //случая когато е banned
                String name = tokens[0]; //когото ще изгонваме/banваме
                listResultsStudent.remove(name);
            }

            input = sc.nextLine();
        }

        System.out.println("Results:");
        listResultsStudent
                .entrySet()
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()))
                .forEach(k -> {
                    System.out.println(String.format("%s | %d", k.getKey(), k.getValue()));
                });

        System.out.println("Submissions:");
        countSubmits
                .entrySet()
                .stream()
                .sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()))
                .forEach(s -> System.out.println(String.format("%s - %d", s.getKey(), s.getValue())));

    }
}
