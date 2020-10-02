package com.Tim401_6.Broker.services;

import com.Tim401_6.Broker.model.User;
import com.Tim401_6.Broker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        userRepository.save(user);
        return user;
    }
}
