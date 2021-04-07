package com.zavrsnirad.CodeFlow.dto.json;

public class GradeDtoJson {

    private Long gradeId;

    private Integer grade;

    private UserDtoJson grader;

    public GradeDtoJson(Long gradeId, Integer grade, UserDtoJson grader) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.grader = grader;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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
