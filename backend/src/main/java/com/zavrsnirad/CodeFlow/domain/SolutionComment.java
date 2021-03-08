package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity(name = "SOLUTION_COMMENT")
public class SolutionComment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="solution_comment_id")
    private UUID solutionCommentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @ManyToOne
    @JoinColumn(name="solution_id")
    private Solution solution;

    public SolutionComment() {
    }

    public SolutionComment(String comment, User commenter, Solution solution) {
        this.comment = comment;
        this.commenter = commenter;
        this.solution = solution;
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

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}
