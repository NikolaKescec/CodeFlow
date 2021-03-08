package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "TASK_COMMENT")
public class TaskComment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="task_comment_id")
    private UUID taskCommentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @ManyToOne
    @JoinColumn(name="task_id")
    @JoinColumn(name="user_id")
    private Task task;

    public TaskComment() {
    }

    public TaskComment(String comment, User commenter, Task task) {
        this.comment = comment;
        this.commenter = commenter;
        this.task = task;
    }

    public UUID getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(UUID taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
