package entities;

import java.math.BigDecimal;

public class CustomEmployee {
    public Department department;
    public BigDecimal salary;

    public CustomEmployee() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
