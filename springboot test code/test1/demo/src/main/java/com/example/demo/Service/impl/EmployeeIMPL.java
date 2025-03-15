package com.example.demo.Service.impl;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Repo.EmployeeRepo;
import com.example.demo.payload.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepo.save(employee);
        return "User Registered Successfully!";
    }

    @Override
    public String registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepo.save(employee);
        return "User Registered Successfully!";
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        // Use the corrected method name
        Optional<Employee> employeeOpt = employeeRepo.findByEmail(loginDTO.getEmail());

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (passwordEncoder.matches(password, employee.getPassword())) {
                return new LoginResponse("Login Success", true);
            }
            return new LoginResponse("Password Not Match", false);
        }
        return new LoginResponse("Email Not Exists", false);
    }
}