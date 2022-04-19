package example2.impl;

import example2.Student;
import example2.interfaces.StudentParser;

public class StudentParserImpl implements StudentParser {
    @Override
    public Student parseStudent(String studentInfo) {
        String[] tokens = studentInfo.split(",");

        Student student = new Student();
        student.setName(tokens[0]);
        student.setAge(Integer.parseInt(tokens[1]));

        return student;
    }

    @Override
    public String formatStudent(Student student) {
        return String.format("%d,%s,%d", student.getFacultyNumber(), student.getName(), student.getAge());
    }
}
