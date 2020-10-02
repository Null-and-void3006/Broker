package com.Tim401_6.Broker.controller;

import com.Tim401_6.Broker.model.User;
import com.Tim401_6.Broker.repository.UserRepository;
import com.Tim401_6.Broker.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @GetMapping(path="/user/{id}")
    public Optional<User> getUser(@PathVariable(value = "id") int userId) {
        return userRepository.findById(userId);
    }

    @GetMapping(path="/user/username/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username) {
        List<User> user = userRepository.findFirstByUsername(username);

        if(user.size() > 0) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @PutMapping(path="/register-user")
    public User registerUser(@RequestBody User user) {
        return registerUserService.registerUser(user);
    }
}
