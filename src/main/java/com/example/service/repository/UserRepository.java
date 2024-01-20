package com.example.service.repository;

import com.example.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);
    public User findUserByUsernameOrEmail(String username, String email);
}