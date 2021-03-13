package com.zavrsnirad.CodeFlow.dto.json;

import java.util.UUID;

public class TaskDtoJson {

    UserDtoJson author;

    private UUID taskId;

    private String taskText;

    private String language;

    private String correctOutput;

    private String authorSolution;

    private Double averageGrade;

    private SolutionDtoJson loggedInUserSolution;

    private TaskGradeDtoJson loggedInUserGrade;

    public TaskDtoJson(UserDtoJson author, UUID taskId, String taskText, String language, String correctOutput, String authorSolution, Double averageGrade, SolutionDtoJson loggedInUserSolution, TaskGradeDtoJson loggedInUserGrade) {
        this.author = author;
        this.taskId = taskId;
        this.taskText = taskText;
        this.language = language;
        this.correctOutput = correctOutput;
        this.authorSolution = authorSolution;
        this.averageGrade = averageGrade;
        this.loggedInUserSolution = loggedInUserSolution;
        this.loggedInUserGrade = loggedInUserGrade;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public TaskGradeDtoJson getLoggedInUserGrade() {
        return loggedInUserGrade;
    }

    public void setLoggedInUserGrade(TaskGradeDtoJson loggedInUserGrade) {
        this.loggedInUserGrade = loggedInUserGrade;
    }

    public UserDtoJson getAuthor() {
        return author;
    }

    public void setAuthor(UserDtoJson author) {
        this.author = author;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(String correctOutput) {
        this.correctOutput = correctOutput;
    }

    public String getAuthorSolution() {
        return authorSolution;
    }

    public void setAuthorSolution(String authorSolution) {
        this.authorSolution = authorSolution;
    }

    public SolutionDtoJson getLoggedInUserSolution() {
        return loggedInUserSolution;
    }

    public void setLoggedInUserSolution(SolutionDtoJson loggedInUserSolution) {
        this.loggedInUserSolution = loggedInUserSolution;
    }
}
