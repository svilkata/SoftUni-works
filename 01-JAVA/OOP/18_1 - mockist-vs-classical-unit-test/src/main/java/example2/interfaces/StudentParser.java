package example2.interfaces;

import example2.Student;

public interface StudentParser {
    Student parseStudent(String studentInfo);
    String formatStudent(Student student);
}
