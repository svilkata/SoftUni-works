package springModelDemo.task3.Dto;

import springModelDemo.task3.Employee;

import java.math.BigDecimal;

public class EmployeeSpringDto {
    private String firstName;
    private String lastName;
    private BigDecimal salary;
    private String managerLastName;

    public EmployeeSpringDto(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return firstName + " " +
                " " + lastName +
                " " + salary +
                " - " + "Manager: "
                 + (managerLastName == null ? "[no manager]" : managerLastName);
    }

    public void setManagerLastNameLength(int value) {

    }
}
