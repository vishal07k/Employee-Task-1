package com.zest.employee.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zest.employee.model.Employee;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeRepository {

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	void findByName() {
		// Test data
		Employee employee = new Employee();
		employee.setName("John Doe");

		when(employeeRepository.findByName("John Doe")).thenReturn(Optional.of(employee));

		// When
		Optional<Employee> actualEmployee = employeeRepository.findByName("John Doe");

		// Then
		assertTrue(actualEmployee.isPresent());
		assertEquals("John Doe", actualEmployee.get().getName());
	}

	@Test
	void findByEmail() {

		Employee employee = new Employee();
		employee.setEmail("john@examplegmail.com");
		when(employeeRepository.findByEmail("john@examplegmail.com")).thenReturn(Optional.of(employee));

		// when
		Optional<Employee> actualResult = employeeRepository.findByEmail("john@examplegmail.com");

		// then
		assertTrue(actualResult.isPresent());
		assertEquals("john@examplegmail.com", actualResult.get().getEmail());

	}

}
