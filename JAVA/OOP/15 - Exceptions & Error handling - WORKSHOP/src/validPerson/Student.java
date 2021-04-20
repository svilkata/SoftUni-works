package validPerson;

public class Student {
    private String name;
    private String email;

    public Student(String name, String email) throws InvalidPersonNameException {
        this.setName(name);
        this.setEMAIL(email);
    }

    private void setEMAIL(String email) {
        this.email = email;
    }

    private void setName(String name) throws InvalidPersonNameException {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isWhitespace(name.charAt(i)) || Character.isDigit(name.charAt(i))) {
                throw new InvalidPersonNameException("Special or numeric character is part of the name");
            }
        }

        this.name = name;
    }
}
