package com.example.demo.Controller;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Service.EmployeeService;
import com.example.demo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Or your frontend URL
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')") // Example of an annotation that could cause 403
    public ResponseEntity<String> registerEmployee(@RequestBody Employee employee) {
        // Business logic
        return ResponseEntity.ok("Employee registered");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}