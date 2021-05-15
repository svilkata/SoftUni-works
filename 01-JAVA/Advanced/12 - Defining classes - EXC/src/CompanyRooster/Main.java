package CompanyRooster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");

            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String departmentName = input[3];

            Department department = null;
            if (departments.size() == 0) {
                department = new Department(departmentName);
                departments.add(department);
            } else {
                boolean isFound = false;
                for (Department currDepartment : departments) {
                    if (currDepartment.getName().equals(departmentName)) {
                        department = currDepartment;
                        isFound = true;
                        break;
                    }
                }

                if (!isFound) {
                    department = new Department(departmentName);
                    departments.add(department);
                }
            }


            Employee employee = null;

            switch (input.length) {
                case 4:
                    employee = new Employee(name, salary, position, department);
                    break;
                case 5:
                    try {
                        int age = Integer.parseInt(input[4]);
                        employee = new Employee(name, salary, position, department, age);
                    } catch (NumberFormatException e) {
                        String mail = input[4];
                        employee = new Employee(name, salary, position, department, mail);
                    }
                    break;

                default:
                    String mail = input[4];
                    int age = Integer.parseInt(input[5]);
                    employee = new Employee(name, salary, position, department, mail, age);
                    break;
            }

            for (int j = 0; j < departments.size(); j++) {
                if (employee.getDepartment().getName().equals(departments.get(j).getName())) {
                    List<Employee> employees = departments.get(j).getEmployees();
                    employees.add(employee);

                    departments.get(j).setEmployees(employees);
                }
            }
        }

        //highest salary department
        double highestAvgSalary = 0.0;
        Department highestPaidDepartment = null;
        for (Department department : departments) {
            double avgSalary = department.getEmployees().stream()
                    .mapToDouble(e-> e.getSalary())
                    .average()
                    .orElse(0);

            if (avgSalary > highestAvgSalary) {
                highestAvgSalary = avgSalary;
                highestPaidDepartment = department;
            }
        }

        System.out.printf("Highest Average Salary: %s%n", highestPaidDepartment.getName());
        highestPaidDepartment.getEmployees().stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .forEach(empl -> System.out.printf("%s %.2f %s %d%n", empl.getName(),
                        empl.getSalary(), empl.getEmail(), empl.getAge()));
    }
}
