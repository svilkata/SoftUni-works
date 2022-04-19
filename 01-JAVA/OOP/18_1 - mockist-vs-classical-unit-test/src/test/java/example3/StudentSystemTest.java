package example3;

import example2.StudentSystem;
import example2.impl.StudentParserImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentSystemTest {
    @Test
    public void createStudentClassicalTest() {
        String input = "Ivan,33";
        String expectedOutput = "1,Ivan,33";
        StudentSystem studentSystem = new StudentSystem(new StudentParserImpl(), new FakeDatabase());

        String result = studentSystem.createStudent(input);

        assertEquals(expectedOutput, result);
    }
}
