package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> getUsersTaskers();

    List<User> getUsersSolvers();

    List<User> getUsers();

    User addUser(UserDtoReq user);

    void removeUser(String username);

    User updateUser(User updatedUser);

}
