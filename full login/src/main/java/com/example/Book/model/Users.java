package com.example.Book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Explicitly set table name to avoid conflicts
public class Users {

    @Id @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false) // Added nullable=false to ensure password is required
    private String password;

    @Column(nullable = false)
    private String confirmpassword;

    // Default constructor required by JPA
    public Users() {
    }

    // Constructor with fields for easier object creation
    public Users(String username, String email, String password, String confirmpassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setconfirmpassword(String confirmpassword) {
        this.confirmpassword = password;
    }



    @Override
    public String toString() {
        return "Users{" +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", confirmpassword='[PROTECTED]'" +
                '}';
    }
}