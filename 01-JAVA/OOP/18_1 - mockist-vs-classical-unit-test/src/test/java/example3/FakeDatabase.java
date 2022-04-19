package example3;

import example2.Student;
import example2.interfaces.StudentDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase implements StudentDatabase {
    private static int CURRENT_ID = 1;
    private List<Student> students;

    public FakeDatabase() {
        students = new ArrayList<>();
    }

    @Override
    public Student persist(Student student) {
        student.setFacultyNumber(CURRENT_ID++);
        students.add(student);
        return student;
    }

    @Override
    public Student getStudentByFacultyNumber(int facultyNumber) {
        return students.stream()
                .filter(student -> facultyNumber == student.getFacultyNumber())
                .findFirst()
                .orElse(null);
    }
}
