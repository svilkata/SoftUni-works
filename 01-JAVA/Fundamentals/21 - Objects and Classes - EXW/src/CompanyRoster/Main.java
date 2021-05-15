package CompanyRoster;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] token = sc.nextLine().split(" ");

            Employee personToAdd = new Employee(token[0], Double.parseDouble(token[1]), token[2], token[3]);
            if (token.length == 5) {
                if (token[4].contains("@")) {
                    personToAdd.setEmail(token[4]);
                } else {
                    personToAdd.setAge(Integer.parseInt(token[4]));
                }
            } else if (token.length == 6) { //length == 6
                personToAdd.setEmail(token[4]);
                personToAdd.setAge(Integer.parseInt(token[5]));
            }

            employeeList.add(personToAdd);
        }

        //отсяване на видовете Departments
        List<String> howManyDepartments = new ArrayList<>();
        for (Employee employee : employeeList) {
            String currDepartment = employee.getDepartment();
            if (!howManyDepartments.contains(currDepartment)) {
                howManyDepartments.add(currDepartment);
            }
        }

        //разпределяне на всеки един служител по отдел и успоредно с това коя среда заплата е най-голяма
        double maxAvrSalary = Double.MIN_VALUE;
        int indexMaxAvrSalaryDepartment = -1;
        List<Department> departmentList = new ArrayList<>();
        for (int i = 0; i < howManyDepartments.size(); i++) {
            List<Employee> currentDepartmentList = new ArrayList<>();
            double curMaxAverageSalary = 0.0;

            for (int j = 0; j < employeeList.size(); j++) {
                if (howManyDepartments.get(i).equals(employeeList.get(j).getDepartment())) {
                    currentDepartmentList.add(employeeList.get(j));
                    curMaxAverageSalary += employeeList.get(j).getSalary();
                }
            }

            curMaxAverageSalary /= currentDepartmentList.size();
            if (curMaxAverageSalary > maxAvrSalary) {
                maxAvrSalary = curMaxAverageSalary;
                indexMaxAvrSalaryDepartment = i;
            }

            Department newDepartment = new Department(howManyDepartments.get(i), currentDepartmentList);
            departmentList.add(newDepartment);
        }

//        maxAvrSalary = Double.MIN_VALUE;
//        indexMaxAvrSalaryDepartment = -1;
//        for (int i = 0; i < departmentList.size(); i++) {
//            double curMaxAverageSalary = 0.0;
//            for (int j = 0; j < departmentList.get(i).getMembers().size(); j++) {
//                curMaxAverageSalary += departmentList.get(i).getMembers().get(j).getSalary();
//            }
//            curMaxAverageSalary /= departmentList.get(i).getMembers().size();
//
//            if (curMaxAverageSalary > maxAvrSalary) {
//                maxAvrSalary = curMaxAverageSalary;
//                indexMaxAvrSalaryDepartment = i;
//            }
//        }

        System.out.println("Highest Average Salary: " + departmentList.get(indexMaxAvrSalaryDepartment).getDepartmentType());


        departmentList.get(indexMaxAvrSalaryDepartment).getMembers()
                .stream()
                .sorted((p1, p2) -> Double.compare(p2.getSalary(), p1.getSalary())) //сортира възходящо
                .forEach(p -> System.out.println(p));


    }
}
