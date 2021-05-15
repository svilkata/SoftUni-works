import java.util.*;
import java.util.stream.Collectors;

public class AverageStudentsGrades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, List<Double>> studentsWithGrades = new TreeMap<>();

        while (n-- > 0){
            String[] tokens = sc.nextLine().split("\\s+");

            String name = tokens[0];
            double grade = Double.parseDouble(tokens[1]);

            studentsWithGrades.putIfAbsent(name, new ArrayList<>());
            studentsWithGrades.get(name).add(grade);
        }

        studentsWithGrades.entrySet().stream()
//                .sorted((f, s) -> getAvrgGrade(s.getValue()).compareTo(getAvrgGrade(f.getValue())))
//                .sorted(Comparator.comparingDouble(entry -> getAvrgGrade(entry.getValue())))
                .forEach(entry -> {
                    System.out.println(String.format("%s -> %s (avg: %.2f)", entry.getKey(),
                            getGradesAsString(entry.getValue()),
                            getAvrgGrade(entry.getValue())));
                });

        /*for (Map.Entry<String, List<Double>> entry : studentsWithGrades.entrySet()) {
            System.out.println(String.format("%s -> %s (avg: %.2f)", entry.getKey(),
                    getGradesAsString(entry.getValue()),
                    getAvrgGrade(entry.getValue())));
        }*/
    }

    private static Double getAvrgGrade(List<Double> grades) {
        return grades.stream()
                .mapToDouble(g-> g)
                .average()
                .orElse(0.00);
    }

    private static String getGradesAsString(List<Double> value) {
        return value.stream()
                .map(grade -> String.format("%.2f", grade))
                .collect(Collectors.joining(" "));
    }
}
