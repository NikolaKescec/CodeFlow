package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SolutionDtoReq {

    @NotEmpty(message = "Solution has to have code!")
    private String code;

    @NotNull(message = "Language id can not be null!")
    private Long languageId;

    public SolutionDtoReq(@NotEmpty String code, @NotNull Long languageId) {
        this.code = code;
        this.languageId = languageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
}
