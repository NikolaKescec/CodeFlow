package com.zavrsnirad.CodeFlow.dto.json;

import com.zavrsnirad.CodeFlow.domain.Solution;
import com.zavrsnirad.CodeFlow.domain.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

public class SolutionCommentDtoJson {

    private UUID solutionCommentId;

    private String comment;

    private UserDtoJson commenter;

    public SolutionCommentDtoJson(UUID solutionCommentId, String comment, UserDtoJson commenter) {
        this.solutionCommentId = solutionCommentId;
        this.comment = comment;
        this.commenter = commenter;
    }

    public UUID getSolutionCommentId() {
        return solutionCommentId;
    }

    public void setSolutionCommentId(UUID solutionCommentId) {
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
