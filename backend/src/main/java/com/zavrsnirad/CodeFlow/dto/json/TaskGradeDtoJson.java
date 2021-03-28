package com.zavrsnirad.CodeFlow.dto.json;

public class TaskGradeDtoJson {

    private Long taskGradeId;

    private Integer grade;

    private UserDtoJson grader;

    public TaskGradeDtoJson() {
    }

    public TaskGradeDtoJson(Long taskGradeId, Integer grade, UserDtoJson grader) {
        this.taskGradeId = taskGradeId;
        this.grade = grade;
        this.grader = grader;
    }

    public Long getTaskGradeId() {
        return taskGradeId;
    }

    public void setTaskGradeId(Long taskGradeId) {
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
