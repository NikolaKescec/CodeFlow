package com.zavrsnirad.CodeFlow.dto.json;

import java.util.UUID;

public class TaskGradeDtoJson {

    private UUID taskGradeId;

    private Integer grade;

    private UserDtoJson grader;

    public TaskGradeDtoJson() {
    }

    public TaskGradeDtoJson(UUID taskGradeId, Integer grade, UserDtoJson grader) {
        this.taskGradeId = taskGradeId;
        this.grade = grade;
        this.grader = grader;
    }

    public UUID getTaskGradeId() {
        return taskGradeId;
    }

    public void setTaskGradeId(UUID taskGradeId) {
        this.taskGradeId = taskGradeId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public UserDtoJson getGrader() {
        return grader;
    }

    public void setGrader(UserDtoJson grader) {
        this.grader = grader;
    }
}
