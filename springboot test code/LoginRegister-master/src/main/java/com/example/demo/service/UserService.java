package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	
	public User saveUser(User u) {
		return ur.save(u);
	}
	
	public User finduser(String email) {
		return ur.findById(email).orElse(null);
	}
}
