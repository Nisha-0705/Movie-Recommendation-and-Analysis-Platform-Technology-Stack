package com.Nisha.service;

import com.Nisha.model.LoginRequest;
import com.Nisha.model.LoginResponse;
import com.Nisha.security.JwtUtil;
import com.Nisha.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.Nisha.repository.UserRepository;

@Service
public class UserService {
    @Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private UserRepository userRepository;

@Autowired
private JwtUtil jwtUtil;

public User register(User user) {
     if(userRepository.findByEmail(user.getEmail()).isPresent()){
        throw new RuntimeException("Email already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    
    return userRepository.save(user);
}
   public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid Password");
    }

    String token = jwtUtil.generateToken(user.getEmail());

    return new LoginResponse(token);
}
}
