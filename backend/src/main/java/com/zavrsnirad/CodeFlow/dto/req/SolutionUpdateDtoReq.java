package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SolutionUpdateDtoReq extends SolutionDtoReq{
    @NotNull
    private Long solutionId;

    public SolutionUpdateDtoReq(@NotEmpty String code, @NotNull Long languageId) {
        super(code, languageId);
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }
}
