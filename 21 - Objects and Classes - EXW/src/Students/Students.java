package Students;

public class Students {
    private String firstName;
    private String secondName;
    private String grade;

    public Students(String firstName, String secondName, String grade) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        String result = String.format("%s %s: %s", this.firstName, this.secondName, this.grade);
        return result;
    }
}
