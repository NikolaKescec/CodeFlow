package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

public class TaskDtoReq {

    @NotEmpty
    private Long author;

    @NotEmpty
    private String taskText;

    private List<String> language;

    private List<String> testCases;

}
