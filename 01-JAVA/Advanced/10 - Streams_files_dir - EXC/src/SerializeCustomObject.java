import java.io.*;

public class SerializeCustomObject {
    public static void main(String[] args) throws IOException {

        Course course = new Course("Java Advanced", 146);

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream("src/resources/course.ser"));

        objectOutputStream.writeObject(course);


    }

    public static class Course implements Serializable {
        private String name;
        private Integer students;

        public Course(String name, Integer students) {
            this.name = name;
            this.students = students;
        }
    }
}
