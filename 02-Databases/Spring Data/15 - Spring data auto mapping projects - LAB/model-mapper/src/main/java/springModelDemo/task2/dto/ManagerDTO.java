package springModelDemo.task2.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> subordinates;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setSubordinates(Set<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        String stringOfSubordinates = this.subordinates
                .stream()
                .map(EmployeeDTO::toString)
                .map(s -> "    - " + s)
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format("%s %s | Employees: %d%n%s",
                this.firstName, this.lastName,
                this.subordinates.size(),
                stringOfSubordinates);
    }
}
