package com.example.demo.controller;

import com.example.demo.Dto.AuthRequestDTO;
import com.example.demo.Dto.AuthResponseDTO;
import com.example.demo.Service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody AuthRequestDTO authRequest) {
        logger.info("Login attempt for user: {}", authRequest.getUsername());
        return authService.login(authRequest);
    }
}
