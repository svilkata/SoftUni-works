package example2;

import example2.interfaces.StudentDatabase;
import example2.interfaces.StudentParser;

public class StudentSystem {
    private StudentParser studentParser;
    private StudentDatabase studentDatabase;

    public StudentSystem(StudentParser studentParser, StudentDatabase studentDatabase) {
        this.studentParser = studentParser;
        this.studentDatabase = studentDatabase;
    }

    public String createStudent(String studentInfo) {
        Student student = studentParser.parseStudent(studentInfo);
        student = studentDatabase.persist(student);
        return studentParser.formatStudent(student);
    }

    public String getStudent(int facultyNumber) {
        Student student = studentDatabase.getStudentByFacultyNumber(facultyNumber);
        return studentParser.formatStudent(student);
    }
}
