package com.example.service.controller;

import com.example.service.entity.Role;
import com.example.service.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/roles")
public class RoleController {

    @Autowired
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @PostMapping
    public Role createRole(@RequestParam String name){
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @DeleteMapping("/{id}")
    public String deleteRoleById(@PathVariable Long id){
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        roleRepository.delete(role);
        return "Deleted!";
    }

}
