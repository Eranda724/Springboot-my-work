package com.example.Book.repo;

import com.example.Book.model.Users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);
}
