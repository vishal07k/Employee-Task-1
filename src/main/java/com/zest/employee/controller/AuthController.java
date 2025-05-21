package com.zest.employee.controller;

import com.zest.employee.dto.AuthRequest;
import com.zest.employee.dto.EmployeeDTO;
import com.zest.employee.model.Employee;
import com.zest.employee.security.JwtUtil;
import com.zest.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        String token = jwtUtil.generateToken(authRequest.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody EmployeeDTO userDto) {
        EmployeeDTO savedUser = null;
        try {
            savedUser = employeeService.addEmployee(userDto);
            return new ResponseEntity<>("User created Successfully !", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(""+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Welcome to Empoyee Management System", HttpStatus.OK);
    }
}
