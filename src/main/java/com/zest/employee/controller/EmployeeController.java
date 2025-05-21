package com.zest.employee.controller;

import com.zest.employee.dto.EmployeeDTO;
import com.zest.employee.model.Employee;
import com.zest.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // Read All
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO updatedEmployee) {
        EmployeeDTO employee = employeeService.updateEmployee(id, updatedEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee with ID " + id + " deleted successfully.", HttpStatus.OK);
    }

    // Home Test Endpoint
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to Employee Management System", HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Employee>> getPaginatedEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<Employee> employeePage = employeeService.getAllEmployees(page, size, sortBy, sortDir);
        return new ResponseEntity<>(employeePage, HttpStatus.OK);
    }

}
