package Students;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Students> studentsList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split(" ");
            Students newStudend = new Students(tokens[0], tokens[1], tokens[2]);
            studentsList.add(newStudend);
        }

//        Collections.sort(studentsList, studentsList.get());

        studentsList
                .stream()
                .sorted((p1, p2) -> p2.getGrade().compareTo(p1.getGrade()))
                .forEach(p -> System.out.println(p.toString()));

    }
}
