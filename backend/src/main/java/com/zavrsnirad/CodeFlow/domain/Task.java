package com.zavrsnirad.CodeFlow.domain;

import com.zavrsnirad.CodeFlow.domain.composite.TaskId;
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
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "task_text", nullable = false)
    private String taskText;

    private String language;

    @Column(nullable = false)
    private String correctOutput;

    @Column(name="author_solution")
    private String authorSolution;

    @OneToMany
    @JoinColumn(name="task_id")
    @JoinColumn(name="user_id")
    private List<Solution> solutions;

    public Task() {
    }

    public Task(User owner, String taskText, String language, String correctOutput, String authorSolution, List<Solution> solutions) {
        this.owner = owner;
        this.taskText = taskText;
        this.language = language;
        this.correctOutput = correctOutput;
        this.authorSolution = authorSolution;
        this.solutions = solutions;
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
