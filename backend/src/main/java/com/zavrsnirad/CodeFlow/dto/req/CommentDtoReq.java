package com.zavrsnirad.CodeFlow.dto.req;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class CommentDtoReq {

    @NotNull
    private Long authorId;

    @NotEmpty
    private Long commentBaseId;

    @NotEmpty
    private String commentText;

    public CommentDtoReq(Long authorId, @NotEmpty Long commentBaseId, @NotEmpty String commentText) {
        this.authorId = authorId;
        this.commentBaseId = commentBaseId;
        this.commentText = commentText;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCommentBaseId() {
        return commentBaseId;
    }

    public void setCommentBaseId(Long commentBaseId) {
        this.commentBaseId = commentBaseId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
