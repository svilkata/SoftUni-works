package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Student> studentsList = new ArrayList<>();
        while (!input.equals("end")) {
            String[] studentParts = input.split(" ");
            int age = Integer.parseInt(studentParts[2]);

            Student student = new Student(studentParts[0], studentParts[1], age, studentParts[3]);
            studentsList.add(student);

            input = sc.nextLine();
        }

        String city = sc.nextLine();

        for (int i = 0; i < studentsList.size(); i++) {
            Student current = studentsList.get(i);

            if (current.getHometown().equals(city)) {
                current.getString();
            }
        }
    }

    private static void printStudent(Student current) {
        System.out.printf("%s %s is %d years old",
                current.getFirstName(),
                current.getLastName(),
                current.getAge());
        System.out.println();
    }
}
