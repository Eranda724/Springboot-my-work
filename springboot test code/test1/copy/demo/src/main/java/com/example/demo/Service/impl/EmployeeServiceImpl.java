package com.example.demo.Service.impl;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Repo.EmployeeRepo;
import com.example.demo.response.LoginResponse;
import com.example.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(employee);
        return "User Registered Successfully!";
    }

    @Override
    public String registerEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword()));
        employeeRepository.save(employee);
        return "User Registered Successfully!";
    }

    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(loginDTO.getEmail());

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (passwordEncoder.matches(loginDTO.getPassword(), employee.getPassword())) {
                return new LoginResponse("Login Success", true);
            }
            return new LoginResponse("Password Not Match", false);
        }
        return new LoginResponse("Email Not Exists", false);
    }

}