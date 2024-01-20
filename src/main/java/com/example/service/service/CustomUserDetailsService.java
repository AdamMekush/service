package com.example.service.service;

import com.example.service.entity.User;
import com.example.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameOrEmail(username, username);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(
                    username,
                    user.getPassword(),
                    user.getAuthorities()
            );
        } else {
            throw  new UsernameNotFoundException("User not found!");
        }
    }
}
