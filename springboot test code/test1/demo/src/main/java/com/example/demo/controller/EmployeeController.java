package com.example.demo.controller;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Service.EmployeeService;
import com.example.demo.payload.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Adjust for your frontend URL
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            String response = employeeService.registerEmployee(employeeDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registration failed", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO) {
        try {
            LoginResponse response = employeeService.loginEmployee(loginDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed", e);
        }
    }
}