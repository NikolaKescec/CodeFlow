package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.domain.Notification;
import com.zavrsnirad.CodeFlow.domain.Programmer;
import com.zavrsnirad.CodeFlow.domain.TimeAndUser;
import com.zavrsnirad.CodeFlow.dto.req.UserDtoReq;
import com.zavrsnirad.CodeFlow.dto.req.UserUpdateDtoReq;
import com.zavrsnirad.CodeFlow.repository.ProgrammerRepository;
import com.zavrsnirad.CodeFlow.service.FollowerService;
import com.zavrsnirad.CodeFlow.service.NotificationService;
import com.zavrsnirad.CodeFlow.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProgrammerServiceJpa implements ProgrammerService {

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Autowired
    private NotificationService notificationService;

     @Autowired
     private FollowerService followerService;

    @Override
    public Programmer findByUsername(String username) {
        Programmer programmer = programmerRepository.findByUsername(username);
        if(programmer == null)
            throw new IllegalArgumentException("This username does not exist!");
        return programmer;
    }

    @Override
    public Programmer findById(Long id) {
        try {
            return programmerRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new IllegalArgumentException("No such programmer!");
        }
    }

    @Override
    public List<Programmer> getProgrammersTaskers() {
        return programmerRepository.findTop20ByOrderByTaskPointsDesc();
    }

    @Override
    public List<Programmer> getProgrammersSolvers() {
        return programmerRepository.findTop20ByOrderBySolutionPointsDesc();
    }

    @Override
    public List<Programmer> getUsers() {
        return programmerRepository.findAll();
    }

    @Override
    public Programmer addProgrammer(UserDtoReq user) {
        boolean byUsername, byEmail;

        // check for existing username
        byUsername = programmerRepository.findByUsername(user.getUsername()) != null;
        if(byUsername)
            throw new IllegalArgumentException("Username already taken.");

        // check for existing email
        byEmail = programmerRepository.findByEmail(user.getEmail()) != null;
        if(byEmail)
            throw new IllegalArgumentException("Email already taken.");

        if(user.getPassword().length() < 8){
            throw new IllegalArgumentException("Password length can not be shorter than 8 characters!");
        }

        // User cretion
        Programmer newProgrammer = new Programmer(user.getUsername(), user.getEmail(), "USER");
        newProgrammer.setSolutionPoints(0);
        newProgrammer.setTaskPoints(0);

        // Ekripcija lozinke.
        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(user.getPassword(), salt);
        newProgrammer.setPassword(hashed);
        newProgrammer.setUserCreated(newProgrammer.getUsername());

        // saving user
        newProgrammer = programmerRepository.save(newProgrammer);
        return newProgrammer;
    }

    @Override
    public Follower followingUser(Long followedId, Programmer programmer) {
        try{
            return followerService.findByFollowedAndFollower(followedId, programmer.getProgrammerId());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public void removeProgrammer(String username) {
        Programmer programmer = programmerRepository.findByUsername(username);
        if(programmer == null)
            throw new IllegalArgumentException("This username does not exist!");
        programmerRepository.delete(programmer);
    }

    @Override
    public void updateProgrammer(UserUpdateDtoReq userUpdateDtoReq, Programmer programmer) {
        if(!BCrypt.checkpw(userUpdateDtoReq.getPassword(), programmer.getPassword())){
            throw new IllegalArgumentException("Password is incorrect!");
        }

        programmer.setUsername(userUpdateDtoReq.getUsername());

        if(userUpdateDtoReq.getNewPassword() != null && userUpdateDtoReq.getNewPassword().length() < 8) {
            throw new IllegalArgumentException("New password can not be shorter than 8 characters!");
        }

        if(userUpdateDtoReq.getNewPassword() != null) {
            String newSalt = BCrypt.gensalt(12);
            String newHashed = BCrypt.hashpw(userUpdateDtoReq.getPassword(), newSalt);
            programmer.setPassword(newHashed);
        }

        TimeAndUser.updateModified(programmer, programmer);
        programmerRepository.save(programmer);
    }

    @Override
    public Follower followUser(Long programmerToFollowId, Programmer programmer) {
        if(programmerToFollowId.equals(programmer.getProgrammerId()))
            throw new IllegalArgumentException("User can not follow himself!");

        Programmer toFollowProgrammer = findById(programmerToFollowId);

        try{
            Follower testFollower = followerService.findByFollowedAndFollower(programmerToFollowId, programmer.getProgrammerId());
            throw new IllegalArgumentException("You can not follow same programmer twice!");
        }catch(IllegalArgumentException ignored){}

        Follower follower = new Follower(toFollowProgrammer, programmer, true);
        Notification notification = new Notification(programmer.getUsername() + " wants to follow you.", "followership", toFollowProgrammer, programmer);

        follower.setUserCreated(programmer.getUsername());
        notification.setUserCreated(programmer.getUserCreated());

        notification = notificationService.saveNotification(notification);
        follower = followerService.saveFollower(follower);
        toFollowProgrammer.addFollower(follower);
        toFollowProgrammer.addNofitication(notification);

        programmerRepository.save(toFollowProgrammer);
        return follower;
    }

    @Override
    public void acceptFollowerShip(Long notificationId, Long followerProgrammerId, Programmer programmer) {
        Notification notification = notificationService.findById(notificationId);

        if(!notification.getNotified().getProgrammerId().equals(programmer.getProgrammerId()))
            throw new IllegalArgumentException("Only notified programmer can accept followership!");
        notificationService.removeNotification(notificationId);

        Follower follower;
        try{
            follower = followerService.findByFollowedAndFollower(programmer.getProgrammerId(), followerProgrammerId);
        } catch(IllegalArgumentException ex) {
            return;
        }

        if(!follower.isPending()){
            throw new IllegalArgumentException("Followership has already been decided!");
        }

        follower.setPending(false);

        Notification newNotification = new Notification( programmer.getUsername() + " accepted your follow request!", "info", follower.getFollower(), programmer);
        Programmer notifiedFollower = follower.getFollower();

        newNotification = notificationService.saveNotification(newNotification);
        notifiedFollower.addNofitication(newNotification);

        TimeAndUser.updateModified(follower, programmer);
        followerService.saveFollower(follower);
        programmerRepository.save(notifiedFollower);
    }

    @Override
    public void denyFollower(Long notificationId, Long followerProgrammerId, Programmer programmer) {
        Notification notification = notificationService.findById(notificationId);

        if(!notification.getNotified().getProgrammerId().equals(programmer.getProgrammerId()))
            throw new IllegalArgumentException("Only notified programmer can deny followership!");
        notificationService.removeNotification(notificationId);

        Follower follower;
        try{
            follower = followerService.findByFollowedAndFollower(programmer.getProgrammerId(), followerProgrammerId);
        } catch(IllegalArgumentException ex) {
            return;
        }

        if(!follower.isPending()){
            throw new IllegalArgumentException("Followership has already been decided!");
        }

        followerService.deleteFollower(follower.getFollowerId());
    }

    @Override
    public void unfollowUser(Long followershipToUnfollowId) {
        followerService.deleteFollower(followershipToUnfollowId);
    }

    @Override
    public void addTaskPoints(int points, Programmer programmer) {
        programmer.setTaskPoints(programmer.getTaskPoints() + points);
        programmerRepository.save(programmer);
    }

    @Override
    public void removeTaskPoints(int points, Programmer programmer) {
        programmer.setTaskPoints(Math.max(programmer.getTaskPoints() - points, 0));
        programmerRepository.save(programmer);
    }

    @Override
    public void addSolutionPoints(int points, Programmer programmer) {
        programmer.setSolutionPoints(programmer.getSolutionPoints() + points);
        programmerRepository.save(programmer);
    }

    @Override
    public void removeSolutionPoints(int points, Programmer programmer) {
        programmer.setSolutionPoints(Math.max(programmer.getSolutionPoints() - points, 0));
        programmerRepository.save(programmer);
    }

}
