package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RefreshToken {
    @Id
    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(unique = true, nullable = false)
    private UUID refreshToken;

    public RefreshToken() {
    }

    public RefreshToken(UUID refreshToken, User user) {
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
