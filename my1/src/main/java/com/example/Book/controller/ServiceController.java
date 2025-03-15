package com.example.Book.controller;

import com.example.Book.model.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceController {

    private List<Service> students = new ArrayList<>(
            List.of(
                    new Service(1, "Navin", 60),
                    new Service(2, "Kiran", 65)));

    @GetMapping("/students")
    public List<Service> getStudents() {
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }

    @PostMapping("/students")
    public Service addStudent(@RequestBody Service student) {
        students.add(student);
        return student;
    }

}
