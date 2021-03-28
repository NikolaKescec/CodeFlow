package com.zavrsnirad.CodeFlow.dto.json;

public class SolutionGradeDtoJson {

    private Long solutionGradeId;

    private Integer grade;

    private UserDtoJson grader;

    public SolutionGradeDtoJson(Long solutionGradeId, Integer grade, UserDtoJson grader) {
        this.solutionGradeId = solutionGradeId;
        this.grade = grade;
        this.grader = grader;
    }

    public Long getSolutionGradeId() {
        return solutionGradeId;
    }

    public void setSolutionGradeId(Long solutionGradeId) {
        this.solutionGradeId = solutionGradeId;
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
