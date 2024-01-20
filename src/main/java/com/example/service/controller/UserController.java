package com.example.service.controller;

import com.example.service.entity.Role;
import com.example.service.entity.User;
import com.example.service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {
    @Autowired
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
         return userRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException());
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        userRepository.delete(user);
        return "Deleted!";
    }

    @GetMapping("/{id}/roles")
    public Set<Role> getUserRolesById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return user.getRoles();
    }

}
