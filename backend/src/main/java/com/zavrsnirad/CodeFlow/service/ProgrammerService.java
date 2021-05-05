package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.UserUpdateDtoReq;

import java.util.List;

public interface ProgrammerService {

    Programmer findByUsername(String username);

    Programmer findById(Long id);

    List<Programmer> getProgrammersTaskers();

    List<Programmer> getProgrammersSolvers();

    List<Programmer> getUsers();

    Programmer addProgrammer(UserDtoReq user);

    Follower followingUser(Long programmerId, Programmer programmer);

    void removeProgrammer(String username);

    void updateProgrammer(UserUpdateDtoReq userUpdateDtoReq, Programmer programmer);

    Follower followUser(Long toFollowId, Programmer programmer);

    void acceptFollowerShip(Long notificationId, Long followerId, Programmer programmer);

    void denyFollower(Long notificationId, Long followerId, Programmer programmer);

    void unfollowUser(Long followershipToUnfollowId);

}
