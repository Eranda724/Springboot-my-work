package com.example.demo.Service.impl;

import com.example.demo.config.JwtUtils;
import com.example.demo.Dto.AuthRequestDTO;
import com.example.demo.Dto.AuthResponseDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Repo.EmployeeRepo;
import com.example.demo.Service.AuthService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepo employeeRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(EmployeeRepo employeeRepo, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequest) {
        Employee employee = employeeRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(authRequest.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtils.generateToken(employee.getEmail());
        return new AuthResponseDTO(token);
    }
}