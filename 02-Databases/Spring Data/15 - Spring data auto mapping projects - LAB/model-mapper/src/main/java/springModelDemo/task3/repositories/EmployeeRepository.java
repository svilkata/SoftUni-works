package springModelDemo.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springModelDemo.task3.Employee;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    List<Employee> findByBirthdayBeforeOrderBySalaryDesc(LocalDate year);
}
