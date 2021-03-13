package com.zavrsnirad.CodeFlow.dto.json;

import com.zavrsnirad.CodeFlow.domain.SolutionComment;
import com.zavrsnirad.CodeFlow.domain.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

public class SolutionDtoJson {

    private UUID solutionId;

    private String code;

    private String language;

    private UserDtoJson author;

    private List<SolutionCommentDtoJson> comments;

    public SolutionDtoJson(UUID solutionId, String code, String language, UserDtoJson author, List<SolutionCommentDtoJson> comments) {
        this.solutionId = solutionId;
        this.code = code;
        this.language = language;
        this.author = author;
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

    public UserDtoJson getAuthor() {
        return author;
    }

    public void setAuthor(UserDtoJson author) {
        this.author = author;
    }

    public List<SolutionCommentDtoJson> getComments() {
        return comments;
    }

    public void setComments(List<SolutionCommentDtoJson> comments) {
        this.comments = comments;
    }
}
