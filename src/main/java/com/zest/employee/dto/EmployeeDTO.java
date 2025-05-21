package com.zest.employee.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private int id;
	private String name;
	private String email;
	private String department;
	private double salary;
	private String position;
	private Date dateOfJoining;
	private String password;
	private String role;
}
