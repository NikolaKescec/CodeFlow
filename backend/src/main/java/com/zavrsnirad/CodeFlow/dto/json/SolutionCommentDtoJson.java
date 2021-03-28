package com.zavrsnirad.CodeFlow.dto.json;

public class SolutionCommentDtoJson {

    private Long solutionCommentId;

    private String comment;

    private UserDtoJson commenter;

    public SolutionCommentDtoJson(Long solutionCommentId, String comment, UserDtoJson commenter) {
        this.solutionCommentId = solutionCommentId;
        this.comment = comment;
        this.commenter = commenter;
    }

    public Long getSolutionCommentId() {
        return solutionCommentId;
    }

    public void setSolutionCommentId(Long solutionCommentId) {
        this.solutionCommentId = solutionCommentId;
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
