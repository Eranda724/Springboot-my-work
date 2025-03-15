package com.example.Book.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Book.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);


    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
