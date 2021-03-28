package com.zavrsnirad.CodeFlow.dto.json;

public class TestCaseDtoJson {

    private Long testCaseId;

    private String input;

    private String output;

    public TestCaseDtoJson(Long testCaseId, String input, String output) {
        this.testCaseId = testCaseId;
        this.input = input;
        this.output = output;
    }

    public Long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
