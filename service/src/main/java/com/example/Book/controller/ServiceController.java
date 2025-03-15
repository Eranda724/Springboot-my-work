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
                    new Service("Navin", "Service1", true),
                    new Service("Kiran", "Service2", true)));

    @GetMapping("/service")
    public List<Service> getStudents() {
        return Service;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }

    @PostMapping("/service")
    public Service addStudent(@RequestBody Service service) {
        Service.add(service);
        return service;
    }

}
