import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, ArrayList<String>> courses = new LinkedHashMap<>();
        String input = sc.nextLine();

        while (!"end".equals(input)) {
            String[] tokens = input.split(" : ");
            String course = tokens[0];
            String student = tokens[1];

            courses.putIfAbsent(course, new ArrayList<>());
            courses.get(course).add(student);

            input = sc.nextLine();
        }

        courses
                .entrySet()
                .stream()
                .sorted((c1, c2) -> {
                    int first = c1.getValue().size();
                    int second = c2.getValue().size();
                    return Integer.compare(second, first);
                })
                .forEach(c -> {
                    System.out.println(String.format("%s: %d",
                            c.getKey(),
                            c.getValue().size()));

                    c.getValue()
                            .stream()
                            .sorted((s1, s2) -> s1.compareTo(s2))
                            .forEach(s -> System.out.println(String.format("-- %s", s)));
                });
    }
}
