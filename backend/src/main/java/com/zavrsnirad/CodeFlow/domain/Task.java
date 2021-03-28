package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Task extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long taskId;

    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id", referencedColumnName = "programmer_id")
    private Programmer owner;

    @Column(name = "task_text", nullable = false)
    private String taskText;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="author_solution_id", referencedColumnName = "solution_id")
    private Solution authorSolution;

    @Column(nullable = false, name = "input_format")
    private String inputFormat;

    @Column(nullable = false, name = "output_format")
    private String outputFormat;

    @Formula("(SELECT AVG(G.GRADE) FROM TASK_GRADE G WHERE G.task_id = task_id)")
    private Double averageGrade;

    @OneToMany(mappedBy = "task")
    private List<TestCase> testCases;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    private List<Solution> solutions;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task")
    private List<TaskComment> comments;

    @OneToMany(mappedBy = "task")
    private List<TaskGrade> grades;

    @ManyToMany
    private List<Language> writtenIn;

    public Task() {
    }

    public Task(Programmer owner, String taskText, String inputFormat, String outputFormat) {
        this.owner = owner;
        this.taskText = taskText;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
    }

    public List<TaskGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<TaskGrade> grades) {
        this.grades = grades;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<TaskComment> getComments() {
        return comments;
    }

    public void setComments(List<TaskComment> comments) {
        this.comments = comments;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Programmer getOwner() {
        return owner;
    }

    public void setOwner(Programmer owner) {
        this.owner = owner;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Solution getAuthorSolution() {
        return authorSolution;
    }

    public void setAuthorSolution(Solution authorSolution) {
        this.authorSolution = authorSolution;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public List<Language> getWrittenIn() {
        return writtenIn;
    }

    public void setWrittenIn(List<Language> writtenIn) {
        this.writtenIn = writtenIn;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public void addSolution(Solution solution) {
        if(this.solutions == null) {
            solutions = new ArrayList<>();
        }
        this.solutions.add(solution);
    }

    public void removeSolution(Solution solution) {
        this.solutions.remove(solution);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(owner, task.owner) && Objects.equals(taskText, task.taskText) && Objects.equals(authorSolution, task.authorSolution) && Objects.equals(inputFormat, task.inputFormat) && Objects.equals(outputFormat, task.outputFormat) && Objects.equals(averageGrade, task.averageGrade) && Objects.equals(testCases, task.testCases) && Objects.equals(solutions, task.solutions) && Objects.equals(comments, task.comments) && Objects.equals(grades, task.grades) && Objects.equals(writtenIn, task.writtenIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, owner, taskText, authorSolution, inputFormat, outputFormat, averageGrade, testCases, solutions, comments, grades, writtenIn);
    }
}
