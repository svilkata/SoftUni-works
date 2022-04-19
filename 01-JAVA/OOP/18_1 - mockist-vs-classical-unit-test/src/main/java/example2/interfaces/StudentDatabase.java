package example2.interfaces;

import example2.Student;

import java.util.List;

public interface StudentDatabase {
    Student persist(Student student);
    Student getStudentByFacultyNumber(int facultyNumber);
}
