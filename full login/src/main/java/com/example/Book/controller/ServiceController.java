package com.example.Book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book.model.Service;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ServiceController {

    private List<Service> Service = new ArrayList<>(
            List.of(
                    new Service("Navin", 60),
                    new Service("Kiran", 65)));

    @GetMapping("/students")
    public List<Service> getStudents() {
        return Service;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }

    @PostMapping("/students")
    public Service addStudent(@RequestBody Service student) {
        Service.add(student);
        return student;
    }

}
