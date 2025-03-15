package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDTO;
import com.example.demo.Dto.LoginDTO;
import com.example.demo.payload.response.LoginResponse;

public interface EmployeeService {
    String registerEmployee(EmployeeDTO employeeDTO);

    String addEmployee(EmployeeDTO employeeDTO);

    LoginResponse loginEmployee(LoginDTO loginDTO);
}
