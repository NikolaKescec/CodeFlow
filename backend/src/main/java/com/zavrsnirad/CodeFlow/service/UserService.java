package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> getUsers();

    User addUser(User user);

    void removeUser(String username);

    User updateUser(User updatedUser);

}
