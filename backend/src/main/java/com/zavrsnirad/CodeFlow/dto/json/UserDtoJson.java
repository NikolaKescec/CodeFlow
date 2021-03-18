package com.zavrsnirad.CodeFlow.dto.json;

import java.util.UUID;

public class UserDtoJson {

    private UUID id;
    private String username;
    private Integer solutionPoints;
    private Integer taskPoints;

    public UserDtoJson(UUID id, String username, Integer solutionPoints, Integer taskPoints) {
        this.id = id;
        this.username = username;
        this.solutionPoints = solutionPoints;
        this.taskPoints = taskPoints;
    }

    public Integer getSolutionPoints() {
        return solutionPoints;
    }

    public void setSolutionPoints(Integer solutionPoints) {
        this.solutionPoints = solutionPoints;
    }

    public Integer getTaskPoints() {
        return taskPoints;
    }

    public void setTaskPoints(Integer taskPoints) {
        this.taskPoints = taskPoints;
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
