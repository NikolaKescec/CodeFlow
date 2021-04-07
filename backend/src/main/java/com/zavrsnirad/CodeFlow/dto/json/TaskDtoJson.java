package com.zavrsnirad.CodeFlow.dto.json;

import java.util.List;

public class TaskDtoJson {

    private String author;

    private Long taskId;

    private String taskText;

    private List<LanguageDtoJson> writtenIn;

    private List<TestCaseDtoJson> testCases;

    private String inputFormat;

    private String outputFormat;

    private Long authorSolution;

    private Double averageGrade;

    private Long loggedInUserSolution;

    private GradeDtoJson loggedInUserGrade;

    public TaskDtoJson(String author, Long taskId, String taskText, List<LanguageDtoJson> writtenIn, List<TestCaseDtoJson> testCases, String inputFormat, String outputFormat, Long authorSolution, Double averageGrade, Long loggedInUserSolution, GradeDtoJson loggedInUserGrade) {
        this.author = author;
        this.taskId = taskId;
        this.taskText = taskText;
        this.writtenIn = writtenIn;
        this.testCases = testCases;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.authorSolution = authorSolution;
        this.averageGrade = averageGrade;
        this.loggedInUserSolution = loggedInUserSolution;
        this.loggedInUserGrade = loggedInUserGrade;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public List<LanguageDtoJson> getWrittenIn() {
        return writtenIn;
    }

    public void setWrittenIn(List<LanguageDtoJson> writtenIn) {
        this.writtenIn = writtenIn;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public Long getAuthorSolution() {
        return authorSolution;
    }

    public void setAuthorSolution(Long authorSolution) {
        this.authorSolution = authorSolution;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Long getLoggedInUserSolution() {
        return loggedInUserSolution;
    }

    public void setLoggedInUserSolution(Long loggedInUserSolution) {
        this.loggedInUserSolution = loggedInUserSolution;
    }

    public List<TestCaseDtoJson> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseDtoJson> testCases) {
        this.testCases = testCases;
    }

    public GradeDtoJson getLoggedInUserGrade() {
        return loggedInUserGrade;
    }

    public void setLoggedInUserGrade(GradeDtoJson loggedInUserGrade) {
        this.loggedInUserGrade = loggedInUserGrade;
    }
}
