package com.zavrsnirad.CodeFlow.dto.json;

import com.zavrsnirad.CodeFlow.domain.Programmer;

import javax.persistence.*;

public class FollowerDtoJson {

    private Long followerId;

    private String programmer;

    private String follower;

    private boolean pending;

    public FollowerDtoJson(Long followerId, String programmer, String follower, boolean pending) {
        this.followerId = followerId;
        this.programmer = programmer;
        this.follower = follower;
        this.pending = pending;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public String getProgrammer() {
        return programmer;
    }

    public void setProgrammer(String programmer) {
        this.programmer = programmer;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
