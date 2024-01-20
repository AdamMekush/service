package com.example.service.controller;

import com.example.service.entity.Privilege;
import com.example.service.entity.Role;
import com.example.service.repository.PrivilegeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/privileges")
public class PrivilegeController {

    @Autowired
    private final PrivilegeRepository privilegeRepository;

    public PrivilegeController(PrivilegeRepository roleRepository) {
        this.privilegeRepository = roleRepository;
    }

    @GetMapping
    public List<Privilege> getAllPrivileges(){
        return privilegeRepository.findAll();
    }

    @PostMapping
    public Privilege createPrivilege(@RequestParam String name){
        Privilege privilege = new Privilege();
        privilege.setName(name);
        return privilegeRepository.save(privilege);
    }

    @GetMapping("/{id}")
    public Privilege getPrivilegeById(@PathVariable Long id){
        return privilegeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @DeleteMapping("/{id}")
    public String deletePrivilegeById(@PathVariable Long id){
        Privilege privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        privilegeRepository.delete(privilege);
        return "Deleted!";
    }

}
