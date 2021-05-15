package CompanyRoster;

import java.util.List;

public class Department {
    private String departmentType;
    private List<Employee> members;

    public Department(String departmentType, List<Employee> members) {
        this.departmentType = departmentType;
        this.members = members;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    @Override
    public String toString() {
        String result = String.format("%s: %s", this.departmentType, this.members);
        return result;
    }
}
