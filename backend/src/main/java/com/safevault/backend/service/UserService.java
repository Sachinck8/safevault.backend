package com.safevault.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.safevault.backend.model.User;
import com.safevault.backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;

    }
     public void registerUser(String username, String password){
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        userRepository.save(user);
     }
}
