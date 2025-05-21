package com.zest.employee.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zest.employee.dto.EmployeeDTO;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;

@Component
public class EmployeeMapper {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Employee toEntity(EmployeeDTO dto) {
		return Employee.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.dateOfJoining(dto.getDateOfJoining())
				.position(dto.getPosition())
				.salary(dto.getSalary())
				.department(dto.getDepartment())
				.password(passwordEncoder.encode(dto.getPassword()))
				.role(dto.getRole())
				.build();
	}
	
	public EmployeeDTO toDTO(Employee employee) {
		return EmployeeDTO.builder()
				.name(employee.getName())
				.email(employee.getEmail())
				.dateOfJoining(employee.getDateOfJoining())
				.position(employee.getPosition())
				.salary(employee.getSalary())
				.department(employee.getDepartment())
				.password(employee.getPassword())
				.role(employee.getRole())
				.build();
	}
}
