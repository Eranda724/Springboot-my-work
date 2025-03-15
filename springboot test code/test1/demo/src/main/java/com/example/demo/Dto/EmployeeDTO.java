package com.example.demo.Dto;

public class EmployeeDTO {
    private String name;
    private String email;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { // <-- Ensure this method exists
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
