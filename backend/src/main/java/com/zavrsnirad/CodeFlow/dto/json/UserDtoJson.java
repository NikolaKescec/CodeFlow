package com.zavrsnirad.CodeFlow.dto.json;

public class UserDtoJson {

    private Long id;
    private String username;
    private Integer solutionPoints;
    private Integer taskPoints;

    public UserDtoJson(Long id, String username, Integer solutionPoints, Integer taskPoints) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
