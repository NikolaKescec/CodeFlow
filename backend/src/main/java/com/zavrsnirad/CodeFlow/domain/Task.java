package com.zavrsnirad.CodeFlow.domain;

import com.zavrsnirad.CodeFlow.domain.composite.TaskId;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@IdClass(TaskId.class)
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="task_id")
    private UUID taskId;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User owner;

    @Column(name = "task_text", nullable = false)
    private String taskText;

    private String language;

    @Column(nullable = false)
    private String correctOutput;

    @Column(name="author_solution", length = 4096)
    private String authorSolution;

    @Formula("(SELECT AVG(G.GRADE) FROM TASK_GRADE G WHERE G.task_id = task_id)")
    private Double averageGrade;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private List<Solution> solutions;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "task")
    private List<TaskComment> comments;

    @OneToMany(mappedBy = "task")
    private List<TaskGrade> grades;

    public Task() {
    }

    public Task(User owner, String taskText, String language, String correctOutput, String authorSolution) {
        this.owner = owner;
        this.taskText = taskText;
        this.language = language;
        this.correctOutput = correctOutput;
        this.authorSolution = authorSolution;
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

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(String correctOutput) {
        this.correctOutput = correctOutput;
    }

    public String getAuthorSolution() {
        return authorSolution;
    }

    public void setAuthorSolution(String authorSolution) {
        this.authorSolution = authorSolution;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }


}
