package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity(name = "SOLUTION_GRADE")
public class SolutionGrade extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="solution_grade_id")
    private Long solutionGradeId;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer grade;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "grader_id", referencedColumnName = "programmer_id")
    private Programmer grader;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name="solution_id", referencedColumnName = "solution_id")
    private Solution solution;

    public SolutionGrade() {
    }

    public SolutionGrade(@Min(1) @Max(5) Integer grade, Programmer grader, Solution solution) {
        this.grade = grade;
        this.grader = grader;
        this.solution = solution;
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

    public Programmer getGrader() {
        return grader;
    }

    public void setGrader(Programmer grader) {
        this.grader = grader;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionGrade that = (SolutionGrade) o;
        return Objects.equals(solutionGradeId, that.solutionGradeId) && Objects.equals(grade, that.grade) && Objects.equals(grader, that.grader) && Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionGradeId, grade, grader, solution);
    }
}
