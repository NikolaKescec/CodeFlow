package com.zavrsnirad.CodeFlow.dto.json;

import com.zavrsnirad.CodeFlow.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

public class TaskCommentDtoJson {

    private UUID taskCommentId;

    private String comment;

    private UserDtoJson commenter;

    public TaskCommentDtoJson(UUID taskCommentId, String comment, UserDtoJson commenter) {
        this.taskCommentId = taskCommentId;
        this.comment = comment;
        this.commenter = commenter;
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

    public UserDtoJson getCommenter() {
        return commenter;
    }

    public void setCommenter(UserDtoJson commenter) {
        this.commenter = commenter;
    }
}
