package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.repository.UserRepository;
import com.zavrsnirad.CodeFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    public List<User> getUsersTaskers() {
        return userRepository.findTop20ByOrderByTaskPointsDesc();
    }

    @Override
    public List<User> getUsersSolvers() {
        return userRepository.findTop20ByOrderBySolutionPointsDesc();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDtoReq user) {
        boolean byUsername, byEmail;

        // check for existing username
        byUsername = userRepository.findByUsername(user.getUsername()) != null;
        if(byUsername)
            throw new IllegalArgumentException("Username already taken.");

        // check for existing email
        byEmail = userRepository.findByEmail(user.getEmail()) != null;
        if(byEmail)
            throw new IllegalArgumentException("Email already taken.");

        // User cretion
        User newUser = new User(user.getUsername(), user.getEmail(), "USER");

        // Ekripcija lozinke.
        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(user.getPassword(), salt);
        newUser.setPassword(hashed);

        // saving user
        newUser = userRepository.save(newUser);

        return newUser;
    }

    @Override
    public void removeUser(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new IllegalArgumentException("This username does not exist!");
        userRepository.delete(user);
    }

    // TODO
    @Override
    public User updateUser(User updatedUser) {
        return null;
    }

}
