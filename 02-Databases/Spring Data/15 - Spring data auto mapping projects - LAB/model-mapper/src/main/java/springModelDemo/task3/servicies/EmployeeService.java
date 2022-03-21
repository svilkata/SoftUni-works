package springModelDemo.task3.servicies;

import springModelDemo.task3.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save(Employee employee);

    List<Employee> findEmployeesBornBefore(int year);

    List<Employee> findAll();
}
