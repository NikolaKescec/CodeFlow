package com.zavrsnirad.CodeFlow.dto.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TaskUpdateDtoReq extends TaskDtoReq{
    @NotNull(message = ("Task ID can not be null!"))
    private Long taskId;

    public TaskUpdateDtoReq(@NotEmpty Long author, @NotEmpty String taskText, @NotEmpty String inputFormat, @NotEmpty String outputFormat, @NotEmpty List<Long> language, @NotEmpty List<TestCaseDtoReq> testCase) {
        super(author, taskText, inputFormat, outputFormat, language, testCase);
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

}
