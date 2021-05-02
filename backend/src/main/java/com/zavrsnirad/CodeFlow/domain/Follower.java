package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Follower extends TimeAndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    private Long followerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "programmer", referencedColumnName = "programmer_id")
    private Programmer programmer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "follower", referencedColumnName = "programmer_id")
    private Programmer follower;

    @Column(nullable = false)
    private boolean pending;

    public Follower() {
    }

    public Follower(Programmer programmer, Programmer follower, boolean pending) {
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

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Programmer getFollower() {
        return follower;
    }

    public void setFollower(Programmer follower) {
        this.follower = follower;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follower follower1 = (Follower) o;
        return pending == follower1.pending && Objects.equals(followerId, follower1.followerId) && Objects.equals(programmer, follower1.programmer) && Objects.equals(follower, follower1.follower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, programmer, follower, pending);
    }
}
