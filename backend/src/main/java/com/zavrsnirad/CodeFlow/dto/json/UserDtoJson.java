package com.zavrsnirad.CodeFlow.dto.json;

import java.util.UUID;

public class UserDtoJson {

    private UUID id;
    private String username;
    private Integer points;

    public UserDtoJson(UUID id, String username, Integer points) {
        this.id = id;
        this.username = username;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
