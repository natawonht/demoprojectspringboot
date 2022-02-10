package com.ais.demoproject.service;

import java.util.List;

import com.ais.demoproject.model.User;
import com.ais.demoproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String hello() {
        return "Hello World!";
    }

    public String sayHello(String userName) {
        return String.format("Hello, %s!", userName);
    }

    public User addUser(User userData) {
        return userRepository.save(userData);
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getUserById(String userName) {
        return userRepository.findUserByuserName(userName);
    }

}




