package com.example.Book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Book.model.Users;
import com.example.Book.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        // Validate that password and confirm password match
        if (!user.getPassword().equals(user.getConfirmpassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        
        // Clear the confirm password field (optional, but recommended)
        user.setconfirmpassword(null);
        
        // Save the user to the database
        return repo.save(user);
    }
    public String verify(Users user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}
