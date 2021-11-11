package com.cocobob.server.service;

import com.cocobob.server.domain.User;
import com.cocobob.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User insertUser(User user){
        return userRepository.save(user);
    }

    public boolean existEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
