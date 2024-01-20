package com.example.service.controller;

import com.example.service.entity.User;
import com.example.service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class RegisterController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/register").setViewName("register");
    }

    @Autowired
    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        else{
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

            userRepository.save(user);

            return "redirect:/login";
        }
    }
}
