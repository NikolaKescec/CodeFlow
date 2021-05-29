package com.zavrsnirad.CodeFlow.dto.json;

public class FollowerDtoJson {

    private Long followerId;

    private boolean following;

    private boolean pending;

    public FollowerDtoJson(Long followerId, boolean following, boolean pending) {
        this.followerId = followerId;
        this.following = following;
        this.pending = pending;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
