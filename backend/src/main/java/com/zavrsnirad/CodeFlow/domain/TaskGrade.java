package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity(name = "TASK_GRADE")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"grader_id", "task_id"})})
public class TaskGrade extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_grade_id")
    private Long taskGradeId;

    @Column(nullable = false)
    @Min(1)
    @Max(5)
    private Integer grade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grader_id", referencedColumnName = "programmer_id")
    private Programmer grader;

    @ManyToOne(optional = false)
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    private Task task;

    public TaskGrade() {
    }

    public TaskGrade(@Min(1) @Max(5) Integer grade, Programmer grader, Task task) {
        this.grade = grade;
        this.grader = grader;
        this.task = task;
    }

    public Long getTaskGradeId() {
        return taskGradeId;
    }

    public void setTaskGradeId(Long taskGradeId) {
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

    public Programmer getGrader() {
        return grader;
    }

    public void setGrader(Programmer grader) {
        this.grader = grader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskGrade taskGrade = (TaskGrade) o;
        return Objects.equals(taskGradeId, taskGrade.taskGradeId) && Objects.equals(grade, taskGrade.grade) && Objects.equals(grader, taskGrade.grader) && Objects.equals(task, taskGrade.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskGradeId, grade, grader, task);
    }
}
