package com.zest.employee.service;

import com.zest.employee.dto.EmployeeDTO;
import com.zest.employee.exception.UserAlreadyExist;
import com.zest.employee.exception.UserNotFound;
import com.zest.employee.mapper.EmployeeMapper;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public EmployeeDTO addEmployee(EmployeeDTO employee){

        Optional<Employee> user = employeeRepository.findByEmail(employee.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyExist();
        }
        
        Employee saved = employeeRepository.save(employeeMapper.toEntity(employee));

        return employeeMapper.toDTO(saved);

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read by ID
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFound());
    }

    // Update

    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO updatedEmployee) {
        Employee existingEmployee = getEmployeeById(id);

        
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setRole(updatedEmployee.getRole());
        existingEmployee.setDateOfJoining(updatedEmployee.getDateOfJoining());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setSalary(updatedEmployee.getSalary());
       

        return employeeMapper.toDTO(employeeRepository.save(existingEmployee));
    }

    // Delete
    public void deleteEmployee(Integer id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    public Page<Employee> getAllEmployees(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

}
