package example2.impl;

import example2.Student;
import example2.interfaces.StudentDatabase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabaseImpl implements StudentDatabase {
    private static int CURRENT_ID = 1;
    private static String FILE_NAME = "database.txt";

    @Override
    public Student persist(Student student) {
        student.setFacultyNumber(CURRENT_ID++);
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            fileWriter.write(student.toString() + '\n');
            return student;
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public Student getStudentByFacultyNumber(int facultyNumber) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(FILE_NAME));
            List<Student> students = new ArrayList<>();

            String line = fileReader.readLine();

            do {
                students.add(getStudentFromStr(line));
                line = fileReader.readLine();
            } while(line != null);

            return students.stream()
                    .filter(student -> student.getFacultyNumber() == facultyNumber)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Student getStudentFromStr(String line) {
        String[] tokens = line.split(",");
        Student student = new Student();
        student.setFacultyNumber(Integer.parseInt(tokens[0]));
        student.setName(tokens[1]);
        student.setAge(Integer.parseInt(tokens[2]));
        return student;
    }
}
