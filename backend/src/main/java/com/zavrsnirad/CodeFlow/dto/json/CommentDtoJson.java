package com.zavrsnirad.CodeFlow.dto.json;

public class CommentDtoJson {

    private Long commentId;

    private String comment;

    private UserDtoJson commenter;

    public CommentDtoJson(Long commentId, String comment, UserDtoJson commenter) {
        this.commentId = commentId;
        this.comment = comment;
        this.commenter = commenter;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommetId(Long taskCommentId) {
        this.commentId = taskCommentId;
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
