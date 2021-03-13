package com.zavrsnirad.CodeFlow.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Solution {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="solution_id")
    private UUID solutionId;

    @Column(nullable = false)
    private String code;

    private String language;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "solution")
    private List<SolutionComment> comments;

    public Solution() {
    }

    public Solution(String code, String language, User author) {
        this.code = code;
        this.language = language;
        this.author = author;
    }

    public List<SolutionComment> getComments() {
        return comments;
    }

    public void setComments(List<SolutionComment> comments) {
        this.comments = comments;
    }

    public UUID getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(UUID solutionId) {
        this.solutionId = solutionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
