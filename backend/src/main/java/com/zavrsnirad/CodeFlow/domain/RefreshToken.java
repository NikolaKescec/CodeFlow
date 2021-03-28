package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class RefreshToken extends  TimeAndUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name="programmer_id")
    @MapsId
    private Programmer programmer;

    @Column(unique = true, nullable = false)
    private UUID refreshToken;

    public RefreshToken() {
    }

    public RefreshToken(UUID refreshToken, Programmer programmer) {
        this.refreshToken = refreshToken;
        this.programmer = programmer;
    }

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Programmer getUser() {
        return programmer;
    }

    public void setUser(Programmer programmer) {
        this.programmer = programmer;
    }

}
