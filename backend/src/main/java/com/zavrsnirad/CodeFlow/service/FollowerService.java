package com.zavrsnirad.CodeFlow.service;

import com.zavrsnirad.CodeFlow.domain.Follower;

public interface FollowerService {

    Follower saveFollower(Follower follower);

    Follower findById(Long followerId);

    Follower findByFollowedAndFollower(Long followedId, Long followerId);

    void deleteFollower(Long followerId);

}
