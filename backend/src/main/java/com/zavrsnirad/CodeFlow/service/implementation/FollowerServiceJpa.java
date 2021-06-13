package com.zavrsnirad.CodeFlow.service.implementation;

import com.zavrsnirad.CodeFlow.domain.Follower;
import com.zavrsnirad.CodeFlow.repository.FollowerRepository;
import com.zavrsnirad.CodeFlow.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FollowerServiceJpa implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public Follower saveFollower(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public Follower findById(Long followerId) {
        try{
            return followerRepository.findById(followerId).get();
        } catch(NoSuchElementException ex){
            throw new IllegalArgumentException("No such followership!");
        }
    }

    @Override
    public Follower findByFollowedAndFollower(Long followedId, Long followerId) {
        Follower follower = followerRepository.findByFollowedAndFollowerId(followedId, followerId);
        if(follower == null)
            throw new IllegalArgumentException("There is no such followership!");
        return follower;
    }

    @Override
    public void deleteFollower(Long followerId) {
        Follower follower = findById(followerId);
        followerRepository.delete(follower);
    }
}
