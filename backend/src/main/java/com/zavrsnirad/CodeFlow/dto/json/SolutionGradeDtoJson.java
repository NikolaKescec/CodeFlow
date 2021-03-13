package com.zavrsnirad.CodeFlow.dto.json;

import com.zavrsnirad.CodeFlow.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

public class SolutionGradeDtoJson {

    private UUID solutionGradeId;

    private Integer grade;

    private UserDtoJson grader;

    public SolutionGradeDtoJson(UUID solutionGradeId, Integer grade, UserDtoJson grader) {
        this.solutionGradeId = solutionGradeId;
        this.grade = grade;
        this.grader = grader;
    }

    public UUID getSolutionGradeId() {
        return solutionGradeId;
    }

    public void setSolutionGradeId(UUID solutionGradeId) {
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
