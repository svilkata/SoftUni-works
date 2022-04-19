package example2;

import example2.interfaces.StudentDatabase;
import example2.interfaces.StudentParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentSystemTest {
//    @Mock
    private StudentParser parser = mock(StudentParser.class);

    @Mock
    private StudentDatabase database;

    @InjectMocks
    private StudentSystem studentSystem;

    @Test
    public void createStudent_sampleInput_expectedResult() {
        String input = "Pesho,18";
        String expectedResult = "1,Pesho,18";
        Student student = new Student("Pesho", 18);
        Student withId = new Student(1, "Pesho", 18);

        when(parser.parseStudent(input)).thenReturn(student);
        when(database.persist(student)).thenReturn(withId);
        when(parser.formatStudent(withId)).thenReturn(expectedResult);

        String result = studentSystem.createStudent(input);

        assertEquals(expectedResult, result);
    }

    @Test
    public void getStudentByFacultyNumberTest() {
        int facultyNumber = 10;
        Student student = new Student(10, "Georgi", 23);
        String formattedStudent = "10,Georgi,23";
        when(database.getStudentByFacultyNumber(facultyNumber)).thenReturn(student);
        when(parser.formatStudent(student)).thenReturn(formattedStudent);

        String result = studentSystem.getStudent(facultyNumber);

        assertEquals("10,Georgi,23", result);
    }
}
