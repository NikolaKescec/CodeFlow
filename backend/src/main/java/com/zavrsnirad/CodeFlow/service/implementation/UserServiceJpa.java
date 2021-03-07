package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.repository.UserRepository;
import com.zavrsnirad.CodeFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJpa implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new IllegalArgumentException("This username does not exist!");
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public void removeUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new IllegalArgumentException("This username does not exist!");
        userRepository.delete(user);
    }

    @Override
    public User updateUser(User updatedUser) {
        return null;
    }

}
