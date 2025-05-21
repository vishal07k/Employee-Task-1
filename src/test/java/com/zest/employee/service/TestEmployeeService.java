package com.zest.employee.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zest.employee.dto.EmployeeDTO;
import com.zest.employee.mapper.EmployeeMapper;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

	@Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeRequestDto;
    private EmployeeDTO employeeResponseDto;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1);
        employee.setEmail("test@example.com");
        employee.setPassword("Pass@123");

        employeeRequestDto = new EmployeeDTO();
        employeeRequestDto.setEmail("test3@example.com");
        employeeRequestDto.setPassword("password");

        employeeResponseDto = new EmployeeDTO();
    }

    @Test
    void AddEmployee() {
        when(employeeRepository.findByEmail(employeeRequestDto.getEmail())).thenReturn(Optional.empty());
        when(employeeMapper.toEntity(employeeRequestDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        

        EmployeeDTO result = employeeService.addEmployee(employeeRequestDto);
        verify(employeeRepository).save(employee);
    }


    @Test
    void GetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
       
        List<Employee> result = employeeService.getAllEmployees();
        assertFalse(result.isEmpty());
    }

    @Test
    void GetEmployeeById() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1);
        assertNotNull(result);
    }

    @Test
    void UpdateEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.toDTO(employee)).thenReturn(employeeResponseDto);

        EmployeeDTO result = employeeService.updateEmployee(1, employeeRequestDto);
        assertNotNull(result);
    }


    @Test
    void DeleteEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1);
        verify(employeeRepository).delete(employee);
    }
}
