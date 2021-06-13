package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class TaskDtoReq {

    @NotEmpty
    private Long author;

    @NotEmpty
    private String taskText;

    @NotEmpty
    private String inputFormat;

    @NotEmpty
    private String outputFormat;

    @NotEmpty
    private List<Long> language;

    @NotEmpty
    private List<TestCaseDtoReq> testCase;

    public TaskDtoReq(@NotEmpty Long author, @NotEmpty String taskText, @NotEmpty String inputFormat, @NotEmpty String outputFormat, @NotEmpty List<Long> language, @NotEmpty List<TestCaseDtoReq> testCase) {
        this.author = author;
        this.taskText = taskText;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.language = language;
        this.testCase = testCase;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
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

    public List<Long> getLanguage() {
        return language;
    }

    public void setLanguage(List<Long> language) {
        this.language = language;
    }

    public List<TestCaseDtoReq> getTestCase() {
        return testCase;
    }

    public void setTestCase(List<TestCaseDtoReq> testCase) {
        this.testCase = testCase;
    }
}
