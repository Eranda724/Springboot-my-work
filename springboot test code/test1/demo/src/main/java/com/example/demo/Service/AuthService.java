package com.example.demo.Service;

import com.example.demo.Dto.AuthRequestDTO;
import com.example.demo.Dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO authRequest);
}