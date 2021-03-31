package com.zavrsnirad.CodeFlow.dto.req;

import com.sun.istack.NotNull;

public class TestCaseDtoReq {

    @NotNull
    private String input;

    @NotNull
    private String output;

    public TestCaseDtoReq(@NotNull String input, @NotNull String output) {
        this.input = input;
        this.output = output;
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
