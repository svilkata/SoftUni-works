package com.example.nlt.repositories;

import com.example.nlt.models.Employee;
import com.example.nlt.models.dto.ExportEmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByAgeGreaterThanOrderByProjectNameAsc(int i);
}
