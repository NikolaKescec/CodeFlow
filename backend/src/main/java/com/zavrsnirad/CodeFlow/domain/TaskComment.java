package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "TASK_COMMENT")
public class TaskComment extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_comment_id")
    private Long taskCommentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "commenter_id", referencedColumnName = "programmer_id")
    private Programmer commenter;

    @ManyToOne(optional = false)
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    private Task task;

    public TaskComment() {
    }

    public TaskComment(String comment, Programmer commenter, Task task) {
        this.comment = comment;
        this.commenter = commenter;
        this.task = task;
    }

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Programmer getCommenter() {
        return commenter;
    }

    public void setCommenter(Programmer commenter) {
        this.commenter = commenter;
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
        TaskComment that = (TaskComment) o;
        return Objects.equals(taskCommentId, that.taskCommentId) && Objects.equals(comment, that.comment) && Objects.equals(commenter, that.commenter) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskCommentId, comment, commenter, task);
    }
}
