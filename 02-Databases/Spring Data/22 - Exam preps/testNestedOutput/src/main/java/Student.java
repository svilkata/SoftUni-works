public class Student implements Person{
    private String studentNumber;
    private String firstName;
    private String lastName;

    public Student(String studentNumber, String firstName, String secondName) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = secondName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
}
