package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class TaskDtoReq {

    @NotEmpty
    private UUID owner;

    @NotEmpty
    private String taskText;

    private String language;

    @NotEmpty
    private String correctOutput;

    private String authorSolution;

}
