package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name = "TASK_GRADE")
public class TaskGrade{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="task_grade_id")
    private UUID taskGradeId;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "grader_id", referencedColumnName = "user_id")
    private User grader;

    @ManyToOne
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Task task;

    public TaskGrade() {
    }

    public TaskGrade(@Min(1) @Max(5) Integer grade, User grader, Task task) {
        this.grade = grade;
        this.grader = grader;
        this.task = task;
    }

    public UUID getTaskGradeId() {
        return taskGradeId;
    }

    public void setTaskGradeId(UUID taskGradeId) {
        this.taskGradeId = taskGradeId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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


}
