package com.zest.employee.service;

import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Employee emp = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Provide role with prefix "ROLE_"
        return new User(emp.getEmail(), emp.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(emp.getRole())));
    }
}
