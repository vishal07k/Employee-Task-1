package com.zest.employee.repository;

import com.zest.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String username);
    Optional<Employee> findByEmail(String email);

}
