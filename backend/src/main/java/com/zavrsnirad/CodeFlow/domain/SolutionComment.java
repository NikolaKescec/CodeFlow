package com.zavrsnirad.CodeFlow.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "SOLUTION_COMMENT")
public class SolutionComment extends TimeAndUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="solution_comment_id")
    private Long solutionCommentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "commenter_id", referencedColumnName = "programmer_id")
    private Programmer commenter;

    @ManyToOne(optional = false)
    @JoinColumn(name="solution_id", referencedColumnName = "solution_id")
    private Solution solution;

    public SolutionComment() {
    }

    public SolutionComment(String comment, Programmer commenter, Solution solution) {
        this.comment = comment;
        this.commenter = commenter;
        this.solution = solution;
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

    public Programmer getCommenter() {
        return commenter;
    }

    public void setCommenter(Programmer commenter) {
        this.commenter = commenter;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionComment that = (SolutionComment) o;
        return Objects.equals(solutionCommentId, that.solutionCommentId) && Objects.equals(comment, that.comment) && Objects.equals(commenter, that.commenter) && Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionCommentId, comment, commenter, solution);
    }
}
