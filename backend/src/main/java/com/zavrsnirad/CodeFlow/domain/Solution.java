package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Solution extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="solution_id")
    private Long solutionId;

    @Column(nullable = false, length = 4096)
    private String code;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
    private Language language;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "programmer_id")
    private Programmer author;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "solution")
    private List<SolutionComment> comments;

    @Formula("(SELECT AVG(G.GRADE) FROM SOLUTION_GRADE G WHERE G.solution_id = solution_id)")
    private Double averageGrade;

    @OneToMany(mappedBy = "solution", cascade = CascadeType.REMOVE)
    private List<SolutionGrade> grades;

    @ManyToOne
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    private Task task;

    public Solution() {
    }

    public Solution(String code, Language language, Programmer author, Task task) {
        this.code = code;
        this.language = language;
        this.author = author;
        this.task = task;
    }

    public List<SolutionGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<SolutionGrade> grades) {
        this.grades = grades;
    }

    public List<SolutionComment> getComments() {
        return comments;
    }

    public void setComments(List<SolutionComment> comments) {
        this.comments = comments;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Programmer getAuthor() {
        return author;
    }

    public void setAuthor(Programmer author) {
        this.author = author;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(solutionId, solution.solutionId) && Objects.equals(code, solution.code) && Objects.equals(language, solution.language) && Objects.equals(author, solution.author) && Objects.equals(comments, solution.comments) && Objects.equals(averageGrade, solution.averageGrade) && Objects.equals(grades, solution.grades) && Objects.equals(task, solution.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionId, code, language, author, comments, averageGrade, grades, task);
    }
}
