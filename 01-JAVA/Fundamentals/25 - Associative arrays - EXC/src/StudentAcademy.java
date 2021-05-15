import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class StudentAcademy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, ArrayList<Double>> students = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            double grade = Double.parseDouble(sc.nextLine());

            students.putIfAbsent(name, new ArrayList<>());
            students.get(name).add(grade);
        }

        students
                .entrySet()
                .stream()
                .filter(s -> s.getValue().stream().mapToDouble(x -> Double.valueOf(x))
                        .average().getAsDouble() >= 4.50)
                .sorted((s1, s2) -> {
                            double first = s1.getValue().stream().mapToDouble(x -> Double.valueOf(x)).average().getAsDouble();
                            double second = s2.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble();
                            return Double.compare(second, first);
                        })
                .forEach(s-> System.out.println(String.format("%s -> %.2f",
                        s.getKey(),
                        s.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble())));

    }

}
