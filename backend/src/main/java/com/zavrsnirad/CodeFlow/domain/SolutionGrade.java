package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name = "SOLUTION_GRADE")
public class SolutionGrade {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="solution_grade_id")
    private UUID solutionGradeId;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "grader_id", referencedColumnName = "user_id")
    private User grader;

    @ManyToOne
    @JoinColumn(name="solution_id", referencedColumnName = "solution_id")
    private Solution solution;

    public SolutionGrade() {
    }

    public SolutionGrade(@Min(1) @Max(5) Integer grade, User grader, Solution solution) {
        this.grade = grade;
        this.grader = grader;
        this.solution = solution;
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

    public User getGrader() {
        return grader;
    }

    public void setGrader(User grader) {
        this.grader = grader;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
