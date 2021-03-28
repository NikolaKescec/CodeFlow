package com.zavrsnirad.CodeFlow.dto.json;

public class TaskCommentDtoJson {

    private Long taskCommentId;

    private String comment;

    private UserDtoJson commenter;

    public TaskCommentDtoJson(Long taskCommentId, String comment, UserDtoJson commenter) {
        this.taskCommentId = taskCommentId;
        this.comment = comment;
        this.commenter = commenter;
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

    public UserDtoJson getCommenter() {
        return commenter;
    }

    public void setCommenter(UserDtoJson commenter) {
        this.commenter = commenter;
    }
}
