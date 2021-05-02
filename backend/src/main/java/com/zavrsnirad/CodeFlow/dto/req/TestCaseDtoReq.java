package com.zavrsnirad.CodeFlow.dto.req;

import com.sun.istack.NotNull;

public class TestCaseDtoReq {
    private Long testCaseId;

    @NotNull
    private String input;

    @NotNull
    private String output;

    public TestCaseDtoReq(Long testCaseId, String input, String output) {
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
